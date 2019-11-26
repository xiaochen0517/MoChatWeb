package com.sxjdxy.mochat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sxjdxy.mochat.dao.UserDao;
import com.sxjdxy.mochat.data.IMUrlData;
import com.sxjdxy.mochat.domain.User;
import com.sxjdxy.mochat.service.service.UserService;
import com.sxjdxy.mochat.until.OkHttpUntil;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 功能：
 * userservice impl
 * @author MoChen
 * Date  2019/11/25
 * @version 0.1
 */
@Component
public class UserServiceImpl implements UserService {

    private final static String TAG = "UserServiceImpl";

    @Autowired
    private UserDao userDao;

    @Override
    public String addUser(String userid, String nickname, String password) {
        //处理数据
        if (userid.length() > 5 || nickname.length() > 2 || password.length() > 6){
            //初始化数据
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", userid);
            jsonObject.put("password", password);
            jsonObject.put("nickname", nickname);
            //开始注册IM账号
            try {
                Response response = OkHttpUntil.postJsonOkHttp(IMUrlData.ADD_USER_URL, jsonObject.toJSONString());
                if (response.isSuccessful()){
                    System.out.println(response.body().string());
                    //写入数据库
                    User user = new User();
                    user.setUserid(userid);
                    user.setNickname(nickname);
                    user.setPassword(password);
                    int result = userDao.addUser(user);
                    if (result > 0){
                        return "success";
                    }else {
                        return "faile1";
                    }
                }else{
                    return "faile2";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "faile3";
            }
        }else{
            return "faile4";
        }
    }
}
