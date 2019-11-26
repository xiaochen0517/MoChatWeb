package com.sxjdxy.mochat.dao;

import com.sxjdxy.mochat.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    @Select("select * from user")
    List<User> select();

    @Insert("insert into user (userid, nickname, password, mail, blog, authkey)" +
            "values" +
            " (#{user.userid}, #{user.nickname}, #{user.password}, #{user.mail}, #{user.blog}, #{user.authkey});")
    int addUser(@Param("user") User user);

    /**
     * 查询userid是否重复
     * @param userid
     * @return
     */
    @Select("select count(*) from user where userid =#{userid};")
    int findUserId(@Param("userid") String userid);

    @Select("select count(*) from user where nickname =#{nickname};")
    int findNickName(@Param("nickname") String nickname);

    @Select("select count(*) from user where mail =#{mail};")
    int findMail(@Param("mail") String mail);

}
