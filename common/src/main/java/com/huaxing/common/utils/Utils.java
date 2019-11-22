package com.huaxing.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;

/**
 * Created by wtc on 2019/11/21.
 * 常用工具类
 */
public final class Utils {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static Resources resources;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull final Context context) {
        Utils.context = context.getApplicationContext();
        Utils.resources = context.getApplicationContext().getResources();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("should be initialized in application");
    }
}
