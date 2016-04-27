package com.example.zane.icy_clatable.calendar_ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zane.icy_clatable.R;
import com.example.zane.icy_clatable.app.App;
import com.kermit.exutils.utils.ExUtils;

/**
 * Created by Zane on 16/3/31.
 */
public class CalendarGridViewAdapter extends BaseAdapter{

    private static final String TAG = "CalendarGridViewAdapter";

    //56个格子全部占满
    private final int NUM = 42;
    //当前的天数是56个格子中的哪一个,根据这一天是在星期几来判断
    private int currentDayNum;
    //当前月的最后一天是56个格子中的哪一个
    private int lastDayNum;
    //当前月的第一天是56个格子中的哪一个
    private int firstDayNum;
    //当前天是几号
    private int currentDay;
    //这个月第一天是星期几
    private int fisrtDay_weekday;
    //上一个月的最后一天是31还是30或者其他
    private int lastMonth_lastday;
    //这个月的最后一天是多少
    private int lastDay;
    //子item的高度
    private int item_height;

    /**
     *
     * @param lastDay
     * @param currentDay
     * @param fisrtDay_weekday
     * @param lastMonth_lastday
     */
    public CalendarGridViewAdapter(int height,int lastDay, int currentDay, int fisrtDay_weekday, int lastMonth_lastday) {
        this.lastMonth_lastday = lastMonth_lastday;
        this.fisrtDay_weekday = fisrtDay_weekday;
        this.currentDay = currentDay;
        this.lastDay = lastDay;
        this.item_height = height;
        init();
    }

    @Override
    public int getCount() {
        return NUM;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(App.getInstance()).inflate(R.layout.item_calendar_gridview, parent, false);
            viewHolder.textView = (TextView)convertView.findViewById(R.id.textview_calendar_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        //动态指定日历item的高度问题
        ViewGroup.LayoutParams params = viewHolder.textView.getLayoutParams();
        params.height = item_height;
        params.width = (ExUtils.getScreenWidth() - ExUtils.dip2px(40)) / 8;
        viewHolder.textView.setLayoutParams(params);

        //对每一个格子进行情况判断给值
        //上个月多出来的几天
        if (position < firstDayNum){
            //改变字体颜色
            if (position == 5 || position == 6){
                viewHolder.textView.setTextColor(App.getInstance().getResources().getColor(R.color.calendar_freeday_pasted_color));
            } else {
                viewHolder.textView.setTextColor(App.getInstance().getResources().getColor(R.color.calendar_day_pasted_color));
            }
            viewHolder.textView.setText(lastMonth_lastday - (firstDayNum - position - 1) + "");
        }
        //这个月的天数
        else if (position <= lastDayNum){
            //本月内的放假字体颜色
            if (position == 5 || position == 6 || position == 12 || position == 13 || position == 19 || position == 20
                    || position == 26 || position == 27 || position == 33 || position == 34){
                viewHolder.textView.setTextColor(App.getInstance().getResources().getColor(R.color.calendar_freeday_color));
            } else {
                viewHolder.textView.setTextColor(App.getInstance().getResources().getColor(R.color.black));
            }
            viewHolder.textView.setText((position - firstDayNum + 1) + "");
        }
        //下个月的天数
        else if (position > lastDayNum){
            if (position == 40 || position == 41){
                viewHolder.textView.setTextColor(App.getInstance().getResources().getColor(R.color.calendar_freeday_pasted_color));
            } else {
                viewHolder.textView.setTextColor(App.getInstance().getResources().getColor(R.color.calendar_day_pasted_color));
            }
            viewHolder.textView.setText(position - lastDayNum + "");
        }

        return convertView;

    }

    public void init(){
        //判断这个月第一天是第几格
        switch (fisrtDay_weekday){
            case 1:
                firstDayNum = 0;
                break;
            case 2:
                firstDayNum = 1;
                break;
            case 3:
                firstDayNum = 2;
                break;
            case 4:
                firstDayNum = 3;
                break;
            case 5:
                firstDayNum = 4;
                break;
            case 6:
                firstDayNum = 5;
                break;
            case 7:
                firstDayNum = 6;
                break;
        }
        //判断这个月最后一天是第几格
        lastDayNum = firstDayNum + (lastDay - 1);
    }

    class ViewHolder{
        public TextView textView;
    }
}
