package com.example.zane.icy_clatable.clazz_ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.zane.icy_clatable.MainActivity;
import com.example.zane.icy_clatable.R;
import com.example.zane.icy_clatable.app.App;
import com.example.zane.icy_clatable.config.WeeksConfig;
import com.example.zane.icy_clatable.data.bean.Clazz;
import com.example.zane.icy_clatable.event.WeekChooseEvent;
import com.example.zane.icy_clatable.utils.TimeCaluUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

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
    private List<Clazz.ClassEntity> clazzes;
    private Clazz clazz;
    private List<Integer> nullPosition;
    private List<Integer> mutiplyPosition;

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

        setupAllClazz();

        toolbar.setTitle(WeeksConfig.weeks[TimeCaluUtils.getCurWeek(TimeCaluUtils.CaluDays())] + "(本周)");
        toolbar.setNavigationIcon(R.drawable.back4);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean isNull = false;
                for (Integer null_position : nullPosition){
                    if (null_position == position){
                        isNull = true;
                        break;
                    }
                }
                if (!isNull) {
                    fragment = new ClassDetialDialogFragment(clazzes.get(position).getMutilple());
                    fragment.show(getFragmentManager(), "dialogclassdetailfragment");
                }
                isNull = true;
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

    /**
     * week 如果点击某周就调用这个方法来更新课表显示
     * @param week
     */
    public void setupClazz(int week){



    }

    /**
     * 显示整个学期的课的方法
     */
    public void setupAllClazz(){
        nullPosition = new ArrayList<>();
        mutiplyPosition = new ArrayList<>();
        for (int i = 0 ; i < clazzes.size(); i++){
            //去除42节里面的空课
            if (clazzes.get(i).getMutilple() == null){
                nullPosition.add(i);
            } else if (clazzes.get(i).getMutilple().size() > 1){
                mutiplyPosition.add(i);
            }
        }
        adapter = new ClassTableGridAdapter(App.getInstance(), nullPosition, mutiplyPosition, clazzes);
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
        day1.setText(String.valueOf(TimeCaluUtils.getCurDay(days)));
        day2.setText(String.valueOf(TimeCaluUtils.getCurDay(days+1)));
        day3.setText(String.valueOf(TimeCaluUtils.getCurDay(days+2)));
        day4.setText(String.valueOf(TimeCaluUtils.getCurDay(days+3)));
        day5.setText(String.valueOf(TimeCaluUtils.getCurDay(days+4)));
        day6.setText(String.valueOf(TimeCaluUtils.getCurDay(days+5)));
        day7.setText(String.valueOf(TimeCaluUtils.getCurDay(days + 6)));
        month.setText(TimeCaluUtils.getCurMonth(days) + "月");
    }


    private void init(){

        clazz = (Clazz)getIntent().getSerializableExtra(MainActivity.CLAZZ);
        //拿到42节课
        clazzes = clazz.getClassX();

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
