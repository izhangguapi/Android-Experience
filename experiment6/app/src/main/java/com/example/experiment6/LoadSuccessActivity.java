package com.example.experiment6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LoadSuccessActivity extends AppCompatActivity {
    private TextView tv_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_success);
        tv_message = (TextView)findViewById(R.id.tv_message);
        Intent intent = getIntent();
        tv_message.setText("你好："+intent.getStringExtra("account"));
    }
}