package com.example.zane.icy_clatable.data.server;

import com.kermit.exutils.utils.NetUtils;


import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

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
        //更改响应头
        if (NetUtils.hasNetwork()){
            String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder()
                           .header("Cache-Control", cacheControl)
                           .removeHeader("Pragma")
                           .build();
        }else {
            return originalResponse.newBuilder()
                           .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                           .removeHeader("Pragma")
                           .build();
        }
    }
}
