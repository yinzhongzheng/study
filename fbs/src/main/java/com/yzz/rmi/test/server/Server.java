package com.yzz.rmi.test.server;

import com.yzz.rmi.test.bean.IPeople;
import com.yzz.rmi.test.bean.People;
import com.yzz.rmi.test.bean.PeopleService;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/24
 *
 * @Since 0.0.1
 */
public class Server {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(3333);
            People people = new People();
            people.setAge(1);
            people.setName("yzz");
            IPeople peopleService = new PeopleService();
            Naming.bind("rmi://127.0.0.1:3333/StudentService", peopleService);
            System.out.println("服务已启动");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
