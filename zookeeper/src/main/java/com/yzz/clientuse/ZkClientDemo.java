package com.yzz.clientuse;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2019/1/1
 *
 * @Since 0.0.1
 */
public class ZkClientDemo extends ZkMarshallingError {

    public static final String DEFAULT_CONNECT_STRING = "192.168.1.211:2181,112.168.1.211:2181";

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient(DEFAULT_CONNECT_STRING, 2000);
    }
}
