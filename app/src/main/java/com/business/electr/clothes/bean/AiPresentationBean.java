package com.business.electr.clothes.bean;

/**
 * @ClassName: AiPresentationBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/5/19 14:24
 */
public class AiPresentationBean {
    private String title;//标题
    private String recordTime;//记录时间


    public AiPresentationBean(String title, String recordTime) {
        this.title = title;
        this.recordTime = recordTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }
}
