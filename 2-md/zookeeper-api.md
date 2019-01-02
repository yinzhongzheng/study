# Zookeeper-针对比较重要的API内容解析

## version
* Zookeeper对ZNode进行事务（可以对数据造成更改的操作）操作时，采用CAS（Compare And Swap）无锁机制来进行数据交换。
* CAS无锁机制相比锁机制具有更改的性能（线程无序等待，减少了线程切换）,但是会造成ABA的问题。
* 所以引入了version字段来很轻易的解决这个问题,这点很重要。

## ZNode的节点类型(4种)
1. 按照生命周期来分包括：持久节点(PERSISTENT)和零时节点(EPHEMERAL)
2. 安装节点是否有序来分：有序节点和无序节点(SEQUENTIAL)

## API解析
### 增
1. 同步方法
String create(final String path, byte data[], List<ACL> acl,CreateMode createMode);
2. 异步方法
void create(final String path, byte data[], List<ACL> acl,CreateMode createMode,  StringCallback cb, Object ctx)

|path|data|acl|createMode|cb|ctx|
|:-----------| :-----------|:-----------|:-----------|:-----------|:-----------|
|ZNode路径|数据|权限组(Ids以提供权限封装)|节点的创建类型|异步结果回调(异步调用)|回调方法会将提供该参数(异步调用)|

### 删
1. void delete(final String path, int version)
2.  delete(final String path, int version, VoidCallback cb,Object ctx)

|path|version|cb|ctx|
|:-----------| :-----------|:-----------|:-----------|
|ZNode路径|版本号|异步结果回调(异步调用)|回调方法会将提供该参数(异步调用)|

### 改
1. void setData(final String path, byte data[], int version,StatCallback cb, Object ctx)
2. Stat setData(final String path, byte data[], int version)

|path|data|version|cb|ctx|
|:-----------| :-----------|:-----------|:-----------|:-----------|
|ZNode路径|数据|版本号|异步结果回调(异步调用)|回调方法会将提供该参数(异步调用)|

### 查
> #### ZNode
1. byte[] getData(final String path, Watcher watcher, Stat stat)
2. byte[] getData(String path, boolean watch, Stat stat)
3. getData(final String path, Watcher watcher,DataCallback cb, Object ctx)
4. getData(String path, boolean watch, DataCallback cb, Object ctx)

|path|watch|watcher|cb|ctx|
|:-----------| :-----------|:-----------|:-----------|:-----------|
|ZNode路径|是否监听(复用之前创建注册监听)|设置监听器|异步结果回调(异步调用)|回调方法会将提供该参数(异步调用)|

> #### Children(获取节点所有一级子节点信息)
* getChildren(String path, boolean watch, Children2Callback cb,Object ctx)

|path|watch|watcher|cb|ctx|
|:-----------| :-----------|:-----------|:-----------|:-----------|
|ZNode路径|是否监听(复用之前创建注册监听)|设置监听器|异步结果回调(异步调用)|回调方法会将提供该参数(异步调用)|

## 监听触发

|\|None|NodeCreated|NodeDeleted|NodeDataChanged|NodeChildrenChanged|
|:-----------:|:-----------:| :-----------:|:-----------:|:-----------:|:-----------:|
|创建连接|√|-|-|-|-|
|getData|-|-|-|√|-|
|getChildren|-|-|-|-|√|
|exists|-|√|√|√|-|

## 测试用例
```java
package com.yzz.test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2019/1/2
 *
 * @Since 0.0.1
 */
public class EventTest implements Watcher, AsyncCallback.StringCallback {

    public static final String ZK_HOST = "192.168.43.210:2181";

    CountDownLatch countDownLatch = new CountDownLatch(1);
    private ZooKeeper zooKeeper;

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
        try {
            if (Event.KeeperState.SyncConnected == event.getState()) {
                Event.EventType eventType = event.getType();
                String path = event.getPath();
                switch (eventType) {
                    case None:
                        if (null == event.getPath()) {
                            System.out.println("连接成功");
                            countDownLatch.countDown();
                        }
                        break;
                    case NodeCreated:
                        //子节点被创建
                        zooKeeper.exists(path, true);
                        //zooKeeper.getData(path,true,new Stat());
                        System.out.println("节点被创建" + path);
                        break;
                    case NodeDeleted:
                        //子节点被删除
                        zooKeeper.exists(path, true);
                        System.out.println("节点被删除" + event.getPath());
                        break;
                    case NodeDataChanged:
                        //节点数据被修改
                        Stat stat = new Stat();
                        zooKeeper.getData(path, true, stat);
                        System.out.println("节点数据被修改" + path + "[stat: " + stat + " ]");
                        break;
                    case NodeChildrenChanged:
                        //子节点发生改变
                        zooKeeper.getChildren(path, true);
                        System.out.println("子节点发生改变" + path);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doZK() {
        try {
            zooKeeper = new ZooKeeper(ZK_HOST, 5000, this);
            countDownLatch.await();

            //创建(同步)
            zooKeeper.create("/ev", "haha".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.exists("/ev-sync", true);
            //异步
            zooKeeper.create("/ev-sync", "c".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, this, "1");

            //获取
            Stat stat1 = new Stat();
            zooKeeper.getData("/ev", this, stat1);
            zooKeeper.getChildren("/ev", true);

            //修改 version可以是-1，-1是一种标识，标识服务器以最新的版本号进行更新，那么就会出现数据不一致的情况，多个更新操作将会更新同一个值
            //version字段是CAS的演化版本，通过增加version字段解决ABA的问题，实现了高性能了高并发下的原子安全操作。
            zooKeeper.setData("/ev", "hha".getBytes(), stat1.getVersion());
            zooKeeper.setData("/ev-sync", "c".getBytes(), -1);

            //创建子节点同步
            zooKeeper.create("/ev/children", "c".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.create("/ev/children-1", "c".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            //删除子节点
            Stat stat2 = new Stat();
            zooKeeper.getData("/ev/children-1", this, stat2);
            zooKeeper.delete("/ev/children-1", stat2.getVersion());

            //删除子节点
            Stat stat3 = new Stat();
            zooKeeper.getData("/ev/children", this, stat2);
            zooKeeper.delete("/ev/children", stat3.getVersion());

            //删除当前节点
            Stat stat4 = new Stat();
            zooKeeper.getData("/ev", this, stat4);
            zooKeeper.delete("/ev", stat4.getVersion());
            zooKeeper.delete("/ev-sync", -1);

            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventTest test = new EventTest();
        test.doZK();
    }

    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        if (KeeperException.Code.get(rc) == KeeperException.Code.OK) {
            System.out.println(path + "--->SUCCESS");
        }

    }
}

```
