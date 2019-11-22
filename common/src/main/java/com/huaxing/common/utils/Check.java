package com.huaxing.common.utils;

import java.util.List;

public class Check {
    public static <T> boolean isNull(T t) {
        return t == null;
    }

    public static boolean isEmpty(List t) {
        return t == null || t.size() == 0;
    }

    public static <T> void checkNull(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
    }
}
