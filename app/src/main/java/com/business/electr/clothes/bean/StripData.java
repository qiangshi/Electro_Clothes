package com.business.electr.clothes.bean;

/**
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/5/7 16:08
 */
public class StripData {

    private int time;// 0 1 2 3 4 5 6 7 8 9 10 ... 140 141 142 143

    private int elect;

    public StripData(int time, int elect) {
        this.time = time;
        this.elect = elect;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getElect() {
        return elect;
    }

    public void setElect(int elect) {
        this.elect = elect;
    }
}
