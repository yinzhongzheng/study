package com.yzz.test;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2019/1/2
 *
 * @Since 0.0.1
 */
public interface ITestZk {

    ZooKeeper createZookeeperConnByDefault() throws IOException;

    ZooKeeper createZookeeperConnBySession() throws IOException, Exception;

    void addZNode() throws Exception;

    void deleteZNode() throws Exception;

    void updateZNode() throws Exception;

    void searchZNode() throws Exception;

}
