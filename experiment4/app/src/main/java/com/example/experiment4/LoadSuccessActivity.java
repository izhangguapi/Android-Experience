package com.example.experiment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class LoadSuccessActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_success);

        textView = (TextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        //找到文本显示的控件，将user显示到文本个控件上
        textView.setText(user);
    }
}