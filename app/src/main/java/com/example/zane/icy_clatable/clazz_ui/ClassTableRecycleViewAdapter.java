package com.example.zane.icy_clatable.clazz_ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zane.icy_clatable.R;
import com.example.zane.icy_clatable.config.WeeksConfig;
import com.example.zane.icy_clatable.event.WeekChooseEvent;
import com.example.zane.icy_clatable.utils.TimeCaluUtils;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Zane on 16/3/14.
 */
public class ClassTableRecycleViewAdapter extends RecyclerView.Adapter<ClassTableRecycleViewAdapter.MyViewHolder>{

    private String[] weeks;
    private Context context;
    private int weekDay;


    public ClassTableRecycleViewAdapter(Context mContext){
        this.context = mContext;
        weeks = WeeksConfig.weeks;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_recycleview_classtable, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        weekDay = TimeCaluUtils.getCurWeek(TimeCaluUtils.CaluDays());
        final String week;

        if (weekDay == position){
            holder.textView.setText(weeks[position]+"(本周)");
            week = weeks[position]+"(本周)";
        } else {
            holder.textView.setText(weeks[position]);
            week = weeks[position];
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new WeekChooseEvent(week, position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return weeks.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.textview_week);
        }
    }
}
