package com.yzz.thread.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/16
 *
 * @Since 0.0.1
 */
public class ReentRantLockTest {

    //默认构造是非公平
    //true是公平锁
    //false是非公平锁
    public static final Lock lock = new ReentrantLock(false);

    public static class T extends Thread {
        public T(String name){
            super(name);
        }
        @Override
        public void run() {
            lock.lock();
            System.out.println("==" + Thread.currentThread().getName());
            try {
                //Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            T t = new T(String.valueOf(i+"Thread"));
            t.start();
        }
        System.out.println(null == null);
    }
}
