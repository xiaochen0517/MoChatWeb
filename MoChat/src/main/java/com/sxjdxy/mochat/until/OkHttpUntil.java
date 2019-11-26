package com.sxjdxy.mochat.until;

import okhttp3.*;

import java.io.IOException;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/11/25
 * @version 0.1
 */
public class OkHttpUntil {

    public static Response postJsonOkHttp(String url, String jsonstr) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, jsonstr);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

         return client.newCall(request).execute();
    }

}
