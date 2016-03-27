package com.example.zane.icy_clatable.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zane.icy_clatable.R;
import com.example.zane.icy_clatable.data.ClassModel;
import com.example.zane.icy_clatable.data.bean.Clazz;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private Button button;
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
        classModel = ClassModel.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, editTextId.getText().toString());
                if (editTextId.getText().toString() != null) {
                    classModel.getClassData(editTextId.getText().toString())
                            .subscribe(new Subscriber<Clazz>() {
                                @Override
                                public void onCompleted() {
                                }
                                @Override
                                public void onError(Throwable e) {
                                    Toast.makeText(MainActivity.this, String.valueOf(e) + "    error", Toast.LENGTH_SHORT).show();
                                }
                                @Override
                                public void onNext(Clazz clazz) {
                                    Intent intent = new Intent(MainActivity.this, ClassTableActivity.class);
                                    intent.putExtra(CLAZZ, clazz);
                                    startActivity(intent);
                                }
                            });
                }else {
                    Toast.makeText(MainActivity.this, "学号不能为空哦!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
