package com.yzz.thread.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/21
 *
 * @Since 0.0.1
 */
public class ForkJoinTest {

    static class Task extends RecursiveTask<Integer> {
        final int MAX = 2;
        final int start;
        final int end;

        public Task(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            boolean canDo = (end - start) <= 3;
            int sum = 0;
            if (canDo) {
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                System.out.println(start + "==" + end);
                int middle = (end + start) / 2;
                Task first = new Task(start, middle);
                Task second = new Task(middle + 1, end);
                first.fork();
                second.fork();
                int a = first.join();
                int b = second.join();
                sum = a+b;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(new Task(1, 8));
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
