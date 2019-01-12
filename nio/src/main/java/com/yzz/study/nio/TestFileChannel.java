package com.yzz.study.nio;

import sun.nio.ch.FileChannelImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2019/1/10
 *
 * @Since 0.0.1
 */
public class TestFileChannel {

    //Channel
    //FileChannel; 从文件中读取数据
    //DatagramChannel 通过UDP读取网络中的数据
    //SocketChannel; 通过TCP读取网络中的数据
    //ServerSocketChannel; 可以监听新进来的TCP连接,向web服务器一样，对每一个新进来的连接都会穿件一个SocketChannel
    public static void main(String[] args) {
        try {
            String dir = System.getProperties().getProperty("user.dir") + "/nio/file/nio-data.txt";
            File file = new File(dir);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                if (!file.exists()) {
                    file.createNewFile();
                }
            }
            write(file);
            //read(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static FileChannel getFileChannerl(){
        try{
            String dir = System.getProperties().getProperty("user.dir") + "/nio/file/nio-data.txt";
            File file = new File(dir);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                if (!file.exists()) {
                    file.createNewFile();
                }
            }
            RandomAccessFile accessFile = new RandomAccessFile(dir, "rw");
            return accessFile.getChannel();
        }catch (Exception e){
            return null;
        }
    }

    public static void write(File dir) {
        try {
            RandomAccessFile accessFile = new RandomAccessFile(dir, "rw");
            FileChannel fileChannel = accessFile.getChannel();
            ByteBuffer buffer = ByteBuffer.allocateDirect(10);
            buffer.put("大大".getBytes("UTF-8"));
            buffer.put("c".getBytes());
            buffer.flip();
            fileChannel.position(accessFile.length());
            fileChannel.write(buffer);
            fileChannel.close();
            accessFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 1. 写入数据到buffer
     * 2. buffer.flip
     * 3. 从buffer中读取数据
     * 4. buffer.clear or buffer.compact
     * @param dir
     */
    public static void read(File dir) {
        RandomAccessFile accessFile = null;
        byte[] b = new byte[1024];
        try {
            accessFile = new RandomAccessFile(dir, "rw");
            FileChannel fileChannel = accessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(100);
            int len = fileChannel.read(byteBuffer);
            while (len != -1) {
                System.out.println("读取了" + len + "个");
                byteBuffer.flip();
                byteBuffer.compact();
                while (byteBuffer.position() < len) {
                    System.out.print((char) byteBuffer.get());
                }
                byteBuffer.flip();
                len = fileChannel.read(byteBuffer);
            }
            byteBuffer.clear();
            fileChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
