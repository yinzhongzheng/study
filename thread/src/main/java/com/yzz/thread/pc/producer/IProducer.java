package com.yzz.thread.pc.producer;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/19
 *
 * @Since 0.0.1
 */
public interface IProducer<T> {
    int MIN_NUM = 0;

    void produce(T t);
}
