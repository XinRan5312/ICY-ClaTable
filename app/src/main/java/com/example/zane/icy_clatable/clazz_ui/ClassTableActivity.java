package com.example.zane.icy_clatable.clazz_ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.example.zane.icy_clatable.data.ClassModel;
import com.example.zane.icy_clatable.data.bean.Clazz_Two;
import com.example.zane.icy_clatable.event.WeekChooseEvent;
import com.example.zane.icy_clatable.utils.TimeCaluUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import rx.functions.Action1;

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
    private List<Clazz_Two.DataEntity> clazzes;
    private Clazz_Two clazz;
    private List<Integer> notNullPosition;
    private List<Integer> mutiplyPosition;
    private List<Integer> nullPosition;
    private HashMap<Integer, Integer> map;
    private List<List<Clazz_Two.DataEntity>> clazz_adapter;
    private ClassModel classModel;
    private List<Integer> threePositon;

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

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean isNull = false;
                boolean isThree = false;
                for (Integer null_position : nullPosition){
                    if (null_position == position){
                        isNull = true;
                        for (Integer three_position : threePositon){
                            if (null_position == (three_position + 7)){
                                isThree = true;
                            }
                        }
                        break;
                    }
                }
                if (!isNull || isThree) {
                    fragment = new ClassDetialDialogFragment();
                    fragment.setClazzes(clazz_adapter.get(position));
                    fragment.show(getFragmentManager(), "dialogclassdetailfragment");
                }
                isNull = true;
            }
        });

        //选择周数的fab
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
     * @param week 周数
     */
    public void setupClazz(int week){

        notNullPosition.clear();
        nullPosition.clear();
        clazz_adapter.clear();
        mutiplyPosition.clear();
        threePositon.clear();

        //本来默认某一周的课集合就是1，但是方便适配器，就还是list套list。
        //开始构建42个格子
        for (int n = 0; n < 42; n++){
            clazz_adapter.add(new ArrayList<Clazz_Two.DataEntity>());
        }

        for (int i = 0; i < clazzes.size(); i++){
            Clazz_Two.DataEntity clazz = clazzes.get(i);
            int weekDay = Integer.parseInt(clazz.getWeekday());
            int begin_class = Integer.parseInt(clazz.getBegin_class());

            //如果在范围内
            if (Integer.parseInt(clazzes.get(i).getBegin_week()) <= week && Integer.parseInt(clazzes.get(i).getEnd_week()) >= week){
                //分单双，符合条件才添加
                if (clazzes.get(i).getSingle_or_double().equals(" ") || (week - Integer.parseInt(clazzes.get(i).getBegin_week())) % 2 == 0){
                    int position = ((begin_class - 1) / 2 * 7 + (weekDay - 1));

                    notNullPosition.add(position);
                    clazz_adapter.get(position).add(clazzes.get(i));
                    if ((position >= 0 && position <= 6) || (position >= 14 && position <= 20) || (position >= 21 && position <= 27)) {
                        if (position == 0 || Integer.parseInt(clazzes.get(0).getEnd_class()) - Integer.parseInt(clazzes.get(0).getBegin_class()) > 1) {
                            threePositon.add(position);
                            clazz_adapter.get(position + 7).add(clazzes.get(i));
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 42; i++){
            nullPosition.add(i);
        }
        for (int i = 0; i < notNullPosition.size(); i++){
            nullPosition.remove(notNullPosition.get(i));
        }

        for (int position = 0; position < 42; position++){

        }

        adapter = new ClassTableGridAdapter(App.getInstance(), nullPosition, mutiplyPosition, clazz_adapter, threePositon);
        gridView.setAdapter(adapter);
    }

    /**
     * 显示整个学期的课的方法
     */
    public void setupAllClazz(){
        //清空重用
        notNullPosition.clear();
        mutiplyPosition.clear();
        nullPosition.clear();
        clazz_adapter.clear();
        map.clear();
        threePositon.clear();

        //(begin_class - 1)/2*7 + (weekday-1) 就是42节课里面有课的坐标点..
        for (int i = 0; i < clazzes.size(); i++){

            Clazz_Two.DataEntity clazz = clazzes.get(i);
            int weekDay = Integer.parseInt(clazz.getWeekday());
            int begin_class = Integer.parseInt(clazz.getBegin_class());
            notNullPosition.add(((begin_class - 1) / 2 * 7 + (weekDay - 1)));
            mutiplyPosition.add(((begin_class - 1) / 2 * 7 + (weekDay - 1)));
            map.put(i, ((begin_class - 1) / 2 * 7 + (weekDay - 1)));

        }

        //查找一个位置多门课的情况(查找一个list里面重复元素的算法)
        //set去重
        HashSet<Integer> mutiplySet = new HashSet<>(mutiplyPosition);
        Iterator<Integer> iterator = mutiplySet.iterator();
        while (iterator.hasNext()){
            //集合去除集合单独元素
            mutiplyPosition.remove(iterator.next());
        }

        //根据重复的value来获得key,构建42个课程格子
        for (int n = 0; n < 42; n++){
            clazz_adapter.add(new ArrayList<Clazz_Two.DataEntity>());
        }
        for (int i = 0; i < map.size(); i++){
            clazz_adapter.get(map.get(i)).add(clazzes.get(i));
        }

        //由于接口数据改变，为了方便适配器代码不变，弄出空课的position出来。。
        for (int i = 0; i < 42; i++){
            nullPosition.add(i);
        }
        for (int i = 0; i < notNullPosition.size(); i++){
            nullPosition.remove(notNullPosition.get(i));
        }

        //将有三节小课的大课找出来出来
        for (int position = 0; position < 42; position++){
            if ((position >= 0 && position <= 6) || (position >= 14 && position <= 20) || (position >= 21 && position <= 27)) {
                if (position == 0 || Integer.parseInt(clazzes.get(0).getEnd_class()) - Integer.parseInt(clazzes.get(0).getBegin_class()) > 1) {
                    threePositon.add(position);
                    //clazz_adapter.get(position + 7).add(clazzes.get(position));
                    for (int i = 0; i < map.size(); i++){
                        if (map.get(i) == position){
                            clazz_adapter.get(position + 7).add(clazzes.get(i));
                        }
                    }
                }
            }
        }

        //怀恋Rxjava.......

        adapter = new ClassTableGridAdapter(App.getInstance(), nullPosition, mutiplyPosition, clazz_adapter, threePositon);
        gridView.setAdapter(adapter);
    }

    //注册对周数选择的监听
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeekChoose(WeekChooseEvent event){
        week_position = event.getPosition();

        //根据选择更改界面的课表内容
        if (week_position == 0){
            //整学期的
            setupAllClazz();
        } else {
            setupClazz(week_position);
        }

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

    //改变最上层天数的变化
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

        EventBus.getDefault().register(this);

        //拿到课程
        classModel = ClassModel.getInstance();
        classModel.getClassData(getIntent().getStringExtra(MainActivity.CLAZZ), "bks")
                .subscribe(new Action1<List<Clazz_Two.DataEntity>>() {
                    @Override
                    public void call(List<Clazz_Two.DataEntity> dataEntities) {
                        if (dataEntities != null){
                            clazzes = dataEntities;
                            setupClazz(TimeCaluUtils.getCurWeek(TimeCaluUtils.CaluDays()));
                        }
                    }
                });

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

        notNullPosition = new ArrayList<>();
        mutiplyPosition = new ArrayList<>();
        nullPosition = new ArrayList<>();
        clazz_adapter = new ArrayList<>(42);
        threePositon = new ArrayList<>();
        map = new HashMap<>();

        setUpDays((TimeCaluUtils.getCurWeek(TimeCaluUtils.CaluDays()) - 1) * 7);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
