package com.sxjdxy.mochat.until.data;

import org.springframework.util.DigestUtils;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/11/26
 * @version 0.1
 */
public class MD5DataUntil {

    //加入一个盐值，用户混淆
    private final static String salt = "sdaf6546y65l;uq234o;i";

    public static String getMd5(String message){
        String base = message + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

}
