package com.sxjdxy.mochat.domain;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/12/18
 * @version 0.1
 */
public class Blogcom {

    private int id;
    private long blogid;//博客id
    private String userid;//评论用户id
    private String body;//评论内容
    private long time;//评论时间
    private int	review;//审核状态0审核中1通过2未通过

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public long getBlogid() {
        return blogid;
    }

    public void setBlogid(long blogid) {
        this.blogid = blogid;
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
