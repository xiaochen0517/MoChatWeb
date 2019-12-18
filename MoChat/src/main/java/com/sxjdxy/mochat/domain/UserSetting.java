package com.sxjdxy.mochat.domain;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/12/18
 * @version 0.1
 */
public class UserSetting {

    private int id;
    private String userid;//用户id
    private boolean comreview;//评论审核是否开启

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public boolean isComreview() {
        return comreview;
    }

    public void setComreview(boolean comreview) {
        this.comreview = comreview;
    }
}
