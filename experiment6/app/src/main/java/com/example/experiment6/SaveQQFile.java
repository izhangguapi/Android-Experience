package com.example.experiment6;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SaveQQFile {
    //存储文件
    public static boolean saveUserInfo(Context context,String account,String passwd){
        FileOutputStream fos=null;

        //打开文件date.txt
        try {
            fos = context.openFileOutput("date.txt",context.MODE_PRIVATE);
            fos.write((account+":"+passwd).getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //读取文件
    public static Map<String, String> getUserInfo(Context context) {
        String content = "";
        FileInputStream fis = null;
        try {
            //获取文件的输入流对象fis
            fis = context.openFileInput("date.txt");
            //将输入流对象中的数据转换为字节码的形式
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);//通过read()方法读取字节码中的数据
            content = new String(buffer); //将获取的字节码转换为字符串
            Map<String, String> userMap = new HashMap<String, String>();
            String[] infos = content.split(":");//将字符串以“：”分隔后形成一个数组的形式
            userMap.put("account", infos[0]);   //将数组中的第一个数据放入userMap集合中
            userMap.put("password", infos[1]); //将数组中的第二个数据放入userMap集合中
            return userMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                if(fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
