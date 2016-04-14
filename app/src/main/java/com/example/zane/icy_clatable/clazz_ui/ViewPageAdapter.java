package com.example.zane.icy_clatable.clazz_ui;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zane.icy_clatable.R;
import com.example.zane.icy_clatable.data.bean.Clazz;
import com.example.zane.icy_clatable.data.bean.Clazz_Two;

import java.util.List;

/**
 * Created by Zane on 16/3/16.
 */
public class ViewPageAdapter extends PagerAdapter{

    private List<View> views;
    private List<Clazz_Two.DataEntity> clazzes;
    private View view;
    private TextView className;
    private TextView teacher;
    private TextView classRoom;
    private TextView time;
    private TextView type;
    private TextView week;

    public ViewPageAdapter(List<View> views, List<Clazz_Two.DataEntity> clazzes){
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

        view = views.get(position);
        className = (TextView) view.findViewById(R.id.className_viewpage);
        teacher = (TextView) view.findViewById(R.id.teacher_viewpage);
        classRoom = (TextView) view.findViewById(R.id.classRoom_viewpage);
        time = (TextView) view.findViewById(R.id.time_viewpage);
        type = (TextView) view.findViewById(R.id.type_viewpage);
        week = (TextView) view.findViewById(R.id.week_viewpage);

        setData(position);

        return views.get(position);
    }

    //设置数据
    public void setData(int position){

        className.setText(clazzes.get(position).getCourse_name());
        teacher.setText(clazzes.get(position).getTeacher());
        classRoom.setText(clazzes.get(position).getClassrom());
        time.setText(clazzes.get(position).getDuring());
        type.setText(clazzes.get(position).getKinds());

        String weekDuring="";
        if (clazzes.get(position).getSingel_or_double().equals(" ")){
            weekDuring = clazzes.get(position).getBengin_week()+"-"+clazzes.get(position).getEnd_week()+"周";
        } else if (clazzes.get(position).getSingel_or_double().equals("1")){
            weekDuring = clazzes.get(position).getBengin_week()+"-"+clazzes.get(position).getEnd_week()+"周(单周)";
        } else if (clazzes.get(position).getSingel_or_double().equals("2")){
            weekDuring = clazzes.get(position).getBengin_week()+"-"+clazzes.get(position).getEnd_week()+"周(双周)";
        }
        week.setText(weekDuring);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

}
