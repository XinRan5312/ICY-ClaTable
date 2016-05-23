package com.example.zane.icy_clatable.data.server;

import android.util.Log;
import android.widget.Toast;

import com.example.zane.icy_clatable.app.App;
import com.kermit.exutils.utils.ExUtils;
import com.kermit.exutils.utils.LogUtils;

import java.util.ServiceConfigurationError;

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

    private static final String TAG = "ErrorTransform";

    @Override
    public Observable<T> call(Observable<T> tObservable) {

        //onErrorResumeNext:如果发生错误就会调用这个方法，然后返回一个空的并且会
        //直接调用Subscriber的onComplataed()方法的Observable
        return tObservable.doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

                //判断异常是什么类型
                Log.i(TAG, throwable.getClass().getName()+" "+throwable.getLocalizedMessage()+" "+throwable.getMessage());
                String errorMessage = "";
                //通过状态码判断错误
                //由于后台给的状态信息很不规范，所以应该没有errorBody给我。科科
                //我就全部拿message了，几个message也不知道是什么意思。。。唉
                if (throwable instanceof HttpException) {
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
                }else if (throwable instanceof ServiceConfigurationError){
                    errorMessage = "服务器错误";
                } else {
                    errorMessage = "网络错误";
                }

                Toast.makeText(App.getInstance(), errorMessage, Toast.LENGTH_SHORT).show();

            }
        }).onErrorResumeNext(Observable.<T>empty());
    }
}
