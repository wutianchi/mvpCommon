package com.huaxing.common.bean;

import java.io.Serializable;
/**
 * Created by wtc on 2017/7/20.
 * des:封装服务器返回数据
 */
public class BaseRespose<T> implements Serializable {
    private String message;
    private int code;
    private T data;
    public boolean success() {
        return 200==code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
