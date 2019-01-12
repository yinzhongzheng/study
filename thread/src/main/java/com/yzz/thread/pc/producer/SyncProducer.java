package com.yzz.thread.pc.producer;

import java.util.Deque;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/19
 *
 * @Since 0.0.1
 */
public class SyncProducer<T extends Deque> implements IProducer<T> {

    @Override
    public void produce(T t) {
        synchronized (t) {
            try {
                if (t.size() > MIN_NUM) {
                    t.wait();
                }
                if (t.size() > MIN_NUM) return;
                //生产
                String p = "产品：" + t.size() + 1;
                final boolean add = t.add(p);
                System.out.println(p + " 当前数量" + t.size() + add);
                t.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                t.notifyAll();
            }
        }
    }
}
