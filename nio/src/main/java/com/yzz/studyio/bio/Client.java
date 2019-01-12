package com.yzz.studyio.bio;

import com.yzz.studyio.bio.protocol.Message;

import java.util.Scanner;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2019/1/12
 *
 * @Since 0.0.1
 */
public class Client {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            BioClient bioClient = new BioClient(9090, "127.0.0.1", args[0]);
            bioClient.listen12n(message -> {
                System.out.println(message.toString());
            });
            while (true) {
                String content = sc.next();
                bioClient.sendMsg(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
