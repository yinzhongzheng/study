package com.yzz.hystrix;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/21
 *
 * @Since 0.0.1
 */
public class Test {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newWorkStealingPool(1);

        int i = 0;
        int j = 0;
        for (; ; ) {
            i++;
            retry:
            for (; ; ) {
                j++;
                while (j==2){
                    break retry;
                }
                while (i==2){
                    continue retry;
                }
                System.out.println(111);
            }
        }
    }

    public String get() {
        Future<String> future = Executors.newSingleThreadExecutor().submit(new Runnable() {
            public void run() {

            }
        }, "1");
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }
}
