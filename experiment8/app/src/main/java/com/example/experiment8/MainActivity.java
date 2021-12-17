package com.example.experiment8;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_SendOrderly,btn_SendDisorder;
    MyReceiverOne myReceiverOne;
    MyReceiverTwo myReceiverTwo;
    MyReceiverThree myReceiverThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDate();
    }
    String action = "76MHz";
    private void initDate() {
        btn_SendOrderly = (Button)findViewById(R.id.btn_SendOrderly);
        btn_SendDisorder = (Button)findViewById(R.id.btn_SendDisorder);

        btn_SendOrderly.setOnClickListener(this);
        btn_SendDisorder.setOnClickListener(this);

        //动态注册广播
        myReceiverOne = new MyReceiverOne();
        myReceiverTwo = new MyReceiverTwo();
        myReceiverThree = new MyReceiverThree();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(action);

        intentFilter.setPriority(200);
        registerReceiver(myReceiverOne,intentFilter);
        intentFilter.setPriority(300);
        registerReceiver(myReceiverTwo,intentFilter);
        intentFilter.setPriority(100);
        registerReceiver(myReceiverThree,intentFilter);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_SendOrderly){
            //发送有序广播
            Intent intent = new Intent();
            intent.setAction(action);
            sendOrderedBroadcast(intent,null);
        }
        if (v.getId() == R.id.btn_SendDisorder){
            //发送无序广播
            Intent intent = new Intent();
            intent.setAction(action);
            sendBroadcast(intent);
        }
    }
}