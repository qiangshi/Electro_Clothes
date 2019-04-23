package com.business.electr.clothes.bean;

public class DataEvent {

    public static final int TYPE_LOGIN = 0;
    public static final int TYPE_NEW_MSG = 1;
    public static final int TYPE_CLEAR_UNREADMSG = 2;
    public static final int TYPE_CHANGE_USERINFO = 3;
    public static final int TYPE_CHANGE_INFO_DOT = 4;

    private int type;
    private Object data;

    public DataEvent(int type, Object data) {
        this.type = type;
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
