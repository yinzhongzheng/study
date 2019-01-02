package com.yzz.test;

import com.yzz.masterchose.ZookeeperConnector;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2019/1/2
 *
 * @Since 0.0.1
 */
public class TestZk implements ITestZk, Watcher {

    public static final String ZK_HOST = "192.168.43.210:2181";

    public CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public ZooKeeper createZookeeperConnByDefault() throws IOException {
        ZooKeeper zooKeeper = new ZooKeeper(ZK_HOST, 5000, this);
        return null;
    }

    @Override
    public ZooKeeper createZookeeperConnBySession() throws Exception {
        ZooKeeper zooKeeper = ZookeeperConnector.getZooKeeper(ZK_HOST, 5000, this);
        //ZooKeeper zooKeeper = new ZooKeeper(ZK_HOST, 5000, this);
        long sessionID = zooKeeper.getSessionId();
        byte[] sessionPassword = zooKeeper.getSessionPasswd();
        System.out.println(sessionID + "==" + new String(sessionPassword));

        countDownLatch.await();

        System.out.println("Session--复用-正确");
        ZooKeeper zk = new ZooKeeper(ZK_HOST, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("zkSuccess:" + event);
            }
        }, sessionID, sessionPassword);
        System.out.println(zk.getSessionId());


        System.out.println("Session--复用-错误");
        ZooKeeper zkError = new ZooKeeper(ZK_HOST, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("zkError:" + event);
            }
        }, sessionID, "1".getBytes());

        System.in.read();
        return null;
    }

    @Override
    public void addZNode() throws Exception {
        ZooKeeper zooKeeper = ZookeeperConnector.getZookeeper(event -> {
            System.out.println(event.getType());
        });
//        //同步
        String name = zooKeeper.create("/zk_test_sync", "yzz".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(name);

        //异步
        zooKeeper.create("/zk_test_async", "yzz".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new AsyncCallback.StringCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, String name) {
                //KeeperException.Code  rc  结果码 ResultCode
                System.out.println(name + "==" + path + "==" + rc + "==" + ctx);
            }
        }, "test");

        System.in.read();
    }

    @Override
    public void deleteZNode() throws Exception {
        ZooKeeper zooKeeper = ZookeeperConnector.getZookeeper(this);
        zooKeeper.delete("/test-zk2", 0, new AsyncCallback.VoidCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx) {
                System.out.println(KeeperException.Code.get(rc));
            }
        }, "");
    }

    @Override
    public void updateZNode() throws Exception {
        ZooKeeper zooKeeper = ZookeeperConnector.getZookeeper(this);


    }

    public void event() throws Exception{

    }

    @Override
    public void searchZNode() throws Exception {
        ZooKeeper zooKeeper = ZookeeperConnector.getZookeeper(this);
        Stat stat = new Stat();
        byte[] data = zooKeeper.getData("/zk_test_sync", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event);
            }
        }, stat);
        System.out.println(new java.lang.String(data) + "==" + stat.getVersion());
        Random random = new Random();
        Stat s = zooKeeper.setData("/zk_test_sync", ("111111ddd4rf" + random.nextInt()).getBytes(), stat.getVersion());
        System.out.println(s.getVersion());
        System.in.read();


    }


    public static void main(String[] args) {
        try {
            TestZk testZk = new TestZk();
            //创建连接
            //testZk.createZookeeperConnBySession();
            //创建ZNode
            //testZk.addZNode();
            //删除
            //testZk.deleteZNode();
            //查询
            testZk.searchZNode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {

    }
}
