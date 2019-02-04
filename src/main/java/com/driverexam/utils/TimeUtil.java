package com.driverexam.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LinWei on 2018/7/11 10:30
 */
public class TimeUtil {
    /**
     * 根据毫秒返回分秒
     *
     * @param time
     * @return
     */
    public static String getFormatMS(long time) {
        time = time / 1000;//总秒数
        int s = (int) (time % 60);//秒
        int m = (int) (time / 60);//分
        return String.format("%02d:%02d", m, s);
    }

    /**
     * 根据毫秒返回时分秒
     *
     * @param time
     * @return
     */
    public static String getFormatHMS(long time) {
        time = time / 1000;//总秒数
        int s = (int) (time % 60);//秒
        int m = (int) (time / 60);//分
        int h = (int) (time / 3600);//时
        return String.format("%02d:%02d:%02d", h, m, s);
    }


    /**
     * 返回系统当前时间
     *
     * @return
     */
    public static String currentTime(String type) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(type);//"yyyy-MM-dd HH:mm:ss"
        return format.format(date);
    }

    //时间戳转换为规格时间
    public static String TransTime(String time) {
        if (StringUtils.isEmpty(time)) {
            return "";
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//"yyyy-MM-dd HH:mm:ss"
            return format.format(Long.parseLong(time));
        }

    }


    //时间戳转换为规格时间
    public static String TransTime_yMdHms(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(time);
    }

    public static String TransTime_yMdHm(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(time);
    }



    public static Date TransDate(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String TransTime(Date time, String type) {
        SimpleDateFormat format = new SimpleDateFormat(type);//"yyyy-MM-dd HH:mm:ss"
        return format.format(time);

    }

}
