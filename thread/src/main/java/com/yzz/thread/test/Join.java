package com.yzz.thread.test;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/8
 *
 * @Since 0.0.1
 */
public class Join {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 3; i++) {
                    System.out.println(i);
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        });
        t1.start();
        System.out.println("main1");
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main2");

    }
}
