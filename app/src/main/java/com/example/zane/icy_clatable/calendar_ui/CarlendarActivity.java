package com.example.zane.icy_clatable.calendar_ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zane.icy_clatable.R;
import com.example.zane.icy_clatable.config.WeeksConfig;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zane on 16/3/31.
 */
public class CarlendarActivity extends AppCompatActivity {

    public static final String TAG = "CarlendarActivity";

    @Bind(R.id.toolbar_carlendar)
    Toolbar toolbarCarlendar;
    @Bind(R.id.textview_calendar_year)
    TextView textviewCalendarYear;
    @Bind(R.id.textview_calendar_month)
    TextView textviewCalendarMonth;
    @Bind(R.id.textview_calendar_day)
    TextView textviewCalendarDay;
    @Bind(R.id.img_wheather)
    ImageView imgWheather;
    @Bind(R.id.textview_temp)
    TextView textviewTemp;
    @Bind(R.id.img_toLeft)
    ImageView imgToLeft;
    @Bind(R.id.textview_bottom_month)
    TextView textviewBottomMonth;
    @Bind(R.id.img_toRight)
    ImageView imgToRight;
    @Bind(R.id.viewpager_calendar)
    ViewPager viewpagerCalendar;

    private Calendar calendar;
    //当前月
    private int month;
    private CalendarViewpagerAdapter adapter;
    //viewpager的position
    private int position_viewpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_layout);
        ButterKnife.bind(this);
        initToolbar();
        initData();

        viewpagerCalendar.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                Log.i(TAG , "change" + month);
                if (position < position_viewpage && position != 0){
                    month--;
                } else if (position > position_viewpage && position != 11){
                    month++;
                }
                textviewBottomMonth.setText(WeeksConfig.monthes[position]);

                position_viewpage = position;
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        imgToLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (month != 0) {
                    //向左翻页
                    viewpagerCalendar.arrowScroll(1);
                }
            }
        });
        imgToRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (month != 11) {
                    //向右翻页
                    viewpagerCalendar.arrowScroll(2);
                }
            }
        });
    }

    public void initData() {
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        textviewCalendarYear.setText(calendar.get(Calendar.YEAR) + "年");
        textviewCalendarMonth.setText(WeeksConfig.monthes[calendar.get(Calendar.MONTH)]);
        textviewCalendarDay.setText(calendar.get(Calendar.DAY_OF_MONTH) + "");
        textviewBottomMonth.setText(WeeksConfig.monthes[calendar.get(Calendar.MONTH)]);
        month = calendar.get(Calendar.MONTH);
        position_viewpage = month;

        textviewBottomMonth.setText(WeeksConfig.monthes[month]);

        //设置适配器
        adapter = new CalendarViewpagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < 12; i++) {
            adapter.addFragment(CalendarViewpagerFragment.newInstance(i, toolbarCarlendar.getLayoutParams().height));
        }
        viewpagerCalendar.setAdapter(adapter);
        viewpagerCalendar.setCurrentItem(month);
    }

    public void initToolbar() {
        toolbarCarlendar.setTitle("校历");
        toolbarCarlendar.setNavigationIcon(R.drawable.back4);
        setSupportActionBar(toolbarCarlendar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarCarlendar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
