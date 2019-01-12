package com.yzz.study.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2019/1/11
 *
 * @Since 0.0.1
 */
public class TestScatteringReadsGatheringWrites {

    public static void main(String[] args) {
        //scatteringReader();
        GatheringWrites();
    }

    /**
     * scattering 读取buffer数组的时候，只会读取有效的字节数，因为position和limit的原因
     */
    public static void scatteringReader() {
        try {
            FileChannel fileChannel = TestFileChannel.getFileChannerl();
            //position=0,limit=capacity=5 写模式
            ByteBuffer b1 = ByteBuffer.allocate(5);
            ByteBuffer b2 = ByteBuffer.allocate(20);
            //position=2 limit=capacity=5
            b1.put("22".getBytes());
            b2.put("qwery".getBytes());

            //切换为读模式
            //position=0 limit=2,capacity=5
            b1.flip();
            b2.flip();
            ByteBuffer[] buffers = {b1, b2};


            fileChannel.write(buffers);
            fileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 一个Channel 向多个Buffer中写的时候,必须要写满前一个Buffer才会继续讲数据写入后面的Buffer
     */
    public static void GatheringWrites(){
        try {
            FileChannel fileChannel = TestFileChannel.getFileChannerl();
            ByteBuffer[] bs = {ByteBuffer.allocate(20),ByteBuffer.allocate(10)};
            fileChannel.read(bs);

            System.out.println(bs[0].position());
            System.out.println(bs[1].position());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
