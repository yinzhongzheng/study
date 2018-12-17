package com.yzz.thread.aqs;

import java.util.concurrent.Semaphore;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/17
 *
 * @Since 0.0.1
 */
public class SemaphoreTest {
    //信号量的permits
    private final int maxCount;
    private final Semaphore semaphore;

    public SemaphoreTest(int maxCount) {
        this.maxCount = maxCount;
        //默认是nofair
        this.semaphore = new Semaphore(maxCount);
    }

    public void add() {
        try {
            //获取到信号量
            semaphore.acquire();
            //操作
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }


}

class A extends Thread {
    SemaphoreTest semaphoreTest;

    public A(SemaphoreTest semaphoreTest) {
        this.semaphoreTest = semaphoreTest;
    }

    @Override
    public void run() {
        semaphoreTest.add();
        System.out.println("add：");
    }
}



class Test {
    public static void main(String[] args) {
        SemaphoreTest semaphoreTest = new SemaphoreTest(5);
        for (int i = 0; i < 10; i++) {
            A a = new A(semaphoreTest);
            a.start();
        }
    }
}
