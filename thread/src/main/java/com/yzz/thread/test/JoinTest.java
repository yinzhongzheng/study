package com.yzz.thread.test;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/13
 *
 * @Since 0.0.1
 */
public class JoinTest {

    static class T extends Thread{
        Thread thread;

        public T(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            if (thread!=null){
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        T t1 = new T(null);
        T t2 = new T(t1);
        T t3 = new T(t2);
        t1.start();
        t2.start();
        t3.start();
        try {
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束");
    }
}
