package com.business.electr.clothes.bean;

/**
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/5/22 14:37
 */
public class HistoryTaskBean {

    private String recordTime;//记录时间

    private int state;//0 : 未完成   1 ：已完成

    private String sleepTime;//睡觉时间

    private String electNum;//心跳数

    private String number;//步数

    public HistoryTaskBean(String recordTime, int state, String sleepTime, String electNum, String number) {
        this.recordTime = recordTime;
        this.state = state;
        this.sleepTime = sleepTime;
        this.electNum = electNum;
        this.number = number;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getElectNum() {
        return electNum;
    }

    public void setElectNum(String electNum) {
        this.electNum = electNum;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
