package com.yzz.thread.test;

import java.util.List;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/12
 *
 * @Since 0.0.1
 */
public class ProducerAndConsumer {
    private volatile Res target;
    private volatile int count = 0;
    private volatile boolean flag = false;

    public Res getTarget() {
        return target;
    }

    public void setTarget(Res target) {
        this.target = target;
    }

    class Res {
        String name = "yzz";
        String age = "10";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

    class Producer implements Runnable{

        public void create(){
            while (true){
                    synchronized (target){
                        System.out.println("获取对象锁");
                        if (flag){
                            try {
                                System.out.println("wite");
                                target.wait();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("continue");
                        count++;
                        flag = true;
                        System.out.println("生产了"+count);
                        target.notify();
                    }
            }
        }

        @Override
        public void run() {
            create();
        }
    }

    class Consumer implements Runnable{

        public void consum(){
            while (true){
                    synchronized (target){
                        if (!flag) {
                            try {
                                target.wait();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("取到第" + count);
                        flag = false;
                        target.notify();
                    }
            }
        }

        @Override
        public void run() {
            consum();
        }
    }

    public static void main(String[] args) {
        ProducerAndConsumer producerAndConsumer = new ProducerAndConsumer();
        producerAndConsumer.setTarget(producerAndConsumer.new Res());
        Producer producer = producerAndConsumer.new Producer();
        Consumer consumer = producerAndConsumer.new Consumer();
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        Thread t3 = new Thread(producer);
        t1.start();
        t2.start();
        //t3.start();

    }
}
