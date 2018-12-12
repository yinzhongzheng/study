package com.yzz.thread.test;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/12
 *
 * @Since 0.0.1
 */
public class TestDaemon {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    //Thread.sleep(1000);
                    System.out.println("工作线程"+Thread.currentThread().isInterrupted());
                    System.out.println(Thread.interrupted());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(()->{
                try {
                    Thread.sleep(3000);
                    System.out.println("Daemon线程");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });

        t2.setDaemon(true);
        t1.start();
        t2.start();
        t1.interrupt();
        t2.interrupt();
        System.out.println("主线程结束");
    }
}
