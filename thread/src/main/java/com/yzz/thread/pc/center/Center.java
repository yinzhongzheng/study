package com.yzz.thread.pc.center;

import com.yzz.thread.pc.consumer.IConsumer;
import com.yzz.thread.pc.producer.IProducer;

/**
 * describe: 生产者与消费者
 * E-mail:yzzstyle@163.com  date:2018/12/19
 *
 * @Since 0.0.1
 */

public class Center<T> implements ICenter {
    private T sharing;

    private IProducer producer;

    private IConsumer consumer;

    public Center(T sharing, IProducer producer, IConsumer consumer) {
        this.sharing = sharing;
        this.producer = producer;
        this.consumer = consumer;
    }

    @Override
    public void produce() {
        producer.produce(sharing);
    }

    @Override
    public void consume() {
        consumer.consume(sharing);
    }

}


