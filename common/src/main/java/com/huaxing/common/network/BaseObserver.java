package com.huaxing.common.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.huaxing.common.bean.BaseRespose;
import com.socks.library.KLog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<BaseRespose<T>> {
    private Context mContext;
    private ProgressDialog mDialog;
    private Disposable mDisposable;
    public BaseObserver(){
    }
    public BaseObserver(Context context, ProgressDialog dialog){
        mContext = context;
        mDialog = dialog;
        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mDisposable.dispose();
            }
        });
    }
    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(BaseRespose<T> value) {
        KLog.d("---"+value.getMessage());
        if (value.success()){
            T t = value.getData();
            onHandleSuccess(t);
        }else {
            onInterceptError(value.getCode(),value.getMessage());
        }
    }

    @Override
    public void onError(Throwable e) {
        if(mDialog != null && mDialog.isShowing()){
            mDialog.cancel();
        }
        onInterceptError(0,"请求异常，请稍后重试");
    }

    @Override
    public void onComplete() {
//        if(mDialog != null && mDialog.isShowing()){
//            mDialog.cancel();
//        }
    }
    public abstract void onHandleSuccess(T t);
    public abstract void onHandleError(int code, String message);
    /**公共错误码处理**/
    void onInterceptError(int code, String message){
        onHandleError(code,message);
    }
}
