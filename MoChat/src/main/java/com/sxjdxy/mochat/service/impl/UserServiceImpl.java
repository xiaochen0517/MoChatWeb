package com.sxjdxy.mochat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sxjdxy.mochat.dao.UserDao;
import com.sxjdxy.mochat.data.IMUrlData;
import com.sxjdxy.mochat.data.UrlData;
import com.sxjdxy.mochat.domain.User;
import com.sxjdxy.mochat.service.service.UserService;
import com.sxjdxy.mochat.until.MailUntil;
import com.sxjdxy.mochat.until.OkHttpUntil;
import com.sxjdxy.mochat.until.data.MD5DataUntil;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

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

    private String userIdRegex = "[a-zA-Z][a-zA-Z0-9]{4,11}";
    private String nickNameRegex = "[\u4e00-\u9fa5\\w]{2,12}";
    private String passwordRegex = "[a-zA-Z]{6,32}";
    private String mailRegex = "[a-zA-Z0-9]*@[a-zA-Z0-9]*.[a-zA-Z]*";

    private String errorCode = "ErrorCode";

    @Autowired
    private UserDao userDao;

    @Override
    public String addUser(String userid, String nickname, String password, String mail) {
        //json
        JSONObject returnJson = new JSONObject();
        //处理数据
        if (!userid.matches(userIdRegex)){
            returnJson.put(errorCode, 101);
        }else if (!nickname.matches(nickNameRegex)){
            returnJson.put(errorCode, 102);
        }else if(!password.matches(passwordRegex)){
            returnJson.put(errorCode, 103);
        }else if (!mail.matches(mailRegex)){
            returnJson.put(errorCode, 104);
        }else{
            //判断是否邮箱重复
            if (userDao.findMail(mail)>0){
                //邮箱重复
                returnJson.put(errorCode, 201);
            }else if (userDao.findUserId(userid)>0){
                //userid重复
                returnJson.put(errorCode, 202);
            }else if (userDao.findNickName(nickname)>0){
                //昵称重复
                returnJson.put(errorCode, 203);
            }else{
                //将未验证的mail用户存入数据库
                //1.生成md5 authKey
                String authKey = MD5DataUntil.getMd5(new Date().getTime()+"");
                //2.存入数据库
                //写入数据库
                User user = new User();
                user.setUserid(userid);//userid
                user.setNickname(nickname);//昵称
                user.setPassword(password);//密码
                user.setMail(mail);
                user.setBlog(false);//是否为blog账户
                user.setAuthkey(authKey);//邮箱验证authkey
                int result = userDao.addUser(user);
                //3.成功后发送验证码
                if (result > 0){
                    //成功插入数据
                    //发送邮件
                    boolean resultStatus = MailUntil.sendMail(
                            UrlData.HOST_URL+"/verify?userid="+userid+"&authkey="+authKey, mail);
                    if (resultStatus){
                        //发送成功
                        returnJson.put(errorCode, 0);
                    }else{
                        //发送邮件失败
                        returnJson.put(errorCode, 401);
                    }
                }else {
                    //存入数据库错误
                    returnJson.put(errorCode, 301);
                }
            }
        }
        return returnJson.toJSONString();
    }

    /**
     * 邮件验证
     * @param userId userid
     * @param authKey 验证key
     * @return
     */
    @Override
    public String verifyMail(String userId, String authKey) {



        //初始化数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "userid");
        jsonObject.put("password", "password");
        jsonObject.put("nickname", "nickname");
        //开始注册IM账号
        try {
            Response response = OkHttpUntil.postJsonOkHttp(IMUrlData.ADD_USER_URL, jsonObject.toJSONString());
            if (response.isSuccessful()){
                System.out.println(response.body().string());
            }else{
                return "faile2";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "faile3";
        }
        return null;
    }
}
