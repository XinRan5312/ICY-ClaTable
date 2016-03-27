package com.example.zane.icy_clatable.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zane.icy_clatable.R;
import com.example.zane.icy_clatable.config.ColorConfig;
import com.example.zane.icy_clatable.data.bean.Clazz;

import java.util.List;


/**
 * Created by Zane on 16/3/14.
 */
public class ClassTableGridAdapter extends BaseAdapter{

    private static final String TAG = "ClassTableGridAdapter";

    private List<Integer> colos;
    private LayoutInflater inflater;
    private List<Integer> mutiplyPosition;
    private List<Clazz.ClassEntity> clazzes;
    private List<Integer> nullPosition;
    private boolean isNull = false;

    public ClassTableGridAdapter(Context context, List<Integer> nullPosition, List<Integer> mutiplyPosition, List<Clazz.ClassEntity> clazzes){
        colos = ColorConfig.getAllColor(nullPosition);
        inflater = LayoutInflater.from(context);
        this.mutiplyPosition = mutiplyPosition;
        this.clazzes = clazzes;
        this.nullPosition = nullPosition;
    }

    @Override
    public int getCount() {
        return colos.size();
    }

    @Override
    public Object getItem(int position) {
        return colos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_gridview_classtable_layout, parent, false);
            viewHolder.backGround = (ImageView)convertView.findViewById(R.id.imageview);
            viewHolder.imageFlode = (ImageView)convertView.findViewById(R.id.imageview_flod);
            viewHolder.textView = (TextView)convertView.findViewById(R.id.textview_classinfo);
            viewHolder.frameLayout = (FrameLayout)convertView.findViewById(R.id.framelayout_class);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.backGround.setImageResource(colos.get(position));

        for (int i = 0; i < mutiplyPosition.size(); i++){
            if (position == mutiplyPosition.get(i)){
                viewHolder.imageFlode.setVisibility(View.VISIBLE);
            }
        }

        //如果这个课是空，设置不可点击，并且不获取数据
        for (int i = 0; i < nullPosition.size(); i++){
            if (position != nullPosition.get(i)){
                continue;
            } else {
                isNull = true;
                viewHolder.frameLayout.setClickable(false);
                break;
            }
        }

        if (!isNull){
            viewHolder.textView.setText(clazzes.get(position).getMutilple().get(0).getClassname()+"\n"+"@"
                                                + clazzes.get(position).getMutilple().get(0).getClassroom());
        }

        isNull = false;
        return convertView;

    }

    class ViewHolder{
        private ImageView backGround;
        private TextView textView;
        private ImageView imageFlode;
        private FrameLayout frameLayout;
    }
}
