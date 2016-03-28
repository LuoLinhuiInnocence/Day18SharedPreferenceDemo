package com.luo.day18sharedpreferencedemo;

import android.widget.Toast;

/**
 * Created by dllo on 16/1/29.
 * 自定义的Toast的简单封装
 * 目标：
 * 不需要Context，只给一个字符串就可以
 */
public class MyToast {

    private static boolean isShow = true;

    public static void myToast(String data) {
        if (isShow){
            Toast.makeText(BaseApplication.getContext(), data, Toast.LENGTH_SHORT).show();
        }

    }


}
