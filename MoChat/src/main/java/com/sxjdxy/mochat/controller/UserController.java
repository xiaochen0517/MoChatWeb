package com.sxjdxy.mochat.controller;

import com.sxjdxy.mochat.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能：
 * 用户控制层
 * @author MoChen
 * Date  2019/11/25
 * @version 0.1
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *
     * @param userid
     * @param nickname
     * @param password
     * @return
     */
    @RequestMapping(value = "/adduser",
            method = RequestMethod.POST)
    @ResponseBody
    private String addUser(@RequestParam("userid") String userid, @RequestParam("nickname") String nickname, @RequestParam("password") String password, @RequestParam("mail") String mail) {
        System.out.println("user-->adduser-->用户注册请求");
        return userService.addUser(userid, nickname, password, mail);
    }


    @RequestMapping(value = "/verifymail", method = RequestMethod.GET)
    @ResponseBody
    private  String verifyMail(@RequestParam("userid") String userid, @RequestParam("authkey") String authkey){
        System.out.println("user-->verifyMail-->邮箱验证请求");
        System.out.println("userid:"+userid+"\nauthkey:"+authkey);
        return "success";
    }

}
