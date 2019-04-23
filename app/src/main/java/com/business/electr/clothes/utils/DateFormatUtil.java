package com.business.electr.clothes.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zenghaiqiang on 2019/1/17.
 */

public class DateFormatUtil {

    public DateFormatUtil() {

    }

    /**
     * 把Date转换成long类型
     *
     * @param date 需要转换的date数据
     * @return
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * 将long类型时间转换为Date类型时间
     *
     * @param time 需要转换的时间
     * @return 需要的Date类型时间
     */
    public static Date longToDate(long time) {

        Date date = new Date(time);
        return date;
    }

    /**
     * 将String类型数据转为Date数据
     * @param time       需要转换的时间
     * @param formatType 转换的String格式
     * @return Date类型时间
     * @throws ParseException
     */
    public static Date stringToDate(String time, String formatType) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formatType);
        Date date = format.parse(time);
        return date;
    }

    /**
     * 将Date类型数据转换为formatType格式的字符串数据
     * @param date       需要转换的Date类型时间
     * @param formatType 转换的格式
     * @return 需要得到的String类型时间
     */
    public static String dateToString(Date date, String formatType) {
        SimpleDateFormat format = new SimpleDateFormat(formatType);
        String time = format.format(date);
        return time;
    }


    public static String StringToString(String time,String oldFormatType,String newFormatType) throws ParseException {
        Date date = DateFormatUtil.stringToDate(time,oldFormatType);
        return DateFormatUtil.dateToString(date,newFormatType);
    }

    /**
     * 将long类型数据转换为formatType格式的字符串数据
     * @param time       需要转换的long类型时间
     * @param formatType 转换的格式
     * @return 需要得到的String类型时间
     */
    public static String longToString(long time, String formatType) {

        Date date = new Date(time);
        return  dateToString(date, formatType);
    }

    /**
     * 将String类型数据转换为long类型时间
     *
     * @param time       需要转换的String类型时间
     * @param formatType 转换的格式
     * @return 需要得到的long类型时间
     * @throws ParseException
     */
    public static long stringToLong(String time, String formatType) throws ParseException {

        Date date=stringToDate(time,formatType);
        return date.getTime();
    }


    public static String DateAddWeek(long date,String formatType) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(longToString(date,formatType));
        String[] WEEK = {"  (星期天)","  (星期一)","  (星期二)","  (星期三)","  (星期四)","  (星期五)","  (星期六)"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date));
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > 7) {
            return null;
        }
        stringBuilder.append(WEEK[dayIndex - 1]);
        return stringBuilder.toString();
    }


}

