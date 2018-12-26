package com.yzz.rmi.test.bean;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/24
 *
 * @Since 0.0.1
 */
public interface IPeople extends Remote {
    List<People> addUser(String name)throws RemoteException;
}
