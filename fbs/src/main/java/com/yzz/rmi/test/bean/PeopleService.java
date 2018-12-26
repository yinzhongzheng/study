package com.yzz.rmi.test.bean;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/24
 *
 * @Since 0.0.1
 */
public class PeopleService extends UnicastRemoteObject  implements IPeople{


    public PeopleService() throws RemoteException {
    }


    public List<People> addUser(String name) throws RemoteException {
       return new ArrayList<People>();
    }
}
