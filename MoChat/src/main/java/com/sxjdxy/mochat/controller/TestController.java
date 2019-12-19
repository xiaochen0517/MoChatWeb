package com.sxjdxy.mochat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/11/24
 * @version 0.1
 */
@Controller
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.DELETE,
            produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public String getTest(){
        System.out.println("get test");
        return "中文";
    }

}
