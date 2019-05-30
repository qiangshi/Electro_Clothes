package com.business.electr.clothes.bean;

/**
 * Description: java类作用描述
 * Author: 曾海强
 * CreateDate: 2019/5/30 10:41
 */
public class TaskBean {

    /**
     * curHrateNum : 0
     * curDistance : 0
     * dayNo : 0
     * finishRate : 0.0
     * heartNum : 1
     * curStepNum : 0
     * stepNum : 1
     * sleepTime : 1.0
     */

    private int curHrateNum;
    private int curDistance;
    private int dayNo;
    private double finishRate;
    private int heartNum;
    private int curStepNum;
    private int stepNum;
    private double sleepTime;

    public int getCurHrateNum() {
        return curHrateNum;
    }

    public void setCurHrateNum(int curHrateNum) {
        this.curHrateNum = curHrateNum;
    }

    public int getCurDistance() {
        return curDistance;
    }

    public void setCurDistance(int curDistance) {
        this.curDistance = curDistance;
    }

    public int getDayNo() {
        return dayNo;
    }

    public void setDayNo(int dayNo) {
        this.dayNo = dayNo;
    }

    public double getFinishRate() {
        return finishRate;
    }

    public void setFinishRate(double finishRate) {
        this.finishRate = finishRate;
    }

    public int getHeartNum() {
        return heartNum;
    }

    public void setHeartNum(int heartNum) {
        this.heartNum = heartNum;
    }

    public int getCurStepNum() {
        return curStepNum;
    }

    public void setCurStepNum(int curStepNum) {
        this.curStepNum = curStepNum;
    }

    public int getStepNum() {
        return stepNum;
    }

    public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
    }

    public double getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(double sleepTime) {
        this.sleepTime = sleepTime;
    }
}
