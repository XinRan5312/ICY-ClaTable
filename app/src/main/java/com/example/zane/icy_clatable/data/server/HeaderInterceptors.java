package com.example.zane.icy_clatable.data.server;

import android.util.Log;

import com.example.zane.icy_clatable.data.bean.Clazz_Two;
import com.google.gson.Gson;
import com.kermit.exutils.utils.NetUtils;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

/**
 * Created by Zane on 16/3/14.
 * 拦截,http缓存
 */
public class HeaderInterceptors implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //更改请求头
        if (!NetUtils.hasNetwork()){
            //如果没有网络，那么就强制使用缓存数据
            request = request.newBuilder()
                              .cacheControl(CacheControl.FORCE_CACHE)
                              .build();
        }

        //获得响应头，如果有网络，就缓存一分钟,没有网络缓存四周
        Response originalResponse = chain.proceed(request);

        //先解析一遍json数据，根据status和message去手动改状态码和描述
//        String content = originalResponse.body().string();
//        int code = 200;
//        String message = "";
//        JSONObject clazzTwo = null;
//        try {
//
//            clazzTwo = new JSONObject(content);
//            message = clazzTwo.getString("message");
//
//            switch (clazzTwo.getInt("status")){
//                case 200:
//                    code = 200;
//                    break;
//                case 403:
//                    code = 403;
//                    break;
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        //更改响应头
        String cacheControl = request.cacheControl().toString();
        return originalResponse.newBuilder()
                       .header("Cache-Control", cacheControl)
                       .removeHeader("Pragma")
                       .build();

    }
}
