package com.yzz.thread.test;

/**
 * describe: 这里展示不安全的多线程操作
 * 多线程的三个原则
 * 1. 原子性 synchronized 同一时刻只允许一个线程执行
 * 2. 可见性 volatile 1.保证多线程下，共享变量是可见的 2.一定程度上保证了不冲排序
 *     为何是一定程度？
 *          1.自身的修改操作，是禁止重排序的
 *          2.在它前面执行的和在它后面执行的，这个顺序补课改变
 *          例如：
 *          findA()   1
 *          findB()   2
 *          a = 1;    3 //validate修饰的
 *          findC();  4
 *          执行顺序必须是：
 *             1 2 3 4
 *             2 1 3 4
 *          4不可能在3之前先执行。
 *          但是validate不能保证原子性（它是负责具体流程的,没课流程是可以被暂停的）
 *
 * 3. 有序性：体现在通过某一个状态来决定程序的执行，当程序状态和其他方法被重新排序后，可能会导致，应该先执行的
 * 操作，反而没有执行。
 * 例如
 * Thread 1
 *      init(); 1
 *      tag = true; 2
 *
 * Thread 2
 *      if(tag){
 *        load()
 *      };
 *最终执行顺序可能是  2-->1
 *
 * 那么Thread2的load方法就会出现问题，因为并没有执行初始化方法
 *
 * E-mail:yzzstyle@163.com  date:2018/12/8
 *
 * @Since 0.0.1
 */
public class Safe {
    public static class TicketTask implements Runnable{

        private Integer tickets = 100;

        public volatile boolean flag = true;

        public void run() {
            while (tickets>0){
                try {
                    Thread.sleep(10);//这里的时间是为了更好模拟线程不安全加上去的，增加cpu切换。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (flag){
                    sale();
                }else {
                    slaleblock();
                }
            }
        }

        /**
         * synchronized 虽然持有this锁,但是在获取锁之前，是不同步的，其实也不需要同步
         * 当tickets == 1的时候
         * 当t1线程释放this锁之前，t2线程进入了run()方法，并且会通过验证，等待获取this锁
         * 去执行sale方法，那么这时候就会出现重复买票的情况。
         * t1 100
         * t2 101
         */
        public synchronized void sale(){
            //抢票
                if (tickets>0) {//double check
                    System.out.println(Thread.currentThread().getName() + ":抢票第" + (100 - tickets + 1) + "张");
                    tickets--;
                }
        }

        /**
         * synchronized 代码块
         * 推荐，但是要注意范式和共享变量相关的操作都要放在里面
         */
        public void slaleblock(){
            synchronized (tickets){
                if (tickets>0) {
                    System.out.println(Thread.currentThread().getName()+"代码块" + ":抢票第" + (100 - tickets + 1) + "张");
                    tickets--;
                }
            }
        }

        public static void main(String[] args) {
            TicketTask ticketTask = new TicketTask();
            Thread t1 = new Thread(ticketTask);
            Thread t2 = new Thread(ticketTask);
            t1.start();
            try {
                Thread.sleep(500);
                ticketTask.flag = false;
                System.out.println("已经设置");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t2.start();
        }
    }
}
