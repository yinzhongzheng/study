#Zookeeper原生API BUG
## **问题**
###  Zookeeper客户端在创建远程连接的时候，不会抛出超时异常
## 查看问题
### 查看源码，
> ```connectTimeout = sessionTimeout / hostProvider.size();```hostProvider是Zookeeper集群元素对象，size代表着集群中实例的个数。
> 但是一直未见其抛出IO异常，这个问题困扰着我。
### 一种替代方案
```java
package com.yzz.masterchose;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/26
 *
 * @Since 0.0.1
 */
public class ZookeeperConnector {

    //默认连接地址，集群用','分隔
    public static final String DEFAULT_CONNECT_STRING = "192.168.2.210:2181";

    //会话过期时间
    public static final int SESSION_TIMEOUT = 5000;

    //连接时间
    public static final int CONNECT_TIME_OUT = 5000;

    /**
     * 已定义连接池，core和max的大小一致，保证高效，增加了线程的保留时间
     * 使用LinkedBlockingQueue来进行存储，最大值为Interger的最大值，阻塞队列。
     */
    public static ExecutorService executorService =
            new ThreadPoolExecutor(5, 5,
                    2000L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>());


    public static ZooKeeper getZookeeper(Watcher watcher) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        return getZooKeeper(DEFAULT_CONNECT_STRING, SESSION_TIMEOUT, watcher);
    }

    public static ZooKeeper getZookeeper(String connectString, Watcher watcher) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        return getZooKeeper(connectString, SESSION_TIMEOUT, watcher);
    }

    /**
     * 通过Future来做连接超时
     * @param connectString
     * @param sessionTimeout
     * @param watcher
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static ZooKeeper getZooKeeper(final String connectString, final int sessionTimeout,final Watcher watcher) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        
        //这里必须要先初始化ZooKeeper，初始化需要一定的时间，这个时间不能算作超时时间的一部分
        ZooKeeper zk  = new ZooKeeper(connectString,sessionTimeout,watcher);
        //通过Callable的回调来得知ZooKeeper的连接状态
        Future<ZooKeeper> future = executorService.submit(()-> {
        //死循环，去check ZooKeeper的连接状态
         while (true){
             ZooKeeper.States states = zk.getState();
             if (states.isConnected()){
                 return zk;
            }
         }});
        //阻塞状态，超时将抛出TimeoutException
        return future.get(CONNECT_TIME_OUT,TimeUnit.MILLISECONDS);
    }
}

```
## 来看看ZkClient的解决方案
```
public void connect(final long maxMsToWaitUntilConnected, Watcher watcher) throws ZkInterruptedException, ZkTimeoutException, IllegalStateException {
        boolean started = false;
        //获取锁（排它锁）
        acquireEventLock();
        try {
            setShutdownTrigger(false);
            _eventThread = new ZkEventThread(_connection.getServers());
            _eventThread.start();
            _connection.connect(watcher);

            LOG.debug("Awaiting connection to Zookeeper server");
            //同步获取连接
            boolean waitSuccessful = waitUntilConnected(maxMsToWaitUntilConnected, TimeUnit.MILLISECONDS);
            //这里就是超时
            if (!waitSuccessful) {
                throw new ZkTimeoutException("Unable to connect to zookeeper server '" + _connection.getServers() + "' with timeout of " + maxMsToWaitUntilConnected + " ms");
            }
            started = true;
        } finally {
        //释放锁
            getEventLock().unlock();

            // we should close the zookeeper instance, otherwise it would keep
            // on trying to connect
            //回收资源
            if (!started) {
                close();
            }
        }
    }
```
> 不难看出，ZkClient在做连接处理的时候，也做了超时防护。
