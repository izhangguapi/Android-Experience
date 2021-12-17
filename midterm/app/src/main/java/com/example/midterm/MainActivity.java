package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username, password;
    private CheckBox is_save;
    private TextView regedit;
    private Button login;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDate();
    }

    private void initDate() {
        //实例化数据库
        dbHelper = new DBHelper(this);

        //初始化控件
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        regedit = (TextView) findViewById(R.id.regedit);
        regedit.setOnClickListener(this);
        is_save = (CheckBox) findViewById(R.id.is_save);
    }

    @Override
    public void onClick(View v) {
        //点击登录
        if (v.getId() == R.id.login) {
            String _username = null, _password = null, _nickname = null;
            //查寻数据库，得到注册过的账号和密码
            try {
                //打开数据库
                dbHelper.open();
                //根据账号查询密码和昵称
                UserInfo[] usrInfo = dbHelper.queryOneData(username.getText().toString());
                //赋值
                _username = usrInfo[0].Username;
                _password = usrInfo[0].Password;
                _nickname = usrInfo[0].Nickname;
                //关闭数据库
                dbHelper.close();
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "出现异常，登录失败！", Toast.LENGTH_SHORT).show();
            }
            //判断账号是否存在
            if (username.getText().toString().equals(_username) && password.getText().toString().equals(_password)) {
                //跳转到新的界面
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("nickname", _nickname);
                intent.putExtra("username", _username);
                startActivity(intent);

            } else {
                Toast.makeText(MainActivity.this, "账号密码错误！", Toast.LENGTH_SHORT).show();
            }
        }
        //点击注册
        if (v.getId() == R.id.regedit) {
            //弹出"注册"的自定义对话框
            RegisterDialog registerDialog = new RegisterDialog(this, dbHelper);
            registerDialog.show();
        }
    }
}