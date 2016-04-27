package com.example.zane.icy_clatable.data.server;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ServiceConfigurationError;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Zane on 16/3/14.
 * 拦截,http缓存
 */
public class HeaderInterceptors implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        //获得响应头，如果有网络，就缓存一分钟,没有网络缓存四周
        Response originalResponse = chain.proceed(request);
        MediaType contentType = originalResponse.body().contentType();

        //先解析一遍json数据，根据status和message去手动改状态码和描述
        String originalContent = originalResponse.body().string();

        int code ;
        String message ;
        String body ;

        JSONObject wrapper = null;
        try {

            wrapper = new JSONObject(originalContent);
            message = wrapper.getString("message");
            code = wrapper.getInt("status");
            body = wrapper.getString("data");

        } catch (JSONException e) {

            throw new ServiceConfigurationError("服务器错误："+e.getLocalizedMessage());

        }

        //更改响应头,强制添加缓存
        String cacheControl = request.cacheControl().toString();
        return originalResponse.newBuilder()
                       .code(code)
                       .message(message)
                       .body(ResponseBody.create(contentType, body))
                       .header("Cache-Control", cacheControl)
                       .removeHeader("Pragma")
                       .build();

    }
}
