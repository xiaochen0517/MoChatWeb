package com.sxjdxy.mochat.controller;

import com.sxjdxy.mochat.service.service.UserService;
import com.sxjdxy.mochat.until.loger.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能：
 * 用户控制层
 *
 * @author MoChen
 * Date  2019/11/25
 * @version 0.1
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final static String TAG = "UserController";

    @Autowired
    private UserService userService;

    /**
     * 注册用户blog and im
     *
     * @param userid   userid
     * @param nickname 昵称
     * @param password 密码
     * @return json结果
     */
    @RequestMapping(value = "/adduser",
            method = RequestMethod.POST,
            produces = {"text/html;charset=UTF-8;", "application/json;"})
    @ResponseBody
    private String addUser(@RequestParam("userid") String userid, @RequestParam("nickname") String nickname, @RequestParam("password") String password, @RequestParam("mail") String mail) {
        System.out.println("user-->adduser-->用户注册请求");
        return userService.addUser(userid, nickname, password, mail);
    }

    /**
     * 邮件验证地址
     *
     * @param userid  用户id
     * @param authkey 验证key
     * @return 返回信息
     */
    @RequestMapping(value = "/verifymail", method = RequestMethod.GET,
            produces = {"text/html;charset=UTF-8;", "application/json;"})
    @ResponseBody
    private String verifyMail(@RequestParam("userid") String userid, @RequestParam("authkey") String authkey) {
        System.out.println("user-->verifyMail-->邮箱验证请求");
        System.out.println("userid:" + userid + "\nauthkey:" + authkey);
        String ret = userService.verifyMail(userid, authkey);
        System.out.println("return-->" + ret);
        return ret;
    }

    /**
     * 修改密码提交
     *
     * @param mail
     * @return
     */
    @RequestMapping(value = "/editpw", method = RequestMethod.POST,
            produces = {"text/html;charset=UTF-8;", "application/json;"})
    @ResponseBody
    private String editPw(@RequestParam("mail") String mail) {
        Log.d(TAG, "修改密码接口-->" + mail);
        String restr = userService.editPw(mail);
        Log.d(TAG, "restr-->" + restr);
        return restr;
    }

    /**
     * 验证修改密码code
     *
     * @param mail
     * @param authcode
     * @return
     */
    @RequestMapping(value = "/verifyeditpw", method = RequestMethod.POST,
            produces = {"text/html;charset=UTF-8;", "application/json;"})
    @ResponseBody
    private String verifyEditPw(@RequestParam("mail") String mail, @RequestParam("authcode") String authcode) {
        Log.d(TAG, "修改密码验证接口-->" + mail);
        String restr = userService.verifyEditPw(mail, authcode);
        Log.d(TAG, "restr-->" + restr);
        return restr;
    }

    /**
     * 修改密码
     *
     * @param mail
     * @return
     */
    @RequestMapping(value = "/setpw", method = RequestMethod.POST,
            produces = {"text/html;charset=UTF-8;", "application/json;"})
    @ResponseBody
    private String setPw(@RequestParam("mail") String mail, @RequestParam("authkey") String authkey, @RequestParam("password") String password) {
        Log.d(TAG, "修改密码接口-->" + mail);
        String restr = userService.setPw(mail, authkey, password);
        Log.d(TAG, "restr-->" + restr);
        return restr;
    }

    /**
     * 获取用户信息列表
     * @param jsonstr 用户id，上传的数据为json字符串，包含一个userid数组，需要解析数组并返回所有用户信息
     * @return 用户信息
     */
    @RequestMapping(value = "/userlist", method = RequestMethod.POST,
            produces = {"text/html;charset=UTF-8;", "application/json;"})
    @ResponseBody
    private String getContactsDataList(@RequestBody String jsonstr) {
        Log.d(TAG, "获取用户信息列表 jsonstr -- >"+jsonstr);
        return userService.getContactsDataList(jsonstr);
    }

    /**
     * 获取指定用户详细信息
     * @param userid 用户id
     * @return json
     */
    @RequestMapping(value = "/userdetailed", method = RequestMethod.POST,
            produces = {"text/html;charset=UTF-8;", "application/json;"})
    @ResponseBody
    private String getContactsDetailed(@RequestParam("userid") String userid){
        Log.d(TAG, "获取指定用户详细信息 userid -- >"+userid);
        return userService.getContactsDetailed(userid);
    }

}
