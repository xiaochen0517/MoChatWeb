package com.sxjdxy.mochat.test;

import com.sxjdxy.mochat.until.http.MailUntil;
import org.junit.Test;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/11/26
 * @version 0.1
 */
public class TestMail {

    @Test
    public void testMail(){
        MailUntil.sendAddUserMail("1453215", "2827075398@qq.com");
    }

}
