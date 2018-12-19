package com.yzz.thread.pc.consumer;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/19
 *
 * @Since 0.0.1
 */
public interface IConsumer<T> {
    void consume(T t);
}
