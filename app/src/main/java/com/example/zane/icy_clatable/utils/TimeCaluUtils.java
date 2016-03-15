package com.example.zane.icy_clatable.utils;

import java.util.Calendar;

/**
 * Created by Zane on 16/3/15.
 */
public class TimeCaluUtils {

    private static Calendar calendar_start = Calendar.getInstance();
    private static Calendar calendar_now = Calendar.getInstance();
    private static final int[] firstDay = {2016, 1, 28, 0, 0, 0};

    //返回现在距离开学第一天隔了多少天
    public static int CaluDays(){

        calendar_start.set(firstDay[0], firstDay[1], firstDay[2], firstDay[3], firstDay[4], firstDay[5]);
        calendar_now.setTimeInMillis(System.currentTimeMillis());

        return (int)((calendar_now.getTimeInMillis() - calendar_start.getTimeInMillis()) / (60 * 60 * 24 * 1000));
    }
}
