package com.example.zane.icy_clatable.data;

import android.content.Context;

import com.example.zane.icy_clatable.app.App;
import com.example.zane.icy_clatable.config.Config;
import com.example.zane.icy_clatable.data.bean.Clazz;
import com.example.zane.icy_clatable.data.server.SchedulerTransform;
import com.example.zane.icy_clatable.data.server.ServiceApi;
import com.example.zane.icy_clatable.utils.FileUtil;
import com.kermit.exutils.utils.NetUtils;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
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

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetUtils.hasNetwork()){
                    //如果没有网络，那么就强制使用缓存数据
                    request = request.newBuilder()
                                      .cacheControl(CacheControl.FORCE_CACHE)
                                      .build();
                }
                //获得返回头，如果有网络，就缓存一分钟,没有网络缓存四周
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
        };

        Cache cache = new Cache(FileUtil.getDiskCacheDir("response"), 1024*1024*10);

        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(interceptor);
        client.interceptors().add(interceptor);
        client.setCache(cache);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(ServiceApi.class);

    }

    public Observable<Clazz> getClassData(String userId){
        return providesClassDataService()
                .getClassData(userId)
                .compose(new SchedulerTransform<Clazz>());
    }

}
