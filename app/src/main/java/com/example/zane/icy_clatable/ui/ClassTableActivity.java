package com.example.zane.icy_clatable.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zane.icy_clatable.R;
import com.example.zane.icy_clatable.app.App;
import com.example.zane.icy_clatable.config.WeeksConfig;
import com.example.zane.icy_clatable.event.WeekChooseEvent;
import com.example.zane.icy_clatable.utils.TimeCaluUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Zane on 16/3/14.
 */
public class ClassTableActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private GridView gridView;
    private ClassTableGridAdapter adapter;
    private FloatingActionButton fab;
    private ClassDetialDialogFragment fragment;
    private TextView day1;
    private TextView day2;
    private TextView day3;
    private TextView day4;
    private TextView day5;
    private TextView day6;
    private TextView day7;
    private TextView month;
    private String toolbarTitle;
    private int week_position;
    private ChooseWeekDialogFragment dialogFragment;

    private static final String TAG = "ClassTableActivity";

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_classtable_layout);
        init();

        setUpDays((TimeCaluUtils.getCurWeek(TimeCaluUtils.CaluDays()) - 1) * 7);

        EventBus.getDefault().register(this);

        adapter = new ClassTableGridAdapter(App.getInstance());

        toolbar.setTitle(WeeksConfig.weeks[TimeCaluUtils.getCurWeek(TimeCaluUtils.CaluDays())] + "(本周)");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fragment = new ClassDetialDialogFragment();
                fragment.show(getFragmentManager(), "dialogclassdetailfragment");
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment = new ChooseWeekDialogFragment();
                dialogFragment.show(getFragmentManager(), "classTableDialogFragment");
            }
        });

    }

    //注册对周数选择的监听
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeekChoose(WeekChooseEvent event){
        week_position = event.getPosition();
        toolbar.setTitle(event.getWeek());
        if (event.getPosition() == 0){
            day1.setText("");
            day2.setText("");
            day3.setText("");
            day4.setText("");
            day5.setText("");
            day6.setText("");
            day7.setText("");
            month.setText("");
        } else {
            setUpDays((event.getPosition()-1) * 7);
        }

        dialogFragment.dismiss();
    }

    public void setUpDays(int days){
        Toast.makeText(ClassTableActivity.this, String.valueOf(days), Toast.LENGTH_SHORT).show();
        day1.setText(String.valueOf(TimeCaluUtils.getCurDay(days)));
        day2.setText(String.valueOf(TimeCaluUtils.getCurDay(days+1)));
        day3.setText(String.valueOf(TimeCaluUtils.getCurDay(days+2)));
        day4.setText(String.valueOf(TimeCaluUtils.getCurDay(days+3)));
        day5.setText(String.valueOf(TimeCaluUtils.getCurDay(days+4)));
        day6.setText(String.valueOf(TimeCaluUtils.getCurDay(days+5)));
        day7.setText(String.valueOf(TimeCaluUtils.getCurDay(days+6)));
        Log.i(TAG, String.valueOf(TimeCaluUtils.getCurDay(days+3)));
        month.setText(TimeCaluUtils.getCurMonth(days) + "月");
    }


    private void init(){
        day1 = (TextView)findViewById(R.id.day1);
        day2 = (TextView)findViewById(R.id.day2);
        day3 = (TextView)findViewById(R.id.day3);
        day4 = (TextView)findViewById(R.id.day4);
        day5 = (TextView)findViewById(R.id.day5);
        day6 = (TextView)findViewById(R.id.day6);
        day7 = (TextView)findViewById(R.id.day7);
        month = (TextView)findViewById(R.id.month);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        gridView = (GridView)findViewById(R.id.gridview_class);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
