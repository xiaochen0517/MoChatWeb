package com.sxjdxy.mochat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sxjdxy.mochat.dao.UserDao;
import com.sxjdxy.mochat.domain.User;
import com.sxjdxy.mochat.json.domain.ResContacts;
import com.sxjdxy.mochat.service.service.BlogUserService;
import com.sxjdxy.mochat.until.data.UrlDataUntil;
import com.sxjdxy.mochat.until.loger.Log;
import com.sxjdxy.mochat.until.properties.PropertiesUntil;
import com.sxjdxy.mochat.until.data.MD5DataUntil;
import com.sxjdxy.mochat.until.properties.ThreadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Objects;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/12/12
 * @version 0.1
 */
@Component
public class BlogUserServiceImpl implements BlogUserService {

    private static final String TAG = "BlogUserServiceImpl";

    private String userIdRegex = "[a-zA-Z][a-zA-Z0-9]{4,11}";
    private String nickNameRegex = "[\u4e00-\u9fa5\\w]{2,12}";
    private String passwordRegex = "[a-zA-Z0-9]{32}";
    private String mailRegex = "[a-zA-Z0-9]*@[a-zA-Z0-9]*.[a-zA-Z]*";

    private String errorCode = "ErrorCode";

    @Autowired
    private UserDao userDao;

    /**
     * 用户登录
     *
     * @param userid   用户id
     * @param password 密码
     * @return json
     */
    @Override
    public String login(String userid, String password) {
        JSONObject jsonObject = new JSONObject();
        //检查用户名密码
        if (!userid.matches(userIdRegex)) {
            //userid格式错误
            jsonObject.put(errorCode, 101);
        } else if (!password.matches(passwordRegex)) {
            //password格式错误
            jsonObject.put(errorCode, 102);
        } else {
            //格式正确，开始检查用户名密码
            String dbPW = userDao.findPassword(userid);
            if (password.equals(dbPW)) {
                //生成authkey
                String authkey = MD5DataUntil.getMd5(userid + new Date().getTime());
                try {
                    //查看properties中是否有重复的数据
                    String propertiesPath = UrlDataUntil.getPropertiesPath("user.properties");
                    if (propertiesPath == null) {
                        //获取到的配置文件路径为空
                        jsonObject.put(errorCode, 401);
                    } else {
                        PropertiesUntil propertiesUntil = new PropertiesUntil(propertiesPath);
                        String timeStr = propertiesUntil.getPro(userid);
                        if (!timeStr.equals("")) {
                            String oldAuthkey = propertiesUntil.getPro(password);
                            //删除数据
                            propertiesUntil.delPro(password);
                            propertiesUntil.delPro(oldAuthkey);
                            propertiesUntil.delPro(userid);
                        }
                        //用户密码验证正确，保存数据
                        propertiesUntil.setPro(password, authkey);
                        propertiesUntil.setPro(authkey, userid);
                        propertiesUntil.setPro(userid, "30");
                        propertiesUntil.commit();
                        //启动子线程
                        new ThreadProperties(propertiesPath, userid, password, authkey).start();
                        jsonObject.put(errorCode, 0);
                        jsonObject.put("AuthKey", authkey);
                    }
                } catch (IOException e) {
                    //写入密匙错误
                    e.printStackTrace();
                    jsonObject.put(errorCode, 301);
                }
            } else {
                //用户名或密码错误
                jsonObject.put(errorCode, 201);
            }
        }
        String reJson = jsonObject.toJSONString();
        Log.d(TAG, reJson);
        return reJson;
    }

    /**
     * 用户注销
     *
     * @param authkey 验证密匙
     * @return json
     */
    @Override
    public String logout(String authkey) {
        JSONObject jsonObject = new JSONObject();
        //检查authkey
        if (!authkey.matches(passwordRegex)) {
            //authkey错误
            jsonObject.put(errorCode, 101);
        } else {
            try {
                String propertiesPath = UrlDataUntil.getPropertiesPath("user.properties");
                if (propertiesPath == null) {
                    //获取到的配置文件路径为空
                    jsonObject.put(errorCode, 301);
                } else {
                    //查询userid
                    PropertiesUntil propertiesUntil = new PropertiesUntil(propertiesPath);
                    String userid = propertiesUntil.getPro(authkey);
                    if (userid.matches(userIdRegex)) {
                        //获取password
                        String password = userDao.findPassword(userid);
                        if (password != null && password.matches(passwordRegex)) {
                            //成功注销,删除key
                            propertiesUntil.delPro(password);
                            propertiesUntil.delPro(authkey);
                            propertiesUntil.delPro(userid);
                            propertiesUntil.commit();
                            jsonObject.put(errorCode, 0);
                        } else {
                            //获取到password错误
                            jsonObject.put(errorCode, 402);
                        }
                    } else {
                        //注销失败
                        jsonObject.put(errorCode, 401);
                    }
                }
            } catch (Exception e) {
                //读写密匙错误
                e.printStackTrace();
                jsonObject.put(errorCode, 201);
            }
        }
        String reJson = jsonObject.toJSONString();
        Log.d(TAG, reJson);
        return reJson;
    }

    /**
     * 获取用户信息
     * @param authkey 验证密匙
     * @return json
     */
    @Override
    public String getUserData(String authkey) {
        ResContacts resContacts = new ResContacts();
        //检查authkey
        if (!authkey.matches(passwordRegex)) {
            //authkey错误
            resContacts.setErrorCode(101);
        } else {
            try {
                String propertiesPath = UrlDataUntil.getPropertiesPath("user.properties");
                if (propertiesPath == null) {
                    //获取到的配置文件路径为空
                    resContacts.setErrorCode(301);
                } else {
                    //查询userid
                    PropertiesUntil propertiesUntil = new PropertiesUntil(propertiesPath);
                    String userid = propertiesUntil.getPro(authkey);
                    if (userid.matches(userIdRegex)) {
                        //查询信息
                        User user = userDao.findUserSingle(userid);
                        if (user == null){
                            //查询到的用户信息为空
                            resContacts.setErrorCode(501);
                        }else{
                            resContacts.setErrorCode(0);
                            resContacts.setUserid(userid);
                            resContacts.setAddress(user.getAddress());
                            resContacts.setJoindate(user.getJoindate());
                            resContacts.setBirthday(user.getBirthday());
                            resContacts.setSex(user.getSex());
                            resContacts.setIntroduce(user.getIntroduce());
                            resContacts.setMail(user.getMail());
                            resContacts.setNickname(user.getNickname());
                            resContacts.setProfilephoto(user.getProfilephoto());
                        }
                    } else {
                        //authkey验证失败
                        resContacts.setErrorCode(401);
                    }
                }
            } catch (Exception e) {
                //读写密匙错误
                e.printStackTrace();
                resContacts.setErrorCode(201);
            }
        }
        String reJson = JSON.toJSONString(resContacts);
        Log.d(TAG, reJson);
        return reJson;
    }
}
