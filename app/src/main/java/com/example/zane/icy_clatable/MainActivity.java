package com.example.zane.icy_clatable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zane.icy_clatable.calendar_ui.CarlendarActivity;
import com.example.zane.icy_clatable.clazz_ui.ClassTableActivity;
import com.example.zane.icy_clatable.data.ClassModel;
import com.example.zane.icy_clatable.data.bean.Clazz;
import com.example.zane.icy_clatable.data.bean.Clazz_Two;
import com.kermit.exutils.utils.ExUtils;

import rx.Subscriber;
import rx.functions.Action1;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button_calendar;
    private ClassModel classModel;
    private EditText editTextId;
    private static final String TAG = "MainActiivty2";
    public static final String CLAZZ = "clazz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextId = (EditText)findViewById(R.id.edittext_id);
        button = (Button)findViewById(R.id.button);
        button_calendar = (Button)findViewById(R.id.button_calendar);
        button_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CarlendarActivity.class));
            }
        });

        classModel = ClassModel.getInstance();

        //默认是本科生了这里!!!!
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextId.getText().toString() != null) {
                    classModel.getClassData(editTextId.getText().toString(), "bks")
                            .subscribe(new Subscriber<Clazz_Two>() {
                                @Override
                                public void onCompleted() {
                                }
                                @Override
                                public void onError(Throwable e) {
                                    ExUtils.Toast(String.valueOf(e));
                                }

                                @Override
                                public void onNext(Clazz_Two clazz_two) {
                                    if (clazz_two.getData() != null){
                                        Intent intent = new Intent(MainActivity.this, ClassTableActivity.class);
                                        intent.putExtra(CLAZZ, clazz_two);
                                        startActivity(intent);
                                    }
                                }
                            });
                }else {
                    Toast.makeText(MainActivity.this, "学号不能为空哦!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
