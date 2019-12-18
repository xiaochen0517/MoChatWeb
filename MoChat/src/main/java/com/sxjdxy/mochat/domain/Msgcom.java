package com.sxjdxy.mochat.domain;

import java.security.PrivateKey;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/12/18
 * @version 0.1
 */
public class Msgcom {

    private int id;
    private long msgid;//消息id
    private String userid;//评论用户id
    private String body;//评论内容
    private long time;//评论时间

    public long getMsgid() {
        return msgid;
    }

    public void setMsgid(long msgid) {
        this.msgid = msgid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
