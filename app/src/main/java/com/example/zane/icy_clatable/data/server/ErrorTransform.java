package com.example.zane.icy_clatable.data.server;

import com.kermit.exutils.utils.ExUtils;
import com.kermit.exutils.utils.LogUtils;

import okhttp3.Response;
import retrofit2.HttpException;
import rx.Observable;
import rx.functions.Action;
import rx.functions.Action1;

/**
 * Created by Zane on 16/4/9.
 * 更优雅的转换Observable去处理错误
 * Thanks For Jude
 */
public class ErrorTransform<T> implements Observable.Transformer<T, T>{

    private String TAG = "ErrorTransform";

    //错误处理对象
    private Action1<Throwable> errorHandler;

    @Override
    public Observable<T> call(Observable<T> tObservable) {

        //onErrorResumeNext:如果发生错误就会调用这个方法，然后返回一个空的并且会
        //直接调用Subscriber的onComplataed()方法的Observable
        return tObservable.doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

                //判断错误发生在哪里
                LogUtils.i(TAG, throwable.getClass().getName()+" "+throwable.getLocalizedMessage());
                String errorMessage;

                //通过状态码判断错误
                //由于后台给的状态信息很不规范，所以应该没有errorBody给我。科科
                //我就全部拿message了，几个message也不知道是什么意思。。。唉
                if (throwable instanceof Throwable) {
                    HttpException response = (HttpException) throwable;
                    switch (response.code()){
                        case 404:
                            errorMessage = "token无效"+response.message();
                            break;
                        case 402:
                            errorMessage = "数据库连接错误"+response.message();
                            break;
                        case 403:
                            errorMessage = "无记录"+response.message();
                            break;
                        case 400:
                            errorMessage = "参数为空"+response.message();
                            break;
                        default:
                            errorMessage = "未知错误"+response.message();
                            break;
                    }
                } else {
                    errorMessage = "网络错误";
                }

                ExUtils.Toast(errorMessage);
            }
        }).onErrorResumeNext(Observable.<T>empty());
    }
}
