package com.yzz.thread.readWrite;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/16
 *
 * @Since 0.0.1
 */
public class SynchronizedWRTest {

    static class Student {
        int count = 0;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return count + "==" + Thread.currentThread().getName();
        }
    }

    static class ReadThread extends Thread {
        private SynchronizerRW<Student> studentSynchronizerRW;

        public ReadThread(SynchronizerRW<Student> studentSynchronizerRW) {
            this.studentSynchronizerRW = studentSynchronizerRW;
        }

        @Override
        public void run() {
            System.out.println("读线程程：" + studentSynchronizerRW.readLock());
            studentSynchronizerRW.readUnLock();
        }
    }

    static class WriteThread extends Thread {
        private SynchronizerRW<Student> studentSynchronizerRW;

        public WriteThread(SynchronizerRW<Student> studentSynchronizerRW) {
            this.studentSynchronizerRW = studentSynchronizerRW;
        }

        @Override
        public void run() {
            studentSynchronizerRW.writeLock(student -> {
                System.out.println("写线程写开始：" + student);
                student.count++;
                System.out.println("写线程写结束：" + student);
            });
        }
    }

    public static void main(String[] args) {
        SynchronizerRW<Student> studentSynchronizerRW = new SynchronizerRW<>(new Student());
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                Thread t = new ReadThread(studentSynchronizerRW);
                t.start();
            } else {
                Thread t = new WriteThread(studentSynchronizerRW);
                t.start();
            }
        }
    }
}
