package com.sxjdxy.mochat.json.domain;

/**
 * 功能：
 * 用户列表数据类
 * @author MoChen
 * Date  2019/12/10
 * @version 0.1
 */
public class ContactsJson {

    private String userid;

    private String nickname;//昵称

    private String profilePhoto;//头像

    private String introduce;//签名

    private int sex;//性别

    @Override
    public String toString() {
        return "ContactsJson{" +
                "userid='" + userid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", introduce='" + introduce + '\'' +
                ", sex=" + sex +
                '}';
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
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
}
