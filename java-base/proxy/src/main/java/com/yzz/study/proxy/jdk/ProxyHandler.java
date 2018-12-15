package com.yzz.study.proxy.jdk;

import javafx.beans.Observable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * author:yzz
 * date:2018/12/1
 * E-mail:yzzstyle@163.com
 * com.yzz.study.proxy.jdk
 * 注释:
 */
public class ProxyHandler implements InvocationHandler {
    private ILog log;

    public void test(){
        log = (ILog) Proxy.newProxyInstance(ILog.class.getClassLoader(),new Class[]{ILog.class},this);
        log.printLog("test log");
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始");
        System.out.println(proxy.getClass());
        //实例
        //method.invoke(log,args);
        System.out.println("结束");
        return null;
    }
}
