package com.yzz.thread.readWrite;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/16
 * 读：readLock readUnLock 两个方法区辅助，更新writeState
 * 写 writeLock 同步target资源，防止多个写操作target，写操作更新readState的状态
 * @Since 0.0.1
 */
public class SynchronizerRW<T> {

    //读的状态，0可读，非0不可读
    private volatile int readState = 0;
    //写的状态，0可写，非0不可写
    private volatile int writeState = 0;
    //操作共享目标，线程不安全的起源
    private T target;

    //java CAS操作类
    private static final Unsafe unsafe;
    //读写state的偏移量
    private static final long readStateOffSet;
    private static final long writeStateOffSet;

    /**
     * Unsafe的实例化，不能通过getUnsafe()方法来获取，因为，classLoader不一样，不被Unsafe信任。
     * 必须通过范式来拿到Unsafe对象
     */
    static {
        try {
            //反射操作
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            //设置偏移量
            readStateOffSet = unsafe.objectFieldOffset
                    (SynchronizerRW.class.getDeclaredField("readState"));
            writeStateOffSet = unsafe.objectFieldOffset
                    (SynchronizerRW.class.getDeclaredField("writeState"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    /**
     * 初始化共享变量
     * @param target
     */
    public SynchronizerRW(T target) {
        if (null == target) throw new IllegalArgumentException("target must not be null !");
        this.target = target;
    }

    /**
     * CAS 设置读取state的状态
     * @param expect
     * @param update
     * @return
     */
    private boolean compareAndSetReadState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, readStateOffSet, expect, update);
    }

    /**
     * CAS设置写state的状态
     * @param expect
     * @param update
     * @return
     */
    private boolean compareAndSetWriteState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, writeStateOffSet, expect, update);
    }

    /**
     * 读线程获取读的权限
     * 自旋+CAS
     * readState=0 的前提下，多线程同事写的话，会出现线程不安全(原子性、可见性、有序性)，通过CAS+volatile可以解决
     * @return
     */
    public T readLock() {
        for (; ; ) {
            //可读
            if (readState == 0 && compareAndSetWriteState(writeState, writeState + 1)) {
                //这里可以返回
                return target;
            }
        }
    }

    /**
     * 与readLock成对出现
     */
    public void readUnLock() {
        for (; ; ) {
            if (compareAndSetWriteState(writeState, writeState - 1)) {
                return;
            }
        }
    }

    /**
     * writeState=0前提下，去CAS修改readState，修改成功后就去获取target的monitor
     * @param write
     */
    public void writeLock(Write<T> write) {
        for (; ; ) {
            if (writeState == 0 && compareAndSetReadState(readState, readState + 1)) {
                //可以去争夺锁
                break;
            }
        }
        //防止多个读线程操作共享变量
        synchronized (target) {
            //写操作
            write.modify(target);
            readState--;
        }
    }

    /**
     * 写操作的回调
     * @param <T>
     */
    public interface Write<T> {
        void modify(T t);
    }
}
