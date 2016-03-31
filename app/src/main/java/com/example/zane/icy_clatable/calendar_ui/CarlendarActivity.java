package com.example.zane.icy_clatable.calendar_ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_layout);
        ButterKnife.bind(this);
        initToolbar();

        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        textviewCalendarYear.setText(calendar.get(Calendar.YEAR) + "年");
        textviewBottomMonth.setText(WeeksConfig.monthes[calendar.get(Calendar.MONTH)+1]);
        textviewCalendarDay.setText(calendar.get(Calendar.DAY_OF_MONTH)+"");

    }

    public void initToolbar(){
        toolbarCarlendar.setTitle("校历");
        toolbarCarlendar.setNavigationIcon(R.drawable.back4);
        setSupportActionBar(toolbarCarlendar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
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
