package com.sxjdxy.mochat.domain;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/12/18
 * @version 0.1
 */
public class Picture {

    private long id;
    private String userid;//用户id
    private long msgid;//消息id
    private long picid;//图片json字符串id
    private String picjson;//图片json数据

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public long getMsgid() {
        return msgid;
    }

    public void setMsgid(long msgid) {
        this.msgid = msgid;
    }

    public long getPicid() {
        return picid;
    }

    public void setPicid(long picid) {
        this.picid = picid;
    }

    public String getPicjson() {
        return picjson;
    }

    public void setPicjson(String picjson) {
        this.picjson = picjson;
    }
}
