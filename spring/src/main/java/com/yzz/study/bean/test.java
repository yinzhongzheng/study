package com.yzz.study.bean;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.support.GenericPropertiesContextLoader;

/**
 * author:yzz
 * date:2018/12/3
 * E-mail:yzzstyle@163.com
 * com.yzz.study.bean
 * 注释:
 */
public class test {

    public static void main(String[] args) {
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("testBean");
//        SimpleBean bean = applicationContext.getBean(SimpleBean.class);
//        System.out.println(bean);
//        applicationContext.close();

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.yzz.study");
//        annotationConfigApplicationContext.scan("com.yzz.study");
        SimpleBean simpleBean = annotationConfigApplicationContext.getBean(SimpleBean.class);
        System.out.println(simpleBean);

    }

}
