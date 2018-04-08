package com.benjamin.utils;

import android.util.Log;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * description:日志打印
 */
public class LogUtil {
    private static final boolean mDebug = true;

    private static final String TAG = "myLog";
    private static final String I = "I";
    private static final String D = "D";
    private static final String W = "W";
    private static final String V = "V";
    private static final String E = "E";

    private static int logId = 0;

    /**
     * 为了方便查看，在打印的日志信息内容前添加一个序号前缀
     */
    private static String getLogPrefix() {
        logId++;
        if (logId >= 1000)
            logId = 1;
        return "(" + logId + "). ";
    }

    /**
     * 在控制台打印信息
     */
    public static void i(String msg) {
        log(TAG, msg, I);
    }

    public static void d(String msg) {
        log(TAG, msg, D);
    }

    public static void w(String msg) {
        log(TAG, msg, W);
    }

    public static void v(String msg) {
        log(TAG, msg, V);
    }

    public static void e(String msg) {
        log(TAG, msg, E);
    }

    public static void log(String tag, String mess, String type) {
        if (!mDebug) return;
        String msg = getLogPrefix() + mess;
        switch (type) {
            case I:
                Log.i(tag, msg);
                break;
            case D:
                Log.d(tag, msg);
                break;
            case W:
                Log.w(tag, msg);
                break;
            case V:
                Log.v(tag, msg);
                break;
            case E:
                Log.e(tag, msg);
                break;
        }

    }
}
