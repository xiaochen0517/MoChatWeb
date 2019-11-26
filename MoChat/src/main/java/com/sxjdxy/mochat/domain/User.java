package com.sxjdxy.mochat.domain;

import java.sql.Date;

/**
 * 功能：
 * 用户表数据类
 * @author MoChen
 * Date  2019/11/25
 * @version 0.1
 */
public class User {

    private int id;
    private String userid;
    private String nickname;
    private String password;
    private String mail;
    private String profilephot;//头像
    private String introduce;//签名
    private int sex;//性别
    private Date birthday;//生日
    private String address;//地址
    private boolean blog;//是否仅开通blog
    private boolean mailstatus;//邮箱是否验证
    private String authkey;//邮箱验证authkey

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", profilephot='" + profilephot + '\'' +
                ", introduce='" + introduce + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", blog=" + blog +
                ", mailstatus=" + mailstatus +
                ", authkey='" + authkey + '\'' +
                '}';
    }

    public String getAuthkey() {
        return authkey;
    }

    public void setAuthkey(String authkey) {
        this.authkey = authkey;
    }

    public boolean isMailstatus() {
        return mailstatus;
    }

    public void setMailstatus(boolean mailstatus) {
        this.mailstatus = mailstatus;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isBlog() {
        return blog;
    }

    public void setBlog(boolean blog) {
        this.blog = blog;
    }

    public String getProfilephot() {
        return profilephot;
    }

    public void setProfilephot(String profilephot) {
        this.profilephot = profilephot;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
