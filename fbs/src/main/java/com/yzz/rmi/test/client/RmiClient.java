package com.yzz.rmi.test.client;

import com.yzz.rmi.test.bean.IPeople;
import com.yzz.rmi.test.bean.People;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/24
 *
 * @Since 0.0.1
 */
public class RmiClient {

    public static void main(String[] args) {
        try {
           // Registry registry = LocateRegistry.getRegistry("localhost", 1111);
            IPeople people = (IPeople) Naming.lookup("StudentService");
            System.out.println(people);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
