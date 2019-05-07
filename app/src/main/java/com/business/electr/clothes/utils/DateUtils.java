package com.business.electr.clothes.utils;

import com.business.electr.clothes.constants.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    /**
     * Sun Apr 22 22:22:22 GMT 2018
     * 转换为dateFormat格式
     *
     * @return
     */
    public static String format(Date date, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(date.getTime());//这个就是把时间戳经过处理得到期望格式的时间  
    }

    /**
     * 时间毫秒值转换指定格式时间
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    private static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * 获取当前时间  yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentTime() {
        return getTime(System.currentTimeMillis(), new SimpleDateFormat(Constant.DATE_FORMAT_2));
    }

    /**
     * 获取当前时间的年份  2018
     *
     * @return
     */
    public static String getCurrentTimeYear() {
        return getTime(System.currentTimeMillis(), new SimpleDateFormat(Constant.DATE_FORMAT_3));
    }

    /**
     * 获取当前时间的年份  10:20
     *
     * @return
     */
    public static String getCurrentTimeHouse() {
        return getTime(System.currentTimeMillis(), new SimpleDateFormat(Constant.DATE_FORMAT_4));
    }


    /**
     * 获取今天已经走了的时间  单位（分）
     * @return
     */
    public static int getSecondTime() {
        String temp = "";
        temp = getCurrentTimeHouse();
        String[] second = temp.split(":");
        int s = Integer.valueOf(second[0]) * 60 + Integer.valueOf(second[1]);
        return s;
    }

}
