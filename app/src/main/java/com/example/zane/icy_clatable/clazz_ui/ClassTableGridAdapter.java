package com.example.zane.icy_clatable.clazz_ui;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zane.icy_clatable.R;
import com.example.zane.icy_clatable.app.App;
import com.example.zane.icy_clatable.config.ColorConfig;
import com.example.zane.icy_clatable.data.bean.Clazz_Two;
import com.kermit.exutils.utils.ExUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Zane on 16/3/14.
 */
public class ClassTableGridAdapter extends BaseAdapter{

    private static final String TAG = "ClassTableGridAdapter";

    //private List<Integer> colos;
    private LayoutInflater inflater;
    private List<Integer> mutiplyPosition;
    private List<List<Clazz_Two.DataEntity>> clazzes;
    private List<Integer> nullPosition;
    private List<Integer> threePosition;
    private List<Integer> down_ThreePositon;
    private boolean isNull = false;


    public ClassTableGridAdapter(Context context, List<Integer> nullPosition, List<Integer> mutiplyPosition, List<List<Clazz_Two.DataEntity>> clazzes, List<Integer> threePosition){
        inflater = LayoutInflater.from(context);
        this.mutiplyPosition = mutiplyPosition;
        this.clazzes = clazzes;
        this.nullPosition = nullPosition;
        this.threePosition = threePosition;
        down_ThreePositon = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return clazzes.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        //boolean isMutiply = false;

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

        for (int i = 0; i < mutiplyPosition.size(); i++){
            if (position == mutiplyPosition.get(i)){
                //设置折角图片可见
                viewHolder.imageFlode.setVisibility(View.VISIBLE);

            }
        }

        //如果这个课是空，设置不可点击，并且不获取数据
        for (int i = 0; i < nullPosition.size(); i++){

            ViewGroup.LayoutParams params = viewHolder.frameLayout.getLayoutParams();

            if (position != nullPosition.get(i)){
                continue;
            } else {
                boolean isThree = false;
                for (int j = 0; j < down_ThreePositon.size(); j++){
                    if (i == down_ThreePositon.get(j)){
                        params.height = ExUtils.dip2px(60);
                        viewHolder.frameLayout.setLayoutParams(params);
                        convertView.setClickable(true);
                        isThree = true;
                        break;
                    } else {
                        continue;
                    }
                }

                if (!isThree){
                    params.height = ExUtils.dip2px(121);
                    viewHolder.frameLayout.setLayoutParams(params);
                    convertView.setClickable(false);
                }

                isNull = true;


                //viewHolder.backGround.setImageResource(R.color.white);
                break;
            }
        }

        //课表改成42格的position。。。
        if (!isNull){

            int height = App.getInstance().getResources().getDimensionPixelSize(R.dimen.clazz_grid_height);
            AbsListView.LayoutParams params_three = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
            ViewGroup.LayoutParams params = convertView.getLayoutParams();

            for (Integer i : threePosition){
                if (i == position){
                    params.height = ExUtils.dip2px(182);
                    convertView.setLayoutParams(params);
                    down_ThreePositon.add(position + 7);
                } else {
                    params.height = ExUtils.dip2px(121);
                    convertView.setLayoutParams(params);
                }
            }

            if ((position >= 7 && position <= 13) || (position >= 21 && position <= 27) || (position >= 35 && position <= 42)){
                params.height = ExUtils.dip2px(121);
                convertView.setLayoutParams(params);
            }

//            if ((position >= 0 && position <= 6) || (position >= 14 && position <= 20) || (position >= 21 && position <= 27)){
//                if (position == 0 || Integer.parseInt(clazzes.get(position).get(0).getEnd_class()) - Integer.parseInt(clazzes.get(position).get(0).getBegin_class()) > 1){
//                    params.height = ExUtils.dip2px(182);
//                    //viewHolder.textView.setLayoutParams(params1);
//                    convertView.setLayoutParams(params);
//                    threePosition.add(position + 7);
//                } else {
//                    params.height = ExUtils.dip2px(121);
//                    convertView.setLayoutParams(params);
//                }
//            } else {
//                params.height = ExUtils.dip2px(121);
//                convertView.setLayoutParams(params);
//            }

            viewHolder.backGround.setImageResource(ColorConfig.getRandomColor());
            viewHolder.textView.setText(clazzes.get(position).get(0).getCourse_name()+"\n"+"@"
                                                + clazzes.get(position).get(0).getClassroom());
        }

        isNull = false;
        return convertView;

    }

    class ViewHolder{
        private ImageView backGround;
        private TextView textView;
        private ImageView imageFlode;
        private FrameLayout frameLayout;
        private boolean isThree;
    }
}
