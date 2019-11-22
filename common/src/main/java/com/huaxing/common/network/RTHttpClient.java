package com.huaxing.common.network;

import android.content.Context;

import com.huaxing.common.utils.SPUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wtc on 2019/11/22.
 * 简化后的 Retrofit HTTP 客户端调用类.
 */
public class RTHttpClient {
    public static Retrofit retrofit = null;
    public static String access_token= SPUtils.getInstance().getString("Token");//暂定
    public static void init(String baseURL, Context context){
        access_token= SPUtils.getInstance().getString("Token");
        //开启Log
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //全局增加头部信息
        Interceptor mTokenInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                if (access_token.equals("")) {
                    return chain.proceed(originalRequest);
                }
                Request authorised = originalRequest.newBuilder()
                        .header("x-auth-token",access_token)
                        .build();
                return chain.proceed(authorised);
            }
        };
        // OkHttp3.0的使用方式
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addNetworkInterceptor(mTokenInterceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }
    public static <T> T create(Class<T> service) {
        return retrofit.create(service);
    }
}
