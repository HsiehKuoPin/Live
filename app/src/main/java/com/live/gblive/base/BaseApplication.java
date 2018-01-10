package com.live.gblive.base;

import android.app.Application;
import android.content.Context;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/8 14:16
 * description:
 */
public class BaseApplication extends Application {
    private static Context applicationContext;

    public static Context getContext() {
        return applicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
    }
}
