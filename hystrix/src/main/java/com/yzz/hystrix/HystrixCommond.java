package com.yzz.hystrix;

import com.netflix.hystrix.*;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/21
 *
 * @Since 0.0.1
 */
public class HystrixCommond extends HystrixCommand<String> {
    protected HystrixCommond() {
        super(getSetter());
    }

    protected String run() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return "使用hystrix";
    }

    public static Setter getSetter() {
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("GroupKey-test");
        Setter setter = Setter.withGroupKey(groupKey);

        HystrixCommandProperties.Setter commandPropertiesSetter = HystrixCommandProperties.Setter();
        commandPropertiesSetter.withExecutionTimeoutEnabled(false)
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD);

        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("testCommandKey");

        HystrixThreadPoolKey poolKey = HystrixThreadPoolKey.Factory.asKey("poolTest");

        HystrixThreadPoolProperties.Setter hystrixPoolSetter = HystrixThreadPoolProperties.Setter();
        hystrixPoolSetter.withCoreSize(10)
                .withMaxQueueSize(100);

        setter.andCommandPropertiesDefaults(commandPropertiesSetter)
                .andCommandKey(commandKey)
                .andThreadPoolKey(poolKey)
                .andThreadPoolPropertiesDefaults(hystrixPoolSetter);
        return setter;
    }

}
