package com.yzz.hystrix;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/21
 *
 * @Since 0.0.1
 */
@RestController
@RequestMapping(value = "/")
public class HystrixController {

    @Async
    @RequestMapping(value = "/get1")
    public String get1() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HystrixCommond commond = new HystrixCommond();
        return commond.execute();
    }

    @RequestMapping(value = "/get2")
    public String get2() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "没有使用hystrix";

    }

    @RequestMapping(value = "/get3")
    public String get3() {
        System.out.println(Thread.currentThread().getName());
        //HystrixCommond commond = new HystrixCommond();
        return "哈哈";
    }


}
