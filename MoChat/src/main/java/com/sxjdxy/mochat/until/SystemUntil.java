package com.sxjdxy.mochat.until;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/11/26
 * @version 0.1
 */
public class SystemUntil {

    public static String getDate(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(System.currentTimeMillis());
    }

}
