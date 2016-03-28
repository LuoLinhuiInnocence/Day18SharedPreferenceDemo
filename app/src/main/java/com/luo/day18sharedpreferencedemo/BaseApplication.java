package com.luo.day18sharedpreferencedemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/1/29.
 */
public class BaseApplication extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }


    public static Context getContext(){
        return context;
    }
}
