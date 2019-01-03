package com.yzz;

import java.util.ServiceLoader;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2019/1/2
 *
 * @Since 0.0.1
 */

public class Test {

    public static void main(String[] args) {
        ServiceLoader<ITest> serviceLoader = ServiceLoader.load(ITest.class);
        for (ITest test : serviceLoader) {
            test.test();
        }

    }

}
