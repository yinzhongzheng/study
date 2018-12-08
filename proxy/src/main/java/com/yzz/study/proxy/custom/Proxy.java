package com.yzz.study.proxy.custom;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * author:yzz
 * date:2018/12/1
 * E-mail:yzzstyle@163.com
 * com.yzz.study.proxy.custom
 * {@link java.lang.reflect.Proxy}
 */
public class Proxy {

    /**
     * 缓存 {@link java.lang.reflect.Proxy}
     */
    private static Map<String, Class> cachedProxyClassInstaceMap = new WeakHashMap<String, Class>();

    protected InvocationHandler h;

    private Proxy(){

    }

    protected Proxy(InvocationHandler h){
        this.h = h;
    }

    /**
     * {@link java.lang.reflect.Proxy}
     * 这里实现比较简单的   Class<T>[] interfaces --> Class<T> ince
     * @param loader
     * @param ince
     * @param h
     * @param <T>
     * @return
     */
    public static <T> T newInstance(MyClassLoader loader,
                                   Class<T> ince,
                                   InvocationHandler h) throws Exception {

        String className = "$proxy"+cachedProxyClassInstaceMap.size()+1;
        //1.从cache里查找
        Class targetClass = cachedProxyClassInstaceMap.get(ince.getName());
        if (null != targetClass){
            return (T) targetClass;
        }
        //2.创建java file
        String javaFilePth = ProxyGenerator.createProxyJavaFile(loader,className,ince);
        //3.编译 java file
        CompileJavaCode.compile(javaFilePth);
        //4.load class 文件
        Class<T> _class = (Class<T>) loader.findClass(className);
        //这里加入InvocationHandler实例
        Constructor constructor = _class.getConstructor(InvocationHandler.class);
        return (T) constructor.newInstance(h);
    }






}
