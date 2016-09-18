package com.example.zane.icy_clatable.data.server;

import android.util.Log;
import android.widget.Toast;

import com.example.zane.icy_clatable.app.App;

import java.util.ServiceConfigurationError;

import retrofit2.HttpException;
import rx.plugins.RxJavaErrorHandler;

/**
 * Created by Zane on 16/9/17.
 * Email: zanebot96@gmail.com
 */

public class MyRxErrorPlugin extends RxJavaErrorHandler{
    @Override
    public void handleError(Throwable throwable) {
        //判断异常是什么类型
        //Log.i(TAG, throwable.getClass().getName()+" "+throwable.getLocalizedMessage()+" "+throwable.getMessage());
        String errorMessage = "";
        //通过状态码判断错误
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
}
