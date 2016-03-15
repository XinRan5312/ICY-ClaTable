package com.example.zane.icy_clatable.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;
import android.widget.Toast;

import com.example.zane.icy_clatable.R;
import com.example.zane.icy_clatable.app.App;
import com.example.zane.icy_clatable.utils.TimeCaluUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Zane on 16/3/14.
 */
public class ClassTableActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private GridView gridView;
    private ClassTableGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_classtable_layout);

        Toast.makeText(ClassTableActivity.this, String.valueOf(TimeCaluUtils.CaluDays()), Toast.LENGTH_SHORT).show();

        adapter = new ClassTableGridAdapter(App.getInstance());

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("HAHAH");
        toolbar.setTitleTextColor(Color.WHITE);
        gridView = (GridView)findViewById(R.id.gridview_class);
        gridView.setAdapter(adapter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


    }
}
