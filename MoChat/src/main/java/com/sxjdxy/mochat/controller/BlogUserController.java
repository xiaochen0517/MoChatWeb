package com.sxjdxy.mochat.controller;

import com.sxjdxy.mochat.service.service.BlogUserService;
import com.sxjdxy.mochat.until.loger.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/12/10
 * @version 0.1
 */
@Controller
@RequestMapping("/bloguser")
public class BlogUserController {

    private static final String TAG = "BlogUserController";

    @Autowired
    private BlogUserService blogUserService;

    /**
     * 用户登录
     * @param userid 用户id
     * @param password 密码
     * @return json
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST,
            produces = {"text/html;charset=UTF-8;", "application/json;"})
    @ResponseBody
    private String login(@RequestParam("userid")String userid, @RequestParam("password")String password){
        Log.d(TAG, "用户登录请求");
        return blogUserService.login(userid, password);
    }

    /**
     * 用户注销
     * @param authkey 验证密匙
     * @return json
     */
    @RequestMapping(value = "/logout", method = RequestMethod.DELETE,
            produces = {"text/html;charset=UTF-8;", "application/json;"})
    @ResponseBody
    private String logout(@RequestParam("authkey")String authkey){
        Log.d(TAG, "用户注销请求");
        return blogUserService.logout(authkey);
    }

}
