package com.example.experiment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username, password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

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

    private void initData() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            String user = username.getText().toString();
            String pwd = password.getText().toString();
            if (user.equals("") || pwd.equals("")) {
                Toast.makeText(this, "账号或密码为空", Toast.LENGTH_LONG).show();
            } else if (user.equals("123") && pwd.equals("123")) {
                myDialog myDialog_success = new myDialog(this, "登录成功！");
                myDialog_success.show();
            } else {
                myDialog myDialog_error = new myDialog(this, "账号或密码错误！");
                myDialog_error.show();
            }
        }
    }
}