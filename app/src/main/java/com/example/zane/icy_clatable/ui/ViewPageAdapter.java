package com.example.zane.icy_clatable.ui;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zane.icy_clatable.R;
import com.example.zane.icy_clatable.data.bean.Clazz;

import java.util.List;

/**
 * Created by Zane on 16/3/16.
 */
public class ViewPageAdapter extends PagerAdapter{

    private List<View> views;
    private List<Clazz.ClassEntity.MutilpleEntity> clazzes;
    private View view;
    private TextView className;
    private TextView teacher;
    private TextView classRoom;
    private TextView time;
    private TextView type;
    private TextView week;

    public ViewPageAdapter(List<View> views, List<Clazz.ClassEntity.MutilpleEntity> clazzes){
        this.views = views;
        this.clazzes = clazzes;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));

        for (int i = 0; i < views.size(); i++){
            view = views.get(i);
            className = (TextView) view.findViewById(R.id.className_viewpage);
            teacher = (TextView) view.findViewById(R.id.teacher_viewpage);
            classRoom = (TextView) view.findViewById(R.id.classRoom_viewpage);
            time = (TextView) view.findViewById(R.id.time_viewpage);
            type = (TextView) view.findViewById(R.id.type_viewpage);
            week = (TextView) view.findViewById(R.id.week_viewpage);

            setData(position);
        }

        return views.get(position);
    }

    public void setData(int position){
        className.setText(clazzes.get(position).getClassname());
        teacher.setText(clazzes.get(position).getTeachername());
        classRoom.setText(clazzes.get(position).getClassroom());
        time.setText("hahaha");
        type.setText(clazzes.get(position).getObject());
        week.setText(clazzes.get(position).getTime());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }
}
