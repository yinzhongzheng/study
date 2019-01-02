package com.yzz.masterchose;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * ctrl + alt + a 清除import
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/26
 *
 * @Since 0.0.1
 */
public class ZookeeperConnector {

    //默认连接地址，集群用','分隔
    public static final String DEFAULT_CONNECT_STRING = "192.168.43.210:2181";

    public static final int SESSION_TIMEOUT = 100;

    public static final int CONNECT_TIME_OUT = 2000;

    /**
     * 已定义连接池
     */
    public static ExecutorService executorService =
            new ThreadPoolExecutor(5, 5,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>());


    public static ZooKeeper getZookeeper(Watcher watcher) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        return getZooKeeper(DEFAULT_CONNECT_STRING, SESSION_TIMEOUT, watcher);
    }

    public static ZooKeeper getZookeeper(String connectString, Watcher watcher) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        return getZooKeeper(connectString, SESSION_TIMEOUT, watcher);
    }

    /**
     * 通过Future来做连接超时
     *
     * @param connectString
     * @param sessionTimeout
     * @param watcher
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static ZooKeeper getZooKeeper(final String connectString, final int sessionTimeout, final Watcher watcher) throws IOException, InterruptedException, ExecutionException, TimeoutException {

        //通过Callable的回调来得知ZooKeeper的连接状态
        //这里必须要先初始化ZooKeeper，初始化需要一定的时间，这个时间不能算作超时时间的一部分
        ZooKeeper zk = new ZooKeeper(connectString, sessionTimeout, watcher);

        Future<ZooKeeper> future = executorService.submit(() -> {
            //死循环，去check ZooKeeper的连接状态
            while (true) {
                ZooKeeper.States states = zk.getState();
                if (states.isConnected()) {
                    return zk;
                }
            }
        });
        //阻塞状态，超时将抛出TimeoutException
        return future.get(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS);
    }

}
