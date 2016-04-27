package com.example.zane.icy_clatable.app;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.kermit.exutils.utils.ExUtils;
import com.kermit.exutils.utils.LogUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

/**
 * Created by Zane on 16/3/14.
 */
public class App extends Application{

    private static App instance;
    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        ExUtils.initialize(this);
        LogUtils.DEBUG = true;
        instance = this;
        Stetho.initializeWithDefaults(this);

        RxJavaPlugins.getInstance().registerErrorHandler(new RxJavaErrorHandler() {
            @Override
            public void handleError(Throwable e) {
                Log.w("Error",e);
            }
        });

        mRefWatcher = LeakCanary.install(this);

    }

    public static App getInstance(){
        return instance;
    }
}
