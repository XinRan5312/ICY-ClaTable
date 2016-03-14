package com.example.zane.icy_clatable.data.server;

import com.example.zane.icy_clatable.data.bean.Clazz;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Zane on 16/3/14.
 */
public interface ServiceApi {

    @Headers("Cache-Control: public, max-age=3600")
    @GET("kebiao.php")
    Observable<Clazz> getClassData(@Query("id") String userId);
    
}
