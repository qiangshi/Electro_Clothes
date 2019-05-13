package com.business.electr.clothes.bean;

/**
 * @ClassName: HistoryBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/5/12 18:33
 */
public class HistoryBean {

    private String timeDes;
    private String timeDistance;
    private String state;
    private boolean isSelect;//是否选择


    public HistoryBean(String timeDes, String timeDistance, String state, boolean isSelect) {
        this.timeDes = timeDes;
        this.timeDistance = timeDistance;
        this.state = state;
        this.isSelect = isSelect;
    }

    public String getTimeDes() {
        return timeDes;
    }

    public void setTimeDes(String timeDes) {
        this.timeDes = timeDes;
    }

    public String getTimeDistance() {
        return timeDistance;
    }

    public void setTimeDistance(String timeDistance) {
        this.timeDistance = timeDistance;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
