package com.sxjdxy.mochat.test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/11/24
 * @version 0.1
 */
public class TestOkHttp {

    @Test
    public void testOkHttp(){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url("http://www.baidu.com")
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()){
                System.out.println(response.body().string());
            }else{
                System.out.println("faile");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
