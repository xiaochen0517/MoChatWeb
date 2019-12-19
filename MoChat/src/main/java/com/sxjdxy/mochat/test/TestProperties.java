package com.sxjdxy.mochat.test;

import com.sxjdxy.mochat.until.properties.PropertiesUntil;
import org.junit.Test;

import java.io.IOException;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/11/27
 * @version 0.1
 */
public class TestProperties {

    @Test
    public void TestPro() {
        String path = this.getClass().getClassLoader().getResource("user.properties").getPath();
        System.out.println(path);
        try {
            new PropertiesUntil.Builder().updatePro(path, "users", "abc");
            PropertiesUntil propertiesUntil = new PropertiesUntil(path);
            System.out.println(propertiesUntil.getPro("users"));
            propertiesUntil.delPro("users");
            System.out.println(propertiesUntil.getPro("users"));
            propertiesUntil.setPro("users", "qwer");
            propertiesUntil.commit();
            System.out.println(propertiesUntil.getPro("users"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        URL url = this.getClass().getClassLoader().getResource("imtoken.properties");
//        System.out.println(url.getPath());
//        String a = Thread.currentThread().getContextClassLoader().getResource("imtoken.properties").getPath();
//        System.out.println(a);
    }

}
