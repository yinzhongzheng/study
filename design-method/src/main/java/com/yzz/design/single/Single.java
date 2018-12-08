package com.yzz.design.single;

/**
 * author:yzz
 * date:2018/12/2
 * E-mail:yzzstyle@163.com
 * com.yzz.design.single
 * 注释: 单利（懒汉式）
 */
public class Single {

    private String a;

    private Single(){};

    private static class Lazyer{
        private final static Single s = new Single();
    }

    public static final Single getInstance(){
        return Lazyer.s;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
