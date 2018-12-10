package com.yzz.thread.test;

/**
 * describe: 这里演示了 多线程不可见问题  高速缓存（工作内存） 持有共享变量的一份拷贝
 * 不会及时的将结果store到主内存
 * 但是一定会有store指令的发生，只是不能及时刷新
 * E-mail:yzzstyle@163.com  date:2018/12/8
 *
 * @Since 0.0.1
 */
public class VolatileUnSafe {

    static class ThreadUnSafe implements Runnable{

        boolean flag = true;

        public void run() {
            while (flag){
                System.out.println(11);
            }
        }
    }

    public static void main(String[] args) {
        ThreadUnSafe safe = new ThreadUnSafe();
        Thread t = new Thread(safe);
        Thread t2 = new Thread(safe);
        t.start();
        t2.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        safe.flag = false;
        System.out.println("==========================");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
