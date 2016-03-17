package com.example.zane.icy_clatable.utils;

import java.util.Calendar;

/**
 * Created by Zane on 16/3/15.
 */
public class TimeCaluUtils {

    private static Calendar calendar_start = Calendar.getInstance();
    private static Calendar calendar_now = Calendar.getInstance();
    private static Calendar calendar_monday = Calendar.getInstance();
    private static final int[] firstDay = {2016, 1, 28, 0, 0, 0};

    //返回现在距离开学第一天隔了多少天
    private static int CaluDays(){

        calendar_start.set(firstDay[0], firstDay[1], firstDay[2], firstDay[3], firstDay[4], firstDay[5]);
        calendar_now.setTimeInMillis(System.currentTimeMillis());

        return (int)((calendar_now.getTimeInMillis() - calendar_start.getTimeInMillis()) / (60 * 60 * 24 * 1000));
    }

    //返回当前周的星期一日期
    public static int getMonday(){

        int week_day = CaluDays();
        int week;
        if (week_day / 7 == 0){
            week = week_day / 7 - 1;
        } else {
            week = week_day / 7;
        }

        calendar_monday.setTimeInMillis(calendar_start.getTimeInMillis() + week * 60 * 60 * 24 * 1000 * 7 + 60 * 60 * 24 * 1000);
        return calendar_monday.get(Calendar.DAY_OF_MONTH);
    }

    //返回当前周
    public static int getCurWeek(){
        int days = CaluDays();
        int weekDay;

        if ((days / 7) == 0){
            weekDay = days / 7;
        } else {
            weekDay = (days / 7 + 1);
        }

        return weekDay;
    }
}
