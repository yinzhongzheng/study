package com.yzz.thread.pc.producer;

import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/19
 *
 * @Since 0.0.1
 */
public class SingleProducer<T extends Deque> implements IProducer<T> {

    private Lock lock;
    private Condition conditionForTake;
    private Condition conditionForPut;

    public SingleProducer(Lock lock, Condition conditionForTake, Condition conditionForPut) {
        this.lock = lock;
        this.conditionForTake = conditionForTake;
        this.conditionForPut = conditionForPut;
    }

    @Override
    public void produce(T t) {
        lock.lock();
        try {
            if (t.size() > MIN_NUM) {
                conditionForPut.await();
            }
            if (t.size() > MIN_NUM) return;
            //生产
            String p = "产品：" + t.size() + 1;
            t.add(p);
            conditionForTake.signal();
            System.out.println(p + " 当前数量" + t.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
