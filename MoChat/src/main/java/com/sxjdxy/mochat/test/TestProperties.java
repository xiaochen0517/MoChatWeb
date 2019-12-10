package com.sxjdxy.mochat.test;

import com.sxjdxy.mochat.until.PropertiesUtil;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/11/27
 * @version 0.1
 */
public class TestProperties {

    @Test
    public void TestPro(){
//        try {
//            String a = PropertiesUtil.getPro("imtoken.properties", "access_token");
//            System.out.println(a);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        URL url = this.getClass().getClassLoader().getResource("imtoken.properties");
//        System.out.println(url.getPath());
        String a = Thread.currentThread().getContextClassLoader().getResource("imtoken.properties").getPath();
        System.out.println(a);
    }

}
