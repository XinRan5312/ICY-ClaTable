package com.example.zane.icy_clatable.data.server.progress;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Zane on 16/5/4.
 * 用来响应异步请求过程中对于progress的操作
 */
public class ProgressDialogHandler extends Handler{

    public static final int SHOW_PROGRESS = 1;
    public static final int DISSMISS_PROGRESS = 2;

    private ProgressCancelListener progressCancelListener;
    private boolean isCancel;
    private ProgressDialog progressDialog;
    //dialog最好不用application的context
    private Context context;

    public ProgressDialogHandler(Context context, ProgressCancelListener listener, boolean isCancel){
        //忘记了super()也没事，反正是在主线程
        super();
        this.progressCancelListener = progressCancelListener;
        this.isCancel = isCancel;
        this.context = context;
    }

    public void showProgressDialod(){
        if (progressDialog == null){
            progressDialog = new ProgressDialog(context);
        }

        progressDialog.setCancelable(isCancel);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //调用接口方法，取消订阅
                progressCancelListener.cancelProgress();
            }
        });
        if (!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    public void dissmissProgressDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    //处理外部message
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case SHOW_PROGRESS:
                showProgressDialod();
                break;
            case DISSMISS_PROGRESS:
                dissmissProgressDialog();
                break;
        }
    }
}
