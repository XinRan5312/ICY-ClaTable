package com.example.zane.icy_clatable.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zane.icy_clatable.R;
import com.example.zane.icy_clatable.app.App;
import com.example.zane.icy_clatable.config.ColorConfig;

import java.util.List;


/**
 * Created by Zane on 16/3/14.
 */
public class ClassTableGridAdapter extends BaseAdapter{

    private List<Integer> colos;
    private LayoutInflater inflater;


    public ClassTableGridAdapter(Context context){
        colos = ColorConfig.getAllColor();
        inflater = LayoutInflater.from(context);
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
        ImageView imageView = new ImageView(App.getInstance());

        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_gridview_classtable_layout, parent, false);
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.imageview);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.imageView.setImageResource(colos.get(position));


        return convertView;
    }

    class ViewHolder{
        private ImageView imageView;
        private TextView textView;
    }
}
