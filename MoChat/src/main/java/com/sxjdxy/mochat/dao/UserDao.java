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

    @Insert("insert into user (userid, nickname, password)" +
            "values" +
            " (#{user.userid}, #{user.nickname}, #{user.password});")
    int addUser(@Param("user") User user);

}
