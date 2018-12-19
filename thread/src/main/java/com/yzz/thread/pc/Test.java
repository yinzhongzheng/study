package com.yzz.thread.pc;

import com.yzz.thread.pc.center.Center;
import com.yzz.thread.pc.center.ICenter;
import com.yzz.thread.pc.consumer.IConsumer;
import com.yzz.thread.pc.consumer.SingleConsumer;
import com.yzz.thread.pc.consumer.SynConsumer;
import com.yzz.thread.pc.producer.IProducer;
import com.yzz.thread.pc.producer.SingleProducer;
import com.yzz.thread.pc.producer.SyncProducer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/19
 *
 * @Since 0.0.1
 */
public class Test {

    public static void run(ICenter center) {
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    center.produce();
                }
            });
            thread.start();
        }

        for (int i = 0; i < 30; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    center.consume();
                }
            });
            thread.start();
        }
    }

    public static void syncPc() {
        LinkedList<String> linkedList = new LinkedList<>();
        IProducer<LinkedList<String>> producer = new SyncProducer<>();
        IConsumer<LinkedList<String>> consumer = new SynConsumer<>();
        ICenter center = new Center<>(linkedList, producer, consumer);
        run(center);
    }

    public static void syncSingle() {
        //公平锁
        Lock lock = new ReentrantLock(false);
        Condition conditionForTake = lock.newCondition();
        Condition conditionForPut = lock.newCondition();
        LinkedList<String> linkedList = new LinkedList<>();
        IProducer<LinkedList<String>> producer = new SingleProducer<>(lock, conditionForTake, conditionForPut);
        IConsumer<LinkedList<String>> consumer = new SingleConsumer<>(lock, conditionForTake, conditionForPut);
        ICenter center = new Center<>(linkedList, producer, consumer);
        run(center);
    }

    public static void main(String[] args) {
        syncPc();
        //syncSingle();
    }
}
