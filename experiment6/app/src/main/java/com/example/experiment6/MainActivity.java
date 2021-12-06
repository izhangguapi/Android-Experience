package com.example.experiment6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_number, et_password;
    private Button btn_login;
    private CheckBox issave;
    private TextView tv_regedit;
    private DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        initDate();
        //从文件中取账号和密码
        readAccountAndPasswd();
        Log.i("MainActivity","onCreate");
    }

    private void readAccountAndPasswd() {
        Map<String, String> usrMap = SaveQQFile.getUserInfo(this);
        if (usrMap != null){
            String account = usrMap.get("account");
            String passwd = usrMap.get("password");
            et_number.setText(account);
            et_password.setText(passwd);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity","onStart");
    }

    private void initDate() {
        //实例化数据库
        dbAdapter = new DBAdapter(this);
        //账号密码
        et_number = (EditText) findViewById(R.id.et_number);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        //注册按钮
        tv_regedit = (TextView)findViewById(R.id.tv_regedit);
        tv_regedit.setOnClickListener(this);
        //是否保存复选框
        issave = (CheckBox) findViewById(R.id.issave);
        issave.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //保存文件
                    String account = et_number.getText().toString();
                    String passwd = et_password.getText().toString();
                    SaveQQFile.saveUserInfo(MainActivity.this,account,passwd);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            //如果账号和密码都为123，弹出对应的对话框，否则也弹出
            String num = et_number.getText().toString();
            String passwd = et_password.getText().toString();
            //查寻数据库，得到注册过的账号和密码
            dbAdapter.open();
            UsrInfo[] usrInfos=dbAdapter.queryOneData(num);
            dbAdapter.close();
            //判断账号是否存在
            if(num.equals(usrInfos[0].Number)&&passwd.equals(usrInfos[0].Passwd))
            //if (num.equals("123") && passwd.equals("123"))
            {
                //弹出对话框
                //MyDialog myDialog = new MyDialog(this, "登录成功");
                //myDialog.show();
                //按照实验4的要求，跳转到新的界面
                Intent intent = new Intent(MainActivity.this,LoadSuccessActivity.class);
                intent.putExtra("account",num);
                startActivity(intent);
                
            } else {
                MyDialog myDialog = new MyDialog(this, "登录失败，检查账号和密码");
                myDialog.show();
            }

        }
        //点击注册后的响应
        if(v.getId() == R.id.tv_regedit){
            //弹出"注册"的自定义对话框
            RegeditDialog regeditDialog = new RegeditDialog(this,dbAdapter);
            regeditDialog.show();
        }
    }
}