package com.yzz.thread.test;

import java.io.*;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2018/12/13
 *
 * @Since 0.0.1
 */
public class Pip {

    static class PipThread implements Runnable{

        PipedReader reader;

        public PipThread(PipedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            int receive;
            try {
                while ((receive = reader.read())!=-1){
                    System.out.println((char) receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();
        try {
            //writer.connect(reader);
            writer.write("哈哈");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PipThread pipThread = new PipThread(reader);
        Thread thread = new Thread(pipThread);
        thread.start();
    }



}
