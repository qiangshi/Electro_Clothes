package com.business.electr.clothes.net;

/**
 * Created by pantianxiong on 2018/4/23.
 * 描述：
 */
public class BaseApiResponse<T> {
    private int status;//状态  0 ： 成功
    private String msg;//消息
    private long id;//命令号
    private int seq;//流水号
    private T data;//数据


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
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
