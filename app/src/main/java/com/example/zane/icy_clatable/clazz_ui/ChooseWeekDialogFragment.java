package com.example.zane.icy_clatable.clazz_ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.zane.icy_clatable.R;
import com.example.zane.icy_clatable.app.App;
import com.jude.utils.JUtils;

/**
 * Created by Zane on 16/3/14.
 */
public class ChooseWeekDialogFragment extends DialogFragment{

    private RecyclerView recyclerView;
    private ClassTableRecycleViewAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout((int)(JUtils.getScreenWidth() * 0.6), (int)(JUtils.getScreenHeight() * 0.5));
        window.setGravity(Gravity.CENTER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialogfragment_chooseweek_layout, container, false);

        //setup RecycleView
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleview_weeks);
        adapter = new ClassTableRecycleViewAdapter(App.getInstance());
        manager = new LinearLayoutManager(App.getInstance());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        return view;
    }


}
