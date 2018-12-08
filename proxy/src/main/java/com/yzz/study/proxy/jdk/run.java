package com.yzz.study.proxy.jdk;

/**
 * author:yzz
 * date:2018/12/1
 * E-mail:yzzstyle@163.com
 * com.yzz.study.proxy.jdk
 * 注释:
 */
public class run {

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ProxyHandler proxyHandler = new ProxyHandler();
        proxyHandler.test();
    }
}
