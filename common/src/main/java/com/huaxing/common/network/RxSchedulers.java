package com.huaxing.common.network;

import com.huaxing.common.utils.NetWorkUtils;
import com.huaxing.common.utils.ToastUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxSchedulers {
    public static <T> ObservableTransformer<T, T> compose(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.throttleFirst(500, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                if (!NetWorkUtils.isNetConnected()){
                                    ToastUtils.showShort("请检查您的网络连接");
                                }
                            }
                        }).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
