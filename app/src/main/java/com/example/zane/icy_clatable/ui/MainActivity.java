package com.example.zane.icy_clatable.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zane.icy_clatable.R;
import com.example.zane.icy_clatable.data.ClassModel;
import com.example.zane.icy_clatable.data.bean.Clazz;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ClassModel classModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        classModel = ClassModel.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classModel.getClassData("2014210876")
                        .subscribe(new Subscriber<Clazz>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(MainActivity.this, String.valueOf(e), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(Clazz clazz) {
                                Toast.makeText(MainActivity.this, String.valueOf(clazz.getClassX().get(0)), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
