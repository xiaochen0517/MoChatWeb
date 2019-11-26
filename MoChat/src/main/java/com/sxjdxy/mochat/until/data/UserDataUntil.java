package com.sxjdxy.mochat.until.data;

import com.sxjdxy.mochat.data.UserData;

/**
 * 功能：
 * 用户数据检查类
 *
 * @author MoChen
 * Date  2019/11/26
 * @version 0.1
 */
public class UserDataUntil {


    private static boolean judgeUserId(String userid) {
        String regex = "[a-zA-Z][a-zA-Z0-9]{4,11}";
        if (userid.equals("") || userid.length() < UserData.USERID_MIN_LENGTH ||
                userid.length() > UserData.USERID_MAX_LENGTH || !userid.matches(regex)) {
            return false;
        } else {
            return true;
        }
    }

}
