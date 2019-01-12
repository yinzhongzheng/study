package com.yzz.studyio.bio.protocol;

import java.io.Serializable;

/**
 * describe: 自定义协议中的消息头
 * E-mail:yzzstyle@163.com  date:2019/1/12
 *
 * @Since 0.0.1
 */
public class Header implements Serializable {

    //消息来源地
    private String host;
    //消息来源地的端口
    private int port;
    //发送消息用户的昵称
    private String nickName;

    public Header(String host, int port, String nickName) {
        this.host = host;
        this.port = port;
        this.nickName = nickName;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getNickName() {
        return nickName;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return " host: " + host + " port: " + port + " nickName: " + nickName;
    }
}
