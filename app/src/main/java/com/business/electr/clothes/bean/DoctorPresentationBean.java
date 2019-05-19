package com.business.electr.clothes.bean;

/**
 * @ClassName: DoctorPresentationBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/5/19 15:47
 */
public class DoctorPresentationBean {

    private String HeardImagUrl;

    private String nickName;

    private String commtentTime;

    private String position;//职位

    private String content;

    public DoctorPresentationBean(String heardImagUrl, String nickName, String commtentTime, String position, String content) {
        HeardImagUrl = heardImagUrl;
        this.nickName = nickName;
        this.commtentTime = commtentTime;
        this.position = position;
        this.content = content;
    }

    public String getHeardImagUrl() {
        return HeardImagUrl;
    }

    public void setHeardImagUrl(String heardImagUrl) {
        HeardImagUrl = heardImagUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCommtentTime() {
        return commtentTime;
    }

    public void setCommtentTime(String commtentTime) {
        commtentTime = commtentTime;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}


