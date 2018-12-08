package com.yzz.design.single;

/**
 * author:yzz
 * date:2018/12/2
 * E-mail:yzzstyle@163.com
 * com.yzz.design.single
 * 注释:
 */
public class Test {

    public static void main(String[] args) {
        Single a = Single.getInstance();
        a.setA("cccc");
        System.out.println(Single.getInstance().getA());
    }

}
