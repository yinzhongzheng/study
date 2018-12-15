package com.yzz.study.proxy.custom;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * author:yzz
 * date:2018/12/2
 * E-mail:yzzstyle@163.com
 * com.yzz.study.proxy.custom
 * 注释:
 */
public class Test {

    public static void main(String[] args) {
        try {
            ILog log = Proxy.newInstance(new MyClassLoader(), ILog.class, new InvocationHandler() {
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("==================");
                    System.out.println(method.getName());
                    return null;
                }
            });
            System.out.println(log);
            log.printLog("1","2");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
