package com.sxjdxy.mochat.controller;

import com.sxjdxy.mochat.service.service.BlogUserService;
import com.sxjdxy.mochat.until.loger.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;

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

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            produces = {"text/html;charset=UTF-8;", "application/json;"})
    @ResponseBody
    private String login(@RequestParam("userid")String usrid, @RequestParam("password")String password){
        Log.d(TAG, "用户登录请求");
        return "";
    }

}
