package com.sxjdxy.mochat.until.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/11/27
 * @version 0.1
 */
public class JsonDataUntil {

    public static String praseToken(String jsonstr){
        JSONObject jsonObject = JSON.parseObject(jsonstr);
        try {
            String token = jsonObject.getString("access_token");
            return token;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
