package com.example.zane.icy_clatable.calendar_ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zane.icy_clatable.R;
import com.example.zane.icy_clatable.utils.TimeCaluUtils;
import com.kermit.exutils.utils.ExUtils;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zane on 16/3/31.
 */
public class CalendarViewpagerFragment extends Fragment {

    private static final String TAG = "CalendarViewpagerFragment";
    public static final String MONTH_CALENDAR = "month_calendar";
    public static final String TOOLBAR_HEIGHT = "toolbar_height";

    @Bind(R.id.week1)
    TextView week1;
    @Bind(R.id.week2)
    TextView week2;
    @Bind(R.id.week3)
    TextView week3;
    @Bind(R.id.week4)
    TextView week4;
    @Bind(R.id.week5)
    TextView week5;
    @Bind(R.id.week6)
    TextView week6;
    @Bind(R.id.gridview_carlendar)
    GridView gridviewCarlendar;
    @Bind(R.id.layout_calendar)
    LinearLayout layoutCalendar;

    private CalendarGridViewAdapter adapter;
    private Calendar calendar;
    private Calendar curCalendar;
    //需要展示的月份
    private int month;
    private int lastDay;
    private int currentDay;
    private int firstDay_weekday;
    private int lastMonth_lastday;
    //toolbar的高度
    private int toolbarHeight;

    public static CalendarViewpagerFragment newInstance(int month, int toolbarHeight) {
        CalendarViewpagerFragment fragment = new CalendarViewpagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MONTH_CALENDAR, month);
        bundle.putInt(TOOLBAR_HEIGHT, toolbarHeight);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.item_calendar_viewpager, container, false);
        ButterKnife.bind(this, view);

        //通过argument来获得需要展示的月份数
        month = getArguments().getInt(MONTH_CALENDAR);
        toolbarHeight = getArguments().getInt(TOOLBAR_HEIGHT);
        calendar = Calendar.getInstance();
        curCalendar = Calendar.getInstance();
        curCalendar.setTimeInMillis(System.currentTimeMillis());
        calendar.clear();
        //month  0到11,在这里设置今年的年份。
        calendar.set(2016, month, 1);

        //这个月的最大天数
        lastDay = calendar.getActualMaximum(Calendar.DATE);

        //当前的天数
        currentDay = curCalendar.get(Calendar.DAY_OF_MONTH);

        //1-7,1: sunday.这个月第一天是星期几
        firstDay_weekday = calendar.get(Calendar.DAY_OF_WEEK);
        //转换一下
        if (firstDay_weekday != 1) {
            firstDay_weekday--;
        } else {
            firstDay_weekday = 7;
        }

        //上一个月的最后一天的日期号
        if (month != 0) {
            int mon = month - 1;
            calendar.set(Calendar.MONTH, mon);
            lastMonth_lastday = calendar.getActualMaximum(Calendar.DATE);
        } else {
            lastMonth_lastday = 31;
        }


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Log.i(TAG, "上一个月最后一天： " + lastMonth_lastday + "当前的天数： " + currentDay);
        //Log.i(TAG, "这个月第一天是星期几： " + firstDay_weekday + "这个月的最后一天: " + lastDay);


        //重置calendar,设置左侧周数
        calendar.set(Calendar.MONTH, month);
        //动态改变item的宽度，保持对齐
        ViewGroup.LayoutParams params = layoutCalendar.getLayoutParams();
        params.width = (ExUtils.getScreenWidth() - ExUtils.dip2px(40)) / 8;
        layoutCalendar.setLayoutParams(params);

        week1.setText(TimeCaluUtils.getCurWeek(TimeCaluUtils.CaluDays_random(calendar.getTimeInMillis())) + "");
        week2.setText(TimeCaluUtils.getCurWeek(TimeCaluUtils.CaluDays_random(calendar.getTimeInMillis())) + 1 + "");
        week3.setText(TimeCaluUtils.getCurWeek(TimeCaluUtils.CaluDays_random(calendar.getTimeInMillis())) + 2 + "");
        week4.setText(TimeCaluUtils.getCurWeek(TimeCaluUtils.CaluDays_random(calendar.getTimeInMillis())) + 3 + "");
        week5.setText(TimeCaluUtils.getCurWeek(TimeCaluUtils.CaluDays_random(calendar.getTimeInMillis())) + 4 + "");
        week6.setText(TimeCaluUtils.getCurWeek(TimeCaluUtils.CaluDays_random(calendar.getTimeInMillis())) + 5 + "");
        //通过屏幕的一半的高度减去toolbar和其他区域的高度除以6，解决日历手机适配的高度问题。fuck！
        int week_height = (((ExUtils.getScreenHeight() - toolbarHeight) / 2) - (ExUtils.dip2px(105))) / 6;
        adapter = new CalendarGridViewAdapter(week_height, lastDay, currentDay, firstDay_weekday, lastMonth_lastday);
        gridviewCarlendar.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
