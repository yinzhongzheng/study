package com.yzz.thread.readWrite;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/16
 *
 * @Since 0.0.1
 */
public class ConditionWaitAndNotify {
    //排它锁
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private Object[] ts;

    private int index;

    public ConditionWaitAndNotify(int count) {
        if (count <= 0) throw new IllegalArgumentException("count must not be null");
        ts = new Object[count];
    }

    public void add() {
        try {
            lock.lock();
            //one check
            if (index == ts.length) {
                condition.await();
            }
            //double check
            if (index == ts.length) {
                return;
            }
            ts[index] = new Object();
            index++;
            System.out.println("add" + index);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void remove() {
        try {
            lock.lock();
            //one check
            if (index < ts.length) {
                condition.await();
            }
            //double check
            if (index < ts.length){
                return;
            }
            index = 0;
            System.out.println("remove" + index);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    static Thread createAdd(ConditionWaitAndNotify conditionWaitAndNotify){
        return new Thread(() -> {
            while (true){
                conditionWaitAndNotify.add();
                try {
                    //Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    static Thread createRemove(ConditionWaitAndNotify conditionWaitAndNotify){
        return new Thread(() -> {
            while (true) {
                conditionWaitAndNotify.remove();
                try {
                    //Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static void main(String[] args) {
        ConditionWaitAndNotify conditionWaitAndNotify = new ConditionWaitAndNotify(3);
        Thread t1 = createAdd(conditionWaitAndNotify);
        Thread t11 = createAdd(conditionWaitAndNotify);
        Thread t2 = createRemove(conditionWaitAndNotify);
        Thread t22 = createRemove(conditionWaitAndNotify);
        t1.start();
        t11.start();
        t2.start();
        t22.start();
    }
}
