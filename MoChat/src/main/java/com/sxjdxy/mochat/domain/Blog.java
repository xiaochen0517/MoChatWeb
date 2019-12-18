package com.sxjdxy.mochat.domain;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/12/18
 * @version 0.1
 */
public class Blog {

    private int id;
    private String userid;//用户id
    private long blogid;//消息id
    private String body;//消息内容
    private long time;//时间
    private long like;//点赞数
    private long visit;//访问次数
    private int review;//审核状态0审核中1通过2未通过

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public long getBlogid() {
        return blogid;
    }

    public void setBlogid(long blogid) {
        this.blogid = blogid;
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

    public long getLike() {
        return like;
    }

    public void setLike(long like) {
        this.like = like;
    }

    public long getVisit() {
        return visit;
    }

    public void setVisit(long visit) {
        this.visit = visit;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }
}
