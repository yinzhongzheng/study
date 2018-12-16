package com.yzz.thread.readWrite;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/16
 *
 * @Since 0.0.1
 */
public class ReentrantReadWriteLockTest {

    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = reentrantReadWriteLock.readLock();
    static Lock writeLock = reentrantReadWriteLock.writeLock();

    static class R extends Thread {
        @Override
        public void run() {
            readLock.lock();
            System.out.println("读"+Thread.currentThread().getName());
            readLock.unlock();
        }
    }

    static class W extends Thread {
        @Override
        public void run() {
            writeLock.lock();
            System.out.println("写"+Thread.currentThread().getName());
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            if (i%2 == 0){
                Thread r = new R();
                r.start();
            }else {
                Thread w = new W();
                w.start();
            }
        }
    }
}
