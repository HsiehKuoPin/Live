package com.live.gblive.utils;

import android.util.Log;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/11 17:50
 * description:日志打印
 */
public class LogUtil {
    private static final boolean mDebug = true;

    private static final String TAG = "gblive";

    private static int logId = 0;
    /** 为了方便查看，在打印的日志信息内容前添加一个序号前缀 */
    private static String getLogPrefix() {
        logId++;
        if (logId >= 1000)
            logId = 1;
        return "(" + logId + "). ";
    }

    /** 在控制台打印信息 */
    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void w(String msg) {
        w(TAG, msg);
    }

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void i(String tag, String msg) {
        String mess = getLogPrefix() + msg;
        if (mDebug)
            Log.i(tag, mess);
    }

    public static void d(String tag, String msg) {
        String mess = getLogPrefix() + msg;
        if (mDebug)
            Log.d(tag, mess);
    }

    public static void w(String tag, String msg) {
        String mess = getLogPrefix() + msg;
        if (mDebug)
            Log.w(tag, mess);
    }

    public static void v(String tag, String msg) {
        String mess = getLogPrefix() + msg;
        if (mDebug)
            Log.v(tag, mess);
    }

    public static void e(String tag, String msg) {
        String mess = getLogPrefix() + msg;
        if (mDebug)
            Log.e(tag, mess);
    }


}
