package com.example.experiment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username, password;
    private Button login;
    private CheckBox issave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDate();
        Log.i("MainActivity", "调用onCreate()");
        Map<String, String> userInfo = SaveFile.getUserInfo(this);//工具类
        if (userInfo != null) {
            username.setText(userInfo.get("user"));
            password.setText(userInfo.get("pwd"));
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

    private void initDate() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        issave = (CheckBox) findViewById(R.id.issave);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            //如果账号和密码都为123，弹出对应的对话框，否则也弹出
//            String num = et_number.getText().toString();
//            String passwd = et_password.getText().toString();
//            if (num.equals("123") && passwd.equals("123")) {
//                //弹出对话框
//                MyDialog myDialog = new myDialog(this, "登录成功");
//                myDialog.show();
//                Intent intent = new Intent(MainActivity.this,LoadSuccessActivity.class);
//                intent.putExtra("user",num);
//                startActivity(intent);
//            } else {
//                MyDialog myDialog = new MyDialog(this, "登录失败，检查账号和密码");
//                myDialog.show();
//            }
            String user = username.getText().toString();
            String pwd = password.getText().toString();
            if (user.equals("") || pwd.equals("")) {
                Toast.makeText(this, "账号或密码为空", Toast.LENGTH_LONG).show();
            } else if (user.equals("123") && pwd.equals("123")) {
                //弹出对话框
//                MyDialog myDialog_success = new MyDialog(this, "登录成功！");
//                myDialog_success.show();
                //跳转页面并传值
                Intent intent = new Intent(MainActivity.this,LoadSuccessActivity.class);
                intent.putExtra("user","欢迎您"+user);
                startActivity(intent);
                if (issave.isChecked()){
                    boolean isSaveSuccess = SaveFile.saveUserInfo(MainActivity.this, user, pwd);
                    if (isSaveSuccess) {
                        Toast.makeText(this, "保存成功！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "保存失败！", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                //弹出对话框
//                MyDialog myDialog_error = new MyDialog(this, "账号或密码错误！");
//                myDialog_error.show();
                Intent intent = new Intent(MainActivity.this,LoadSuccessActivity.class);
                intent.putExtra("user","密码错误");
                startActivity(intent);
            }
        }
    }
}