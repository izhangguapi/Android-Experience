package com.example.experiment4;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

class MyDialog extends Dialog {
    private Button cancel;
    private Button ok;
    private TextView showdialog_tv;
    private String mydialogname;
    Context context;

    public MyDialog(@NonNull Context context, String dialogname) {
        super(context);
        this.mydialogname = dialogname;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);

        cancel = (Button) findViewById(R.id.cancel);
        ok = (Button) findViewById(R.id.ok);
        showdialog_tv = (TextView) findViewById(R.id.showdialogcontent);
        showdialog_tv.setText(mydialogname);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Toast.makeText(context, "我点击了取消按钮", Toast.LENGTH_LONG).show();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Toast.makeText(context, "我点击了确定按钮", Toast.LENGTH_LONG).show();
            }
        });
    }
}
