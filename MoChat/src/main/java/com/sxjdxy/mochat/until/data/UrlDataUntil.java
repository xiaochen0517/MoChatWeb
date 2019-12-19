package com.sxjdxy.mochat.until.data;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/12/19
 * @version 0.1
 */
public class UrlDataUntil {

    public static String getPropertiesPath(String filename){
        try {
            String propertiesPath = Thread.currentThread().getContextClassLoader().getResource(filename).getPath();
            return URLDecoder.decode(propertiesPath, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
