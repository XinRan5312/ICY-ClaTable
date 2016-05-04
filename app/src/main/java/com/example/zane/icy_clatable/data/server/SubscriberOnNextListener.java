package com.example.zane.icy_clatable.data.server;

/**
 * Created by Zane on 16/5/4.
 * 最终响应onNext的接口，在调用的地方实现方法
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}
