package com.example.zane.icy_clatable.app;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.kermit.exutils.utils.ExUtils;
import com.kermit.exutils.utils.LogUtils;

import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

/**
 * Created by Zane on 16/3/14.
 */
public class App extends Application{

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        ExUtils.initialize(this);
        LogUtils.DEBUG = true;
        instance = this;
        Stetho.initializeWithDefaults(this);

    }

    public static App getInstance(){
        return instance;
    }
}
