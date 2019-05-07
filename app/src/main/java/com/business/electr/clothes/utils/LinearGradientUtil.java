package com.business.electr.clothes.utils;

import android.graphics.Color;

/**
 * @Description: 获取颜色渐变中的色值
 * @Author: 曾海强
 * @CreateDate: 2019/5/7 13:18
 */
public class LinearGradientUtil {

    private int mStartColor;
    private int mMiddleColor;
    private int mEndColor;

    public LinearGradientUtil(int startColor, int middleColor, int endColor) {
        this.mStartColor = startColor;
        this.mMiddleColor = middleColor;
        this.mEndColor = endColor;
    }

    public void setStartColor(int startColor) {
        this.mStartColor = startColor;
    }

    public void setMiddleColor(int middleColor) {
        this.mMiddleColor = middleColor;
    }

    public void setEndColor(int endColor) {
        this.mEndColor = endColor;
    }

    public int getColor(int startColor,int endColor,float radio) {
        int redStart = Color.red(startColor);
        int blueStart = Color.blue(startColor);
        int greenStart = Color.green(startColor);
        int redEnd = Color.red(endColor);
        int blueEnd = Color.blue(endColor);
        int greenEnd = Color.green(endColor);

        int red = (int) (redStart + ((redEnd - redStart) * radio + 0.5));
        int greed = (int) (greenStart + ((greenEnd - greenStart) * radio + 0.5));
        int blue = (int) (blueStart + ((blueEnd - blueStart) * radio + 0.5));
        return Color.argb(255,red, greed, blue);
    }


    public int getThreeColor(float radio) {
        int resultColor;
        if(radio <= 1){
            resultColor = getColor(mStartColor,mMiddleColor,radio);
        }else {
            radio = radio -1;
            resultColor = getColor(mMiddleColor,mEndColor,radio);
        }
        return resultColor;
    }
}
