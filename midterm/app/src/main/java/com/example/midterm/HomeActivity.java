package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private TextView nickname, username;
    private ListView listView;
    private DBHelper dbHelper;

    private ArrayList<String> _nickname = new ArrayList<>();
    private ArrayList<String> _username = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        //实例化数据库
        dbHelper = new DBHelper(this);

        //初始化控件
        listView = (ListView) findViewById(R.id.list_view);
        nickname = (TextView) findViewById(R.id.nickname);
        username = (TextView) findViewById(R.id.username);

        //创建一个Adapter的实例
        MyBaseAdapter mybaseAdapter = new MyBaseAdapter();
        //设置Adapter
        listView.setAdapter(mybaseAdapter);

        //接收传过来的数据
        Intent intent = getIntent();
        nickname.setText("你好：" + intent.getStringExtra("nickname"));
        username.setText("账号：" + intent.getStringExtra("username"));

        //打开数据库
        dbHelper.open();
        //查询数据
        UserInfo[] userInfo = dbHelper.queryNicknameAndUsername(intent.getStringExtra("username"));//username.getText().toString()
        //赋值
        for (int i = 0; i < userInfo.length; i++) {
            _nickname.add(userInfo[i].Nickname);//[i] =usrInfo[i].Nickname;
            _username.add(userInfo[i].Username);
        }
        //关闭数据库
        dbHelper.close();
    }

    class MyBaseAdapter extends BaseAdapter {
        //得到item的总数
        @Override
        public int getCount() {
            return _nickname.size();
        }

        //得到item代表的对象
        @Override
        public Object getItem(int position) {
            return position;
        }

        //得到item的id
        @Override
        public long getItemId(int position) {
            return position;
        }

        //得到item的view试图
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_item, parent, false);

                //对viewHolder的属性进行赋值
                viewHolder.nickname_tv = (TextView) convertView.findViewById(R.id.item_nickname);
                viewHolder.username_tv = (TextView) convertView.findViewById(R.id.item_username);

                //通过setTag将convertView与viewHolder关联
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.nickname_tv.setText("昵称：" + _nickname.get(position));
            viewHolder.username_tv.setText("账号：" + _username.get(position));
            return convertView;
        }

        class ViewHolder {
            public TextView nickname_tv;
            public TextView username_tv;
        }
    }
}