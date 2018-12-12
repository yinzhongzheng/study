package com.yzz.thread.test;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/12
 *
 * @Since 0.0.1
 */
public class FinalTest {
    private final int a;
    private final  String b;

    public FinalTest(int a,String b) {
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) {
        FinalTest test = new FinalTest(1,"3");


    }
}
