package com.yzz.studyio.bio;

import com.yzz.studyio.bio.protocol.BioChartRoomProtocol;
import com.yzz.studyio.bio.protocol.Header;
import com.yzz.studyio.bio.protocol.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * describe:
 * E-mail:yzzstyle@163.com  date:2019/1/12
 * 你可以简单这样测试
 *
 * @Since 0.0.1
 *
 *   public class Client {
 *       public static void main(String[] args) {
 *           try {
 *               Scanner sc = new Scanner(System.in);
 *               BioClient bioClient = new BioClient(9090, "127.0.0.1", args[0]);
 *               bioClient.listen12n(message -> {
 *                   System.out.println(message.toString());
 *               });
 *               while (true) {
 *                   String content = sc.next();
 *                   bioClient.sendMsg(content);
 *               }
 *           } catch (Exception e) {
 *               e.printStackTrace();
 *           }
 *       }
 *   }
*/
public class BioClient {

    //服务端端口
    private final int port;

    //服务端地址
    private final String location;

    //连接服务端的Socket对象
    private Socket socket;

    //创建的一个单线程
    private Executor pool = Executors.newSingleThreadExecutor();

    //用户昵称，必填
    private final String nickName;

    //消息头
    private final Header header;

    //日志
    private Logger logger = Logger.getLogger(BioClient.class.getName());

    /**
     * 该构造初始化了 port、location、nicName 并创建了与服务端通信的Socket连接
     * @param port
     * @param location
     * @param nicName
     * @throws IOException
     */
    public BioClient(int port, String location, String nicName) throws IOException {
        this.port = port;
        this.location = location;
        this.nickName = nicName;
        InetAddress inetAddress = InetAddress.getByName(location);
        socket = new Socket(inetAddress, port);
        header = new Header(inetAddress.getHostAddress(), socket.getLocalPort(), nicName);
    }

    /**
     * 向服务端发送消息
     * @param msg
     * @throws IOException
     */
    public void sendMsg(String msg) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        //自定义协议去发送消息
        BioChartRoomProtocol.write(outputStream, header, msg);
    }

    /**
     * 从服务端接收消息
     * @return
     * @throws IOException
     */
    public Message getResponse() throws IOException {
        InputStream inputStream = socket.getInputStream();
        //自定义协议去接收消息，返回消息对象
        Message message = BioChartRoomProtocol.parse(inputStream);
        return message;
    }

    /**
     * 清除资源
     */
    public void clear() {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 监听
     * 开辟一个工作线程去接收服务端转发的消息
     * @param callback
     */
    public void listen12n(MSGCallback callback) {
        pool.execute(() -> {
            while (true) {
                try {
                    Message message = getResponse();
                    //该回调面向用户，提供服务端发送的消息
                    callback.message(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    clear();
                    return;
                }
            }
        });

    }

    /**
     * 回调接口
     */
    public interface MSGCallback {
        void message(Message message);
    }
}
