package com.example.experiment4;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SaveFile {

    public static boolean saveUserInfo(Context context, String user, String pwd) {
        try {
            //1、通过上下文获取文件输出流
            FileOutputStream fos = context.openFileOutput("data.txt",
                    Context.MODE_PRIVATE);//保存信息到文件中。获取文件输出流。文件名、模式
            //2、把数据写到文件中
            fos.write((user + ":" + pwd).getBytes());//转化为字节数组
            fos.close();//关闭流
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Map<String, String> getUserInfo(Context context) {
        String content = "";
        FileInputStream fis = null;
        try {
            //获取文件的输入流对象fis
            fis = context.openFileInput("data.txt");
            //将输入流对象中的数据转换为字节码的形式
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);//通过read()方法读取字节码中的数据
            content = new String(buffer); //将获取的字节码转换为字符串
            Map<String, String> userMap = new HashMap<String, String>();
            String[] infos = content.split(":");//将字符串以“：”分隔后形成一个数组的形式
            userMap.put("user", infos[0]);   //将数组中的第一个数据放入userMap集合中
            userMap.put("pwd", infos[1]); //将数组中的第二个数据放入userMap集合中
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
