package com.sxjdxy.mochat.domain;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/12/18
 * @version 0.1
 */
public class Admin {

    private int id;
    private String adminid;
    private String password;
    private int level;

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
