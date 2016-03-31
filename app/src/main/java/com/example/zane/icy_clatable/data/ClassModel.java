package com.example.zane.icy_clatable.data;

import android.content.Context;

import com.example.zane.icy_clatable.app.App;
import com.example.zane.icy_clatable.config.ServiceApiConfig;
import com.example.zane.icy_clatable.data.bean.Clazz;
import com.example.zane.icy_clatable.data.server.HeaderInterceptors;
import com.example.zane.icy_clatable.data.server.SchedulerTransform;
import com.example.zane.icy_clatable.data.server.ServiceApi;
import com.facebook.stetho.okhttp3.StethoInterceptor;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by Zane on 16/3/14.
 */
public class ClassModel {

    private Context mContext;

    public ClassModel(){
        mContext = App.getInstance();
    }

    static class SingletonHolder{
        private static final ClassModel classModel = new ClassModel();
    }

    public static ClassModel getInstance(){
        return SingletonHolder.classModel;
    }

    public ServiceApi providesClassDataService(){

        //添加body日志打印，http，stetho调试的拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new HeaderInterceptors())
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceApiConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();


        return retrofit.create(ServiceApi.class);

    }

    public Observable<Clazz> getClassData(String userId){
        return providesClassDataService()
                .getClassData(userId)
                .compose(new SchedulerTransform<Clazz>());
    }

}
