package com.yzz.study.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * author:yzz
 * date:2018/12/4
 * E-mail:yzzstyle@163.com
 * com.yzz.study.bean
 * 注释:
 */
@ComponentScan
public class SimpleBean {

    private AnnoBean annoBean;

    @Autowired
    public SimpleBean(){
        annoBean = new AnnoBean();
        System.out.println(annoBean.a);
    }
}
