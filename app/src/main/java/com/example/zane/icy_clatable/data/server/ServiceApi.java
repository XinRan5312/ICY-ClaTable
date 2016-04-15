package com.example.zane.icy_clatable.data.server;

import com.example.zane.icy_clatable.data.bean.Clazz_Two;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Zane on 16/3/14.
 */
public interface ServiceApi {

    /**
     *
     * @param userId ID
     * @param kind bks or yjs 本科生或者研究生
     * @return
     */
    @Headers("Cache-Control: public, max-age=3600")
    @GET("kebiaonew.php")
    Observable<List<Clazz_Two.DataEntity>> getClassData(@Query("id") String userId, @Query("kind") String kind);
    
}
