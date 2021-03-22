package com.yn.electricity.okhttp;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * @author 请求
 */
@Slf4j
@Component
public class OkHttpClientUtil {

    @Resource
    private OkHttpClient okHttpClient;

    public String getParams(String url, LinkedHashMap<String, Object> params) {
        Iterator<String> keys = params.keySet().iterator();
        Iterator<Object> values = params.values().iterator();
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("?");

        for (int i = 0; i < params.size(); i++) {
            String value = null;
            try {
                value = URLEncoder.encode(values.next().toString(), "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            stringBuffer.append(keys.next()).append("=").append(value);
            if (i != params.size() - 1) {
                stringBuffer.append("&");
            }
        }
        return url + stringBuffer.toString();
    }


    public String httpGet(String baseUrl, String url) {
        Request.Builder builder = new Request.Builder();
        Request request = builder.addHeader("Content-Type", "application/json")
                .addHeader("AppVersion", "v1.0")
                .url(baseUrl + url)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            ResponseBody body = response.body();
            if (body != null) {
                return body.string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param url    url
     * @param params 参数
     * @return string
     */
    public String httpPost(String baseUrl, String url, LinkedHashMap<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            Iterator<String> keys = params.keySet().iterator();
            Iterator<String> values = params.values().iterator();
            for (int i = 0; i < params.size(); i++) {
                String key = keys.next();
                String value = values.next();
                log.info("post 参数 key = {}   value = {}", key, value);
                builder.add(key, value);
            }
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(baseUrl + url)
                .addHeader("Content-Type", "application/json")
                .addHeader("AppVersion", "v1.0")
                .post(requestBody).build();

        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            ResponseBody body = response.body();
            if (body != null) {
                int code = response.code();
                if (code != HttpCodeEnum.HTTP_CODE_200.getCode()) {
                    log.info("请求失败 ： {}", body.string());
                    return null;
                }
                return body.string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSON 方式发送
     *
     * @param url  url
     * @param gson gson
     * @return String
     */
    public String httpPost(String baseUrl, String url, String gson) {
        MediaType json = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(json, gson);
        Request request = new Request.Builder()
                .url(baseUrl + url)
                .addHeader("Content-Type", "application/json")
                .addHeader("AppVersion", "v1.0")
                .post(body)
                .build();
        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                return responseBody.string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
