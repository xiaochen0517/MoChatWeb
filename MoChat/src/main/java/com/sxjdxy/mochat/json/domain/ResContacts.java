package com.sxjdxy.mochat.json.domain;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/12/10
 * @version 0.1
 */
public class ResContacts {

    private int ErrorCode;

    private String userid;
    private String nickname;
    private String mail;
    private String profilephot;//头像
    private String introduce;//签名
    private int sex;//性别
    private long birthday;//生日
    private long joindate;//注册时间
    private String address;//地址

    @Override
    public String toString() {
        return "ResContacts{" +
                "ErrorCode=" + ErrorCode +
                ", userid='" + userid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", mail='" + mail + '\'' +
                ", profilephot='" + profilephot + '\'' +
                ", introduce='" + introduce + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", joindate=" + joindate +
                ", address='" + address + '\'' +
                '}';
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public long getJoindate() {
        return joindate;
    }

    public void setJoindate(long joindate) {
        this.joindate = joindate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
