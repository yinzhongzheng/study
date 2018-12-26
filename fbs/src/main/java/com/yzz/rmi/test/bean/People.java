package com.yzz.rmi.test.bean;

import java.io.Serializable;
import java.rmi.Remote;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/24
 *
 * @Since 0.0.1
 */
public class People implements Serializable{
    private String name;
    private  int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
