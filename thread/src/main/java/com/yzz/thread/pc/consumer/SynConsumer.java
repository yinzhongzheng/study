package com.yzz.thread.pc.consumer;

import java.util.Deque;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/19
 *
 * @Since 0.0.1
 */
public class SynConsumer<T extends Deque> implements IConsumer<T> {

    @Override
    public void consume(T t) {
        synchronized (t) {
            try {
                if (t.size() == 0) {
                    t.wait();
                }
                if (t.size() == 0) return;
                //消费
                Object produce = t.pollFirst();
                System.out.println("消费：" + produce + " 当前数量" + t.size());
                t.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
                t.notifyAll();
            }
        }
    }
}
