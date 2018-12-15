package com.yzz.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/15
 *
 * @Since 0.0.1
 */
public class TwoLockAllow implements Lock {

    private final static Sync sync = new Sync(2);

    /**
     * 实现做多两个线程共享资源
     * lock和unlock操作都是阻塞的
     */
    private static class Sync extends AbstractQueuedSynchronizer {

        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must large 0");
            }
            setState(count);
        }

        /**
         * compareAndSwapInt java通过调用c代码来操作内存，原子操作去更改该值
         *
         * 获取资源，<0 进入队列中等待
         * compareAndSetState()
         * compareAndSwapInt(Object var1, long var2, int var4, int var5)
         * var1 : 修改的字段锁处于的对象
         * var2 : stateOffset = unsafe.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("state"));
         *       int值得偏移量
         * var4 : 当前值也是期望值
         * var5 : 更新后的值
         *
         * ===================实现逻辑=========================
         * public final void acquireShared(int arg) {
         *   if (tryAcquireShared(arg) < 0)
         *      doAcquireShared(arg);
         * }
         * 我们可以看出来，当tryAcquireShared的返回值小于0的时候，将会将该线程入队操作。
         * 这里是共享锁，我们通过重写tryAcquireShared方法，来控制获取锁的规则。
         *
         * 当当前状态锁的state小于0的时候，后续的线程将入队，否则直接得到锁资源。
         *
         * 后面的获取锁操作都会调用用户定义的tryAcquireShared方法
         * @param arg
         * @return
         */
        @Override
        protected int tryAcquireShared(int arg) {
            for (;;){
                int currentCount = getState();
                int newCount = currentCount - arg;
                if (newCount < 0 || compareAndSetState(currentCount,newCount)){
                    return newCount;
                }
            }

        }

        /**
         * 这里是释放资源，更改state的状态
         * @param arg
         * @return
         */
        @Override
        protected boolean tryReleaseShared(int arg) {

            for (;;){
                int currentCount = getState();
                int newCount = currentCount + arg;
                if (compareAndSetState(currentCount,newCount)){
                    return true;
                }
            }
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

}

class test {
    public static final Lock lock = new TwoLockAllow();

    static class T extends Thread {
        @Override
        public void run() {
            lock.lock();
            System.out.println("==" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            T t = new T();
            t.start();
        }
    }
}
