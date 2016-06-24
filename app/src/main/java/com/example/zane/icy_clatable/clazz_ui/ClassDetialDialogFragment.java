package com.example.zane.icy_clatable.clazz_ui;

import android.app.DialogFragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zane.icy_clatable.R;
import com.example.zane.icy_clatable.data.bean.Clazz_Two;
import com.jude.utils.JUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zane on 16/3/16.
 */
public class ClassDetialDialogFragment extends DialogFragment{

    public static final String PAGES = "pages";
    public static final String CLAZZ_DETAIL = "clazzdetail";
    private static final String TAG = "ClassDetialDialog";

    private ViewPager viewPager;
    private ViewPageAdapter adapter;
    private List<View> views;
    private List<Clazz_Two.DataEntity> clazzes;
    private LinearLayout circleLayout;
    private ImageView circle1;
    private ImageView circle2;
    private ImageView circle3;



    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setGravity(Gravity.CENTER);
        window.setLayout((int) (JUtils.getScreenWidth() * 0.8), (int) (JUtils.getScreenHeight() * 0.6));
    }

    public void setClazzes(List<Clazz_Two.DataEntity> clazzes){
        this.clazzes = clazzes;
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
        circle3 = (ImageView)view.findViewById(R.id.circle3);

        for (int i = 0; i < clazzes.size(); i++){
            View childView = inflater.inflate(R.layout.item_viewpage_layout, null, false);
            views.add(childView);
        }

        //设置指示器
        setUpCircle();

        adapter = new ViewPageAdapter(views, clazzes);
        viewPager.setAdapter(adapter);

        return view;
    }

    public void setUpCircle(){
        //添加小圆点，通过view的size来决定其中一个imageview是否gone
        final Drawable drawable_normal = getActivity().getResources().getDrawable(R.drawable.circle_normal);
        final Drawable drawable_select = getActivity().getResources().getDrawable(R.drawable.circle_select);

        if (views.size() == 1){
            circle1.setBackground(drawable_select);
            circle2.setVisibility(View.GONE);
            circle3.setVisibility(View.GONE);
        } else if (views.size() == 2){
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
        } else if (views.size() == 3){
            circle1.setBackground(drawable_select);
            circle2.setBackground(drawable_normal);
            circle3.setBackground(drawable_normal);

            //添加页面监听
            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 0) {
                        circle1.setBackground(drawable_select);
                        circle2.setBackground(drawable_normal);
                        circle3.setBackground(drawable_normal);
                    } else if (position == 1){
                        circle2.setBackground(drawable_select);
                        circle1.setBackground(drawable_normal);
                        circle3.setBackground(drawable_normal);
                    } else if (position == 2){
                        circle3.setBackground(drawable_select);
                        circle1.setBackground(drawable_normal);
                        circle2.setBackground(drawable_normal);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
