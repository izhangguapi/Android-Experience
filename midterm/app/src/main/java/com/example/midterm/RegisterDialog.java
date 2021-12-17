package com.example.midterm;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterDialog extends Dialog implements View.OnClickListener {
    Context contextActivity;
    private Button cancelBtn, registerBtn;
    private EditText username, nickname, password,passwordAgain;


    public DBHelper baseAdepter;
    public UserInfo userinfo;

    public RegisterDialog(Context context, DBHelper baseAdepter) {
        super(context);
        this.baseAdepter = baseAdepter;
        contextActivity = context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_dialog);

        userinfo = new UserInfo();

        username = (EditText) findViewById(R.id.username);
        nickname = (EditText) findViewById(R.id.nickname);
        password = (EditText) findViewById(R.id.password);
        passwordAgain = (EditText) findViewById(R.id.passwordAgain);

        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        cancelBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerBtn:
                String pwd, pwdAgain;
                pwd = password.getText().toString();
                pwdAgain = passwordAgain.getText().toString();
                if (pwd.equals(pwdAgain) != true) {
                    Toast.makeText(contextActivity, "两次输入密码不一致，请重新输入！", Toast.LENGTH_SHORT).show();
                } else {
                    userinfo.Username = username.getText().toString();
                    userinfo.Password = pwd;
                    userinfo.Nickname = nickname.getText().toString();
                    baseAdepter.open();
                    baseAdepter.insert(userinfo);
                    baseAdepter.close();
                    Toast.makeText(contextActivity, "注册成功", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
                break;
            case R.id.cancelBtn:
                dismiss();
                break;
            default:
                break;
        }
    }
}
