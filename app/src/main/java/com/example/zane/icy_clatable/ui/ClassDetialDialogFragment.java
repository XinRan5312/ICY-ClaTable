package com.example.zane.icy_clatable.ui;

import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.zane.icy_clatable.R;
import com.example.zane.icy_clatable.app.App;
import com.kermit.exutils.utils.ExUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zane on 16/3/16.
 */
public class ClassDetialDialogFragment extends DialogFragment{

    private ViewPager viewPager;
    private ViewPageAdapter adapter;
    private View view1;
    private View view2;
    private List<View> views;
    private LinearLayout circleLayout;
    private ImageView circle1;
    private ImageView circle2;

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setGravity(Gravity.CENTER);
        window.setLayout((int)(ExUtils.getScreenWidth() * 0.8), (int)(ExUtils.getScreenHeight() * 0.6));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        views = new ArrayList<>();

        View view = inflater.inflate(R.layout.dialogfragment_classdetail_layout, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        circleLayout = (LinearLayout) view.findViewById(R.id.circle_layout);
        circle1 = (ImageView)view.findViewById(R.id.circle1);
        circle2 = (ImageView)view.findViewById(R.id.circle2);
        view1 = inflater.inflate(R.layout.item_viewpage_layout, null, false);
        view2 = inflater.inflate(R.layout.item_viewpage_layout, null, false);
        views.add(view1);
        views.add(view2);

        //设置指示器
        setUpCircle();

        adapter = new ViewPageAdapter(views);
        viewPager.setAdapter(adapter);

        return view;
    }

    public void setUpCircle(){
        //添加小圆点，通过view的size来决定其中一个imageview是否gone
        final Drawable drawable_normal = getActivity().getResources().getDrawable(R.drawable.circle_normal);
        final Drawable drawable_select = getActivity().getResources().getDrawable(R.drawable.circle_select);
        if (views.size() == 2){
            circle1.setBackground(drawable_select);
            circle2.setBackground(drawable_normal);

            //添加页面监听
            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 0){
                        circle1.setBackground(drawable_select);
                        circle2.setBackground(drawable_normal);
                    } else {
                        circle2.setBackground(drawable_select);
                        circle1.setBackground(drawable_normal);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        } else {
            circle1.setBackground(drawable_select);
            circle2.setVisibility(View.GONE);
        }
    }
}
