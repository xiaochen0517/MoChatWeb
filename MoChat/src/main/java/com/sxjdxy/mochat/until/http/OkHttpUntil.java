package com.sxjdxy.mochat.until.http;

import com.sxjdxy.mochat.data.IMUrlData;
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
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();

         return client.newCall(request).execute();
    }

    public static Response postJsonOkHttp(String url, String jsonstr, String authorization) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, jsonstr);
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer "+authorization)
                .put(requestBody)
                .build();
        return client.newCall(request).execute();
    }

    public static Response postJsonToken() throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, "{" +
                "\"grant_type\": \"client_credentials\"," +
                "\"client_id\": \""+ IMUrlData.CLIENT_ID +"\"," +
                "\"client_secret\": \""+IMUrlData.CLIENT_SECRET+"\"" +
                "}");
        Request request = new Request.Builder()
                .url("http://a1.easemob.com/1114191121181676/mochat/token")
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();

        return client.newCall(request).execute();
    }

}
