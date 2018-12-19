package com.yzz.thread.pc.consumer;

import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/19
 *
 * @Since 0.0.1
 */
public class SingleConsumer<T extends Deque> implements IConsumer<T> {

    private Lock lock;
    private Condition conditionForTake;
    private Condition conditionForPut;

    public SingleConsumer(Lock lock, Condition conditionForTake, Condition conditionForPut) {
        this.lock = lock;
        this.conditionForTake = conditionForTake;
        this.conditionForPut = conditionForPut;
    }

    @Override
    public void consume(T t) {
        lock.lock();
        try {
            //if (t.size() == 0){
            while (t.size() == 0) {
                conditionForTake.await();
            }
            //if (t.size() == 0) return;
            Object produce = t.pollFirst();
            System.out.println("消费：" + produce + " 当前数量" + t.size());
            conditionForPut.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
