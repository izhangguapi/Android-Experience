package com.example.experiment5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username, password;
    private Button login;
    private CheckBox checkBox;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDate();
        spSeva();
        Log.i("MainActivity", "调用onCreate()");
    }

    /**
     * 根据控件的ID找到对象
     */
    private void initDate() {
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        checkBox = (CheckBox) findViewById(R.id.issave);
    }

    /**
     * SharedPreferences获取账号密码是否勾选保存密码
     */
    private void spSeva() {
        SharedPreferences sp = getSharedPreferences("data", Context.MODE_PRIVATE);
        String user = sp.getString("user", null);
        String pwd = sp.getString("pwd", null);
        boolean issave = sp.getBoolean("isChecked", false);
        username.setText(user);
        password.setText(pwd);
        checkBox.setChecked(issave);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            String user = username.getText().toString();
            String pwd = password.getText().toString();
            if (user.equals("") || pwd.equals("")) {
                Toast.makeText(this, "账号或密码为空", Toast.LENGTH_LONG).show();
            } else if (user.equals("123") && pwd.equals("123")) {
                //SharedPreferences保存
                SharedPreferences sp = getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                if (checkBox.isChecked()) {
                    editor.putString("user", user);
                    editor.putString("pwd", pwd);
                    Toast.makeText(this, "登录成功，保存成功！", Toast.LENGTH_SHORT).show();
                } else {
                    editor.clear();
                    Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
                }
                editor.putBoolean("isChecked", checkBox.isChecked());
                editor.apply();
            } else {
                Toast.makeText(this, "密码错误！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void onStart() {
        super.onStart();
        Log.i("MainActivitylife", "调用onStart()");
    }

    protected void onResume() {
        super.onResume();
        Log.i("MainActivitylife", "调用onResume()");
    }

    protected void onPause() {
        super.onPause();
        Log.i("MainActivitylife", "调用onPause()");
    }

    protected void onStop() {
        super.onStop();
        Log.i("MainActivitylife", "调用onStop()");
    }
}