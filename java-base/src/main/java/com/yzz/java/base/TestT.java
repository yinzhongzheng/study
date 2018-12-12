package com.yzz.java.base;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/11
 *
 * @Since 0.0.1
 */
public class TestT<T> {

    public T[] t;

    public void setT(T[] t){
        this.t = t;
    }

    public void add(T t){

    }

    public static void main(String[] args) {
        TestT<Integer> t = new TestT();
        Integer[] a = new Integer[10];
        t.setT(a);
        System.out.println(t.t.length);
        try {
            Method[] m = t.getClass().getMethods();
            for(Method mm : m){
                Class<?>[] ps = mm.getParameterTypes();
                if (ps.length>0 && mm.getName().equals("add")) {
                    System.out.println(ps[0]);
                    mm.invoke(t,"3");
                }
            }
            System.out.println(t.t.length);
            for (Object o:t.t){
                System.out.println(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
