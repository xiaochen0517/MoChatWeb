package com.sxjdxy.mochat.dao;

import com.sxjdxy.mochat.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能：
 * 用户表持久层
 * @author MoChen
 * Date  2019/11/24
 * @version 0.1
 */
@Repository
public interface UserDao {

    /**
     * 查询所有用户
     * @return 用户list
     */
    @Select("select * from user")
    List<User> select();

    /**
     * 添加用户
     * @param user 用户数据类
     * @return 是否成功
     */
    @Insert("insert into user (userid, nickname, password, mail, blog, authkey, joindate)" +
            "values" +
            " (#{user.userid}, #{user.nickname}, #{user.password}, #{user.mail}, #{user.blog}, #{user.authkey}, #{user.joindate});")
    int addUser(@Param("user") User user);

    /**
     * 修改邮箱验证状态
     * @param userid userid
     * @param authkey 验证码
     * @param mailStatus 邮箱验证状态
     * @return 是否成功
     */
    @Update("update user set mailstatus = #{mailstatus} where userid = #{userid} and authkey = #{authkey}")
    int setMailStatus(@Param("userid")String userid, @Param("authkey")String authkey, @Param("mailstatus")boolean mailStatus);

    /**
     * 修改是否为单blog账户
     * @param userid userid
     * @param authkey 验证码
     * @param blog 是否为单blog账户
     * @return 是否成功
     */
    @Update("update user set blog = #{blog} where userid = #{userid} and authkey = #{authkey}")
    int setBlog(@Param("userid")String userid, @Param("authkey")String authkey, @Param("blog")boolean blog);

    /**
     * 设置指定的authkey
     * @param mail 邮箱
     * @param authcode 添加验证key
     * @return 是否成功
     */
    @Update("update user set authkey = #{authcode} where mail = #{mail}")
    int setAuthkey(@Param("mail")String mail, @Param("authcode")String authcode);

    /**
     * 删除指定用户的authkey
     * @param userid 用户名
     * @return 是否成功
     */
    @Update("update user set authkey = '' where userid = #{userid}")
    int deleteAuthkey(@Param("userid")String userid);

    /**
     * 修改密码
     * @param mail 邮箱
     * @param password 密码
     * @return 是否成功
     */
    @Update("update user set password = #{password} where mail = #{mail}")
    int setPassword(@Param("mail")String mail, @Param("password")String password);

    /**
     * 查询userid是否重复
     * @param userid userid
     * @return 查询到指定条件的行数
     */
    @Select("select count(*) from user where userid =#{userid};")
    int findUserId(@Param("userid") String userid);

    /**
     * 查询nickname是否重复
     * @param nickname 昵称
     * @return 查询到指定条件的行数
     */
    @Select("select count(*) from user where nickname =#{nickname};")
    int findNickName(@Param("nickname") String nickname);

    /**
     * 查询mail是否重复
     * @param mail 邮箱
     * @return 查询到指定条件的行数
     */
    @Select("select count(*) from user where mail =#{mail};")
    int findMail(@Param("mail") String mail);

    /**
     * 查询指定userid和authkey是否存在
     * @param userid userid
     * @param authkey 验证key
     * @return 查询到指定条件的行数
     */
    @Select("select count(*) from user where userid = #{userid} and authkey = #{authkey}")
    int findAuthKey(@Param("userid")String userid, @Param("authkey")String authkey);

    /**
     * 获取指定userid的资料
     * @param userid userid
     * @return 返回user数据类
     */
    @Select("select * from user where userid = #{userid}")
    User findUser(@Param("userid")String userid);

    /**
     * 获取指定userid的资料
     * @param mail 邮箱
     * @return user
     */
    @Select("select * from user where mail = #{mail}")
    User findUserM(@Param("mail")String mail);

    /**
     * 根据mail获取mailstatus
     * @param mail 邮箱
     * @return mailstatus
     */
    @Select("select mailstatus from user where mail = #{mail}")
    boolean findMailStatus(@Param("mail")String mail);

    /**
     * authkey是否重复
     * @param mail 邮箱
     * @param authcode 验证码
     * @return 是否重复
     */
    @Select("select count(*) from user where mail = #{mail} and authkey = #{authcode}")
    int findMailAuthkey(@Param("mail")String mail, @Param("authcode")String authcode);

    /**
     * 查询是否为单blog账户
     * @param mail 邮箱
     * @return blog
     */
    @Select("select blog from user where mail = #{mail}")
    boolean findBlog(@Param("mail")String mail);

    /**
     * 获取单个用户信息
     * @param userid userid
     * @return user
     */
    @Select("select * from user where userid = #{userid}")
    User findUserSingle(@Param("userid") String userid);

}
