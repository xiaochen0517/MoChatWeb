package com.sxjdxy.mochat.until.loger;

import com.sxjdxy.mochat.until.SystemUntil;

/**
 * 功能：
 * 自定义loger日志
 * @author MoChen
 * Date  2019/11/26
 * @version 0.1
 */
public class Log {

    public static void d(String tag, String message){
        System.out.println(SystemUntil.getDate()+" : "+tag+" : "+message);
    }

    public static void e(String tag, String message){
        System.err.println(SystemUntil.getDate()+" : "+tag+"  :  "+message);
    }

}
