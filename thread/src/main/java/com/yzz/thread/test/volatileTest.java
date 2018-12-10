package com.yzz.thread.test;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/10
 *
 * @Since 0.0.1
 */
public class volatileTest {

    private volatile static Bean bean = new Bean();

    public static class Bean{
        int count;
        boolean flag = true;

        void add(){
            count++;
        }
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while(bean.flag) {
                        bean.add();
                    System.out.println(bean.flag);
                }
                System.out.println("结束了");
            }
        });

        t1.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bean.flag = false;
        try {
            Thread.sleep(5000);
        }catch (Exception e){

        }
        System.out.println("==");

    }
}
