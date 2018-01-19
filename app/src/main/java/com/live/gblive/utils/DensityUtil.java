package com.live.gblive.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.lang.reflect.Field;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/17 18:24
 * description:
 */
public class DensityUtil {

    private DensityUtil(){
        throw new AssertionError();
    }


    /**
     * dp转px
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpVal,getDisplayMetrics(context));
    }

    /**
     * sp转px
     * @param context
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,spVal,getDisplayMetrics(context));
    }

    /**
     * px转dp
     * @param context
     * @param pxVal
     * @return
     */
    public static int px2dp(Context context, float pxVal){
        return (int) (pxVal / getDisplayMetrics(context).density + 0.5f);
    }

    /**
     * px转sp
     * @param context
     * @param pxVal
     * @return
     */
    public static int px2sp(Context context, float pxVal){
        return (int) (pxVal / getDisplayMetrics(context).scaledDensity + 0.5f);
    }


    /**
     * 获取DisplayMetrics
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context){
        return context.getResources().getDisplayMetrics();
    }

    /**
     * 通过反射的方式获取状态栏高度，
     * 一般为24dp，有些可能较特殊，所以需要反射动态获取
     */
    public static int getStatusBarHeight(Context context) {
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object obj = clazz.newInstance();
            Field field = clazz.getField("status_bar_height");
            int id = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------无法获取到状态栏高度");
        }
        return px2dp(context,24);
    }
}
