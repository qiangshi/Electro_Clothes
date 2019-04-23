package com.business.electr.clothes.net;

/**
 * Created by pantianxiong on 2018/4/23.
 * 描述：
 */
public class BaseApiResponse<T> {
    private String code;

    private T data;

    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
