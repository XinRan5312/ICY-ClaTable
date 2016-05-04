package com.example.zane.icy_clatable.data.server;

import com.kermit.exutils.utils.LogUtils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Zane on 16/3/14.
 * 线程转换器
 */
public class SchedulerTransform<T> implements Observable.Transformer<T, T> {

    private static final String TAG = "SchedulerTransform" ;

    @Override
    public Observable<T> call(Observable<T> tObservable) {
        return tObservable
                       .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .unsubscribeOn(Schedulers.io());
    }
}
