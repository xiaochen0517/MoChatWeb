package com.sxjdxy.mochat.until.system;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/11/26
 * @version 0.1
 */
public class SystemUntil {

    /**
     * 获取时间
     * @return
     */
    public static String getDate(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(System.currentTimeMillis());
    }

    /**
     * 获取验证码 6位
     * @return
     */
    public static String getAuthCode(){
        String date = new Date().getTime()+"";
        return date.substring(7);
    }

}
