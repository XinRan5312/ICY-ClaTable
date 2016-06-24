package com.example.zane.icy_clatable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zane.icy_clatable.calendar_ui.CarlendarActivity;
import com.example.zane.icy_clatable.clazz_ui.ClassTableActivity;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button_calendar;
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

        //默认是本科生了这里!!!!
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextId.getText().toString() != null) {
                    Intent intent = new Intent(MainActivity.this, ClassTableActivity.class);
                    intent.putExtra(CLAZZ, editTextId.getText().toString());
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "学号不能为空哦!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
