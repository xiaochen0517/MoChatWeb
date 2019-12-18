package com.sxjdxy.mochat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sxjdxy.mochat.dao.UserDao;
import com.sxjdxy.mochat.data.IMUrlData;
import com.sxjdxy.mochat.data.UrlData;
import com.sxjdxy.mochat.domain.User;
import com.sxjdxy.mochat.json.domain.ContactsJson;
import com.sxjdxy.mochat.json.domain.ResContacts;
import com.sxjdxy.mochat.json.domain.ResContactsList;
import com.sxjdxy.mochat.service.service.UserService;
import com.sxjdxy.mochat.until.MailUntil;
import com.sxjdxy.mochat.until.OkHttpUntil;
import com.sxjdxy.mochat.until.PropertiesUtil;
import com.sxjdxy.mochat.until.SystemUntil;
import com.sxjdxy.mochat.until.data.JsonDataUntil;
import com.sxjdxy.mochat.until.data.MD5DataUntil;
import com.sxjdxy.mochat.until.loger.Log;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

/**
 * 功能：
 * userservice impl
 *
 * @author MoChen
 * Date  2019/11/25
 * @version 0.1
 */
@Component
public class UserServiceImpl implements UserService {

    private final static String TAG = "UserServiceImpl";

    private String userIdRegex = "[a-zA-Z][a-zA-Z0-9]{4,11}";
    private String nickNameRegex = "[\u4e00-\u9fa5\\w]{2,12}";
    private String passwordRegex = "[a-zA-Z0-9]{32}";
    private String mailRegex = "[a-zA-Z0-9]*@[a-zA-Z0-9]*.[a-zA-Z]*";

    private String errorCode = "ErrorCode";

    //properties文件路径
    private String propertiesPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("imtoken.properties")).getPath();

    @Autowired
    private UserDao userDao;

    /**
     * 用户注册，同时注册blog和im
     *
     * @param userid   userid
     * @param nickname
     * @param password 密码
     * @param mail
     * @return
     */
    @Override
    public String addUser(String userid, String nickname, String password, String mail) {
        //json
        JSONObject returnJson = new JSONObject();
        //处理数据
        if (!userid.matches(userIdRegex)) {
            returnJson.put(errorCode, 101);
        } else if (!nickname.matches(nickNameRegex)) {
            returnJson.put(errorCode, 102);
        } else if (!password.matches(passwordRegex)) {
            returnJson.put(errorCode, 103);
        } else if (!mail.matches(mailRegex)) {
            returnJson.put(errorCode, 104);
        } else {
            //判断是否邮箱重复
            if (userDao.findMail(mail) > 0) {
                //邮箱重复
                returnJson.put(errorCode, 201);
            } else if (userDao.findUserId(userid) > 0) {
                //userid重复
                returnJson.put(errorCode, 202);
            } else if (userDao.findNickName(nickname) > 0) {
                //昵称重复
                returnJson.put(errorCode, 203);
            } else {
                //将未验证的mail用户存入数据库
                //1.生成md5 authKey
                String authKey = MD5DataUntil.getMd5(new Date().getTime() + "");
                //获取时间
                long javaDate = new Date().getTime();
                User user = new User();
                user.setUserid(userid);//userid
                user.setNickname(nickname);//昵称
                user.setPassword(password);//密码
                user.setMail(mail);
                user.setBlog(true);//是否为blog账户
                user.setAuthkey(authKey);//邮箱验证authkey
                user.setJoindate(javaDate);
                // 2.成功后发送验证码
                //3.存入数据库
                int result = userDao.addUser(user);
                if (result > 0) {
                    //成功插入数据
                    //发送邮件
                    boolean resultStatus = MailUntil.sendAddUserMail(
                            UrlData.HOST_URL + "/user/verifymail?userid=" + userid + "&authkey=" + authKey, mail);
                    if (resultStatus) {
                        //发送成功
                        returnJson.put(errorCode, 0);
                    } else {
                        //发送邮件失败
                        returnJson.put(errorCode, 301);
                    }
                } else {
                    //存入数据库错误
                    returnJson.put(errorCode, 401);
                }


            }
        }
        return returnJson.toJSONString();
    }

    /**
     * 邮件验证
     *
     * @param userId  userid
     * @param authKey 验证key
     * @return
     */
    @Override
    public String verifyMail(String userId, String authKey) {
        if (!userId.matches(userIdRegex) || authKey.equals("")) {
            //userid格式错误
            return "userid格式错误或authkey为空";
        } else {
            //查询userid和authkey
            if (userDao.findAuthKey(userId, authKey) == 0) {
                //无此账户，请重新注册
                return "无此账户，请重新注册";
            } else {
                //获取用户资料
                User user = userDao.findUser(userId);
                if (user == null) {
                    //用户资料为空
                    return "用户资料为空";
                } else {
                    //初始化数据
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("username", user.getUserid());
                    jsonObject.put("password", user.getPassword());
                    jsonObject.put("nickname", user.getNickname());
                    //开始注册IM账号
                    try {
                        Response response = OkHttpUntil.postJsonOkHttp(IMUrlData.ADD_USER_URL, jsonObject.toJSONString());
                        if (response.isSuccessful()) {
                            //成功注册
                            System.out.println(response.body().string());
                            //修改数据
                            if (userDao.setMailStatus(userId, authKey, true) == 0) {
                                //修改失败
                                return "修改账户状态失败";
                            } else {
                                //成功
                                //修改blog
                                userDao.setBlog(userId, authKey, false);
                                //删除表中的authkey
                                userDao.deleteAuthkey(userId);
                                return "您已成功验证，快去登录体验吧！";
                            }
                        } else {
                            //注册失败
                            userDao.setBlog(userId, authKey, true);
                            return "注册IM失败";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        userDao.setBlog(userId, authKey, true);
                        return "注册IM失败";
                    }
                }
            }
        }
    }

    /**
     * 修改密码提交
     *
     * @param mail 邮箱
     * @return
     */
    @Override
    public String editPw(String mail) {
        JSONObject jsonObject = new JSONObject();
        if (!mail.matches(mailRegex)) {
            //mail不符合正则
            jsonObject.put(errorCode, 101);
        } else {
            //查询指定值
            if (!userDao.findMailStatus(mail)) {
                //未验证地址，无法修改
                jsonObject.put(errorCode, 102);
            } else {
                //生成验证码
                String authCode = SystemUntil.getAuthCode();
                //储存验证码
                if (userDao.setAuthkey(mail, authCode) == 0) {
                    //储存失败
                    jsonObject.put(errorCode, 201);
                } else {
                    //发送验证码
                    if (!MailUntil.sendEditPwMail(authCode, mail)) {
                        //发送失败
                        jsonObject.put(errorCode, 301);
                    } else {
                        //发送成功
                        jsonObject.put(errorCode, 0);
                    }
                }
            }
        }
        return jsonObject.toJSONString();
    }

    /**
     * @param mail
     * @param authcode
     * @return
     */
    @Override
    public String verifyEditPw(String mail, String authcode) {
        JSONObject jsonObject = new JSONObject();
        //验证邮箱验证码
        if (!mail.matches(mailRegex)) {
            //mail不符合正则
            jsonObject.put(errorCode, 101);
        }
        if (authcode.equals("")) {
            //验证码不符合条件
            jsonObject.put(errorCode, 102);
        } else {
            //获取数据库值
            if (userDao.findMailAuthkey(mail, authcode) == 0) {
                //无值，验证码错误
                jsonObject.put(errorCode, 201);
            } else {
                String authKey = MD5DataUntil.getMd5(new Date().getTime() + "");
                //写入数据库
                if (userDao.setAuthkey(mail, authKey) == 0) {
                    //写入错误
                    jsonObject.put(errorCode, 301);
                } else {
                    //成功
                    jsonObject.put(errorCode, 0);
                    jsonObject.put("authKey", authKey);
                }
            }
        }
        return jsonObject.toJSONString();
    }

    /**
     * @param mail
     * @param authkey
     * @param password
     * @return
     */
    @Override
    public String setPw(String mail, String authkey, String password) {
        JSONObject jsonObject = new JSONObject();
        //检查mail和authkey
        if (!mail.matches(mailRegex) || authkey.equals("")) {
            //mail或authkey不满足条件
            jsonObject.put(errorCode, 101);
        } else if (!password.matches(passwordRegex)) {
            //password格式不符合需求
            jsonObject.put(errorCode, 102);
        } else {
            //获取数据库中的值
            if (userDao.findMailAuthkey(mail, authkey) == 0) {
                //无此数据
                jsonObject.put(errorCode, 201);
            } else {
                if (userDao.findBlog(mail)) {
                    //此账户为单blog账户，无需修改im账户
                    //修改sql
                    userDao.setPassword(mail, password);
                    jsonObject.put(errorCode, 0);
                } else {
                    //修改im账户密码
                    try {
                        //修改sql
                        userDao.setPassword(mail, password);
                        //获取token
                        Log.d(TAG, propertiesPath);
                        String token = PropertiesUtil.getPro(URLDecoder.decode(propertiesPath, "utf-8"), "access_token");
                        //获取username
                        User user = userDao.findUserM(mail);
                        if (user == null) {
                            //查询账户失败
                            jsonObject.put(errorCode, 202);
                        } else {
                            JSONObject imstr = new JSONObject();
                            imstr.put("newpassword", password);
                            //开始发送请求
                            Response response = OkHttpUntil.postJsonOkHttp(IMUrlData.EDIT_PASSWORD_URL + user.getUserid() + "/password",
                                    imstr.toJSONString(), token);
                            if (response.isSuccessful()) {
                                //成功修改
                                jsonObject.put(errorCode, 0);
                            } else {
                                //修改失败
                                Log.d(TAG, "code-->" + response.code());
                                Log.d(TAG, "msg-->" + response.body().string());
                                //重新获取token
                                Response response1 = OkHttpUntil.postJsonToken();
                                if (response1.isSuccessful()) {
                                    //获取到的新token
                                    String newtoken = JsonDataUntil.praseToken(response1.body().string());
                                    //开始发送请求
                                    System.out.println(IMUrlData.EDIT_PASSWORD_URL + user.getUserid() + "/password");
                                    Response response2 = OkHttpUntil.postJsonOkHttp(IMUrlData.EDIT_PASSWORD_URL + user.getUserid() + "/password",
                                            imstr.toJSONString(), newtoken);
                                    if (response2.isSuccessful()) {
                                        //储存token
                                        PropertiesUtil.updatePro(URLDecoder.decode(propertiesPath, "utf-8"), "access_token", newtoken);
                                        jsonObject.put(errorCode, 0);
                                    } else {
                                        //第二次修改失败
                                        Log.d(TAG, "code-->" + response2.code());
                                        Log.d(TAG, "msg-->" + response2.body().string());
                                        jsonObject.put(errorCode, 401);
                                    }
                                } else {
                                    //第二次获取token失败
                                    Log.d(TAG, "code-->" + response1.code());
                                    Log.d(TAG, "msg-->" + response1.body().string());
                                    jsonObject.put(errorCode, 402);
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        jsonObject.put(errorCode, 301);
                    }
                }
            }
        }
        return jsonObject.toJSONString();
    }

    /**
     * 获取用户列表
     *
     * @param useridJsonStr 上传的用户id list
     * @return json
     */
    @Override
    public String getContactsDataList(String useridJsonStr) {
        int errorCode = 0;
        int nullNum = 0;
        List<ContactsJson> contactsJsonList = new ArrayList<>();
        //判断jsonstr
        if (!useridJsonStr.equals("")) {
            //开始解析
            try {
                JSONArray jsonArray = JSON.parseArray(useridJsonStr);
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String userid = jsonObject.getString("userid");
                    if (!userid.equals("")) {
                        //开始查询
                        User user = userDao.findUserSingle(userid);
                        if (user != null && !user.getUserid().equals("")) {
                            //查询成功
                            ContactsJson contactsJson = new ContactsJson();
                            contactsJson.setUserid(user.getUserid());
                            contactsJson.setNickname(user.getNickname());
                            contactsJson.setProfilePhoto(user.getProfilephoto());
                            contactsJson.setIntroduce(user.getIntroduce());
                            contactsJson.setSex(user.getSex());
                            contactsJsonList.add(contactsJson);
                            errorCode = 0;
                        } else {
                            //查询为空
                            nullNum++;
                        }
                    } else {
                        //获取到的userid为空
                        errorCode = 201;
                        break;
                    }
                }
            } catch (Exception e) {
                //解析json错误
                errorCode = 102;
            }
        } else {
            //上传的json为空
            errorCode = 101;
        }
        ResContactsList resContactsList = new ResContactsList();
        resContactsList.setErrorCode(errorCode);
        resContactsList.setNullNum(nullNum);
        resContactsList.setContactsList(contactsJsonList);
        String jsonstr = JSON.toJSONString(resContactsList);
        Log.d(TAG, jsonstr);
        return jsonstr;
    }

    /**
     * 获取用户详细信息
     *
     * @param userid 用户id
     * @return json
     */
    @Override
    public String getContactsDetailed(String userid) {
        ResContacts resContacts = new ResContacts();
        //判断userid
        if (!userid.equals("")) {
            try {
                //查询数据
                User user = userDao.findUserSingle(userid);
                if (user != null && !user.getUserid().equals("")) {
                    resContacts.setErrorCode(0);
                    resContacts.setUserid(user.getUserid());
                    resContacts.setNickname(user.getNickname());
                    resContacts.setProfilephoto(user.getProfilephoto());
                    resContacts.setMail(user.getMail());
                    resContacts.setIntroduce(user.getIntroduce());
                    resContacts.setSex(user.getSex());
                    resContacts.setBirthday(user.getBirthday());
                    resContacts.setJoindate(user.getJoindate());
                    resContacts.setAddress(user.getAddress());
                } else {
                    //查询值为空
                    resContacts.setErrorCode(201);
                }
            } catch (Exception e) {
                e.printStackTrace();
                resContacts.setErrorCode(102);
            }
        } else {
            //userid为空
            resContacts.setErrorCode(101);
        }
        String resjson = JSON.toJSONString(resContacts);
        Log.d(TAG, resjson);
        return resjson;
    }

    /**
     * 上传用户头像
     *
     * @param request  请求
     * @param userid   用户id
     * @param password 密码
     * @param upload   图片文件
     * @return json
     */
    @Override
    public String uploadProfilePhoto(HttpServletRequest request, String userid, String password, MultipartFile upload) {
        //判断用户名密码
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(errorCode, 400);
        if (!userid.matches(userIdRegex)) {
            //userid错误
            jsonObject.put(errorCode, 101);
        } else if (!password.matches(passwordRegex)) {
            System.out.println(password);
            jsonObject.put(errorCode, 102);
        } else if (upload == null || request == null) {
            jsonObject.put(errorCode, 103);
        } else {
            //判断用户名密码是否正确
            String passwordDB = userDao.findPassword(userid);
            if (!passwordDB.equals(password)) {
                jsonObject.put(errorCode, 201);
            } else {
                try {
                    // 上传的位置
                    String path = request.getSession().getServletContext().getRealPath("/img/" + userid + "/");
                    // 判断，该路径是否存在
                    File file = new File(path);
                    if (!file.exists()) {
                        // 创建该文件夹
                        file.mkdirs();
                    }
                    // 把文件的名称设置唯一值，uuid
                    String filename = UUID.randomUUID().toString().replace("-", "");
                    System.out.println(filename);
                    // 完成文件上传
                    upload.transferTo(new File(path, filename));
                    //获取原文件
                    String profilename = userDao.findProfilePhoto(userid);
                    //删除原文件
                    File profile = new File(path+profilename);
                    if(profile.exists()&&profile.isFile()) {
                        boolean deleteFile = profile.delete();
                        Log.d(TAG, "文件删除"+path+filename+"状态"+deleteFile);
                    }
                    //写入数据库
                    if (userDao.setProfilePhoto(filename, userid) > 0){
                        //成功
                        jsonObject.put(errorCode, 0);
                    }else{
                        //失败
                        jsonObject.put(errorCode, 202);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    jsonObject.put(errorCode, 301);
                }
            }
        }
        String jsonstr = jsonObject.toJSONString();
        Log.d(TAG, jsonstr);
        return jsonstr;
    }

}
