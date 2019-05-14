package com.business.electr.clothes.utils;

import com.business.electr.clothes.constants.Constant;

import java.text.ParseException;
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
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
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

    public static Date getDateByString(String month,String dateFormat){
        Date date = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat(dateFormat);
            date = df.parse(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取日期标题
     */
    public static String getMonthDayStr(Date month) {
        SimpleDateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT_5);
        return df.format(month);
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
