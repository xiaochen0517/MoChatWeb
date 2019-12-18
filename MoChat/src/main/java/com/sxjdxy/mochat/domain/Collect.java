package com.sxjdxy.mochat.domain;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/12/18
 * @version 0.1
 */
public class Collect {

    private long id;
    private String userid;//用户id
    private int type;//收藏的类型
    private long msgid;//消息id
    private long blogid;//博客id
    private String strbody;//字符串
    private String image;//图片名

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getMsgid() {
        return msgid;
    }

    public void setMsgid(long msgid) {
        this.msgid = msgid;
    }

    public long getBlogid() {
        return blogid;
    }

    public void setBlogid(long blogid) {
        this.blogid = blogid;
    }

    public String getStrbody() {
        return strbody;
    }

    public void setStrbody(String strbody) {
        this.strbody = strbody;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
