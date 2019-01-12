package com.yzz.studyio.bio.protocol;

import java.io.Serializable;

/**
 * describe:自定义协议的消息对象，包括消息头和消息体
 * E-mail:yzzstyle@163.com  date:2019/1/12
 *
 * @Since 0.0.1
 */
public class Message implements Serializable {
    private Header header;
    private String message;

    public Message(Header header, String message) {
        this.header = header;
        this.message = message;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(header.getNickName())
                .append("(")
                .append(header.getHost())
                .append(":")
                .append(header.getPort())
                .append(")")
                .append("\r\n")
                .append("\t")
                .append(message);
        return sb.toString();
    }
}
