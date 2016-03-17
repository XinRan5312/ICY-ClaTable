package com.example.zane.icy_clatable.event;

/**
 * Created by Zane on 16/3/17.
 * 周数选择的事件
 */
public class WeekChooseEvent {

    private String week;
    private int position;

    public WeekChooseEvent(String week, int position){
        this.week = week;
        this.position = position;
    }

    public String getWeek() {
        return week;
    }

    public int getPosition() {
        return position;
    }
}
