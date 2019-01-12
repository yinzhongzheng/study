package com.yzz.studyio.bio.protocol;

import java.io.*;

/**
 * describe: 自定义协议的操作类包括 读和写
 * E-mail:yzzstyle@163.com  date:2019/1/12
 * 使用 jdk自带的序列化机制，将Message消息对象作为CS交互的介质
 * @Since 0.0.1
 */
public class BioChartRoomProtocol {

    /**
     * 些消息 适用于客户单发消息
     * @param outputStream
     * @param header
     * @param content
     * @throws IOException
     */
    public static void write(OutputStream outputStream, Header header, String content) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        Message message = new Message(header, content);
        objectOutputStream.writeObject(message);
    }

    /***
     * 写消息 使用服务端转发消息
     * @param outputStream
     * @param message
     * @throws IOException
     */
    public static void write(OutputStream outputStream, Message message) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(message);
    }

    /**
     * 读操作，将消息转化成消息对象
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static Message parse(InputStream inputStream) throws IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Message message = null;
        try {
            message = (Message) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new Error(e.getMessage());
        }
        return message;
    }

}
