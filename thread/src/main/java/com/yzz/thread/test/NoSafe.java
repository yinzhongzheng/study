package com.yzz.thread.test;

/**
 * describe: 模拟抢票
 * E-mail:yzzstyle@163.com  date:2018/12/8
 *
 * @Since 0.0.1
 */
public class NoSafe {

    public static class TicketTask implements Runnable{

        private int tickets = 100;

        public void run() {
            //抢票
            while (tickets > 0){
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+":抢票第"+(100-tickets+1)+"张");
                tickets --;
            }
        }
    }

    public static void main(String[] args) {
        TicketTask ticketTask = new TicketTask();
        Thread t1 = new Thread(ticketTask);
        Thread t2 = new Thread(ticketTask);
        t1.start();
        t2.start();
    }
}
