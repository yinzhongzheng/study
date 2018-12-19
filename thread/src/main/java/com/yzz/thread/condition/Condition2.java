package com.yzz.thread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：Condition描述了两种状态，
 * 实现的功能：
 * 当count=0的时候，不允许remove操作
 * 当count==数组length的时候，不允许add操作
 * Condition上有两个状态 notFull notEmpty
 * double check locking
 * wait被唤醒后，再次判断wait条件
 * 1.if(条件)wait if(条件)return;
 * 2.where(条件)wait
 * 方法1和2都实现了double-check的功能，但是方法2更简洁
 * @Emile yzzstyle@163.com
 * @since 0.0.1
 */
public class Condition2<T> {
    private Object[] objects;
    private int count, index, removeIndex;

    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public Condition2(int size) {
        objects = new Object[size];
    }

    public void add(T t) {
        try {
            lock.lock();
            while (count == objects.length){
            //if (count == objects.length) {
                //数组已经满了
                System.out.println(Thread.currentThread().getName() + "==wait" + count);
                notFull.await();
            }
            //if (count == objects.length)return;

            //没有满就急需装
            objects[index] = t;
            //这里要判断index，防止outofindex
            if (++index == objects.length) {
                index = 0;
            }
            count++;
            notEmpty.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public T remove() {
        Object object = null;
        try {
            lock.lock();
            while (count == 0){
            //if (count == 0) {
                //空的，不能移除了
                System.out.println(Thread.currentThread().getName() + "==wait" + count);
                notEmpty.await();
            }
            //if (count == 0) return object;
            object = objects[removeIndex];
            if (count == 0)return null;
            count--;
            if (++removeIndex == objects.length) {
                removeIndex = 0;
            }
            notFull.signal();
        } catch (Exception e) {
        }finally {
            lock.unlock();
        }
        return (T) object;
    }

    static class T1 extends Thread {
        Condition2 condition;

        public T1(Condition2 condition) {
            this.condition = condition;
        }

        @Override
        public void run() {
            while (true) {
                condition.add(new Object());
            }
        }
    }

    static class T2 extends Thread {
        Condition2 condition;

        public T2(Condition2 condition) {
            this.condition = condition;
        }

        @Override
        public void run() {
            while (true) {
                condition.remove();
            }
        }
    }

    public static void main(String[] args) {
        Condition2 condition = new Condition2(10);
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                Thread t1 = new T1(condition);
                t1.start();
            } else {
                Thread t1 = new T2(condition);
                t1.start();
            }
        }

    }
}
