package com.live.gblive.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.benjamin.app.AppConfig;
import com.live.gblive.R;


/**
 * Created by lee on 2017/4/23.
 */

public class AppMsgUtil {

    private static AppToast toast;

    private static AppToast getToast(Context context,int layoutId){
        if(toast == null){
            toast = new AppToast(context,layoutId);
        }else{
            if(toast.getLayoutId() != layoutId){
                toast = new AppToast(context,layoutId);
            }
        }

        return toast;
    }

    /**
     * 显示toast
     * @param context
     * @param msg
     */
    public static void show(Context context,String msg){
        AppToast toast = getToast(context, R.layout.toast_app_msg);
        toast.setText(msg);
        toast.show();
    }

    /**
     * 显示哭脸toast
     * @param context
     * @param msg
     */
    public static void showFail(Context context,String msg){
        AppToast toast = getToast(context,R.layout.toast_fail_app_msg);
        toast.setText(msg);
        toast.show();
    }



    private static class AppToast extends Toast{

        private static final int LONG_DELAY = 3500; // 3.5 seconds
        private static final int SHORT_DELAY = 2000; // 2 seconds
        private long lastShowTime = 0;
        private TextView tvMessage;
        private View layout;
        private int layoutId;

        public AppToast(Context context,int layoutId) {
            super(context);
            this.layoutId = layoutId;
            layout = LayoutInflater.from(context).inflate(layoutId,null);
            tvMessage = (TextView) layout.findViewById(android.R.id.message);
            tvMessage.setLayoutParams(new LinearLayout.LayoutParams((int) (AppConfig.SCREEN_WIDTH * 0.6), ViewGroup.LayoutParams.WRAP_CONTENT));
            setDuration(Toast.LENGTH_SHORT);
            setGravity(Gravity.CENTER,0,0);
            setView(layout);
        }

        public int getLayoutId() {
            return layoutId;
        }

        @Override
        public void setText(int resId) {
            tvMessage.setText(resId);
        }

        @Override
        public void setText(CharSequence s) {
            tvMessage.setText(s);
        }

        @Override
        public void show() {
            long nowTime = System.currentTimeMillis();
            if(nowTime - (getDuration() == Toast.LENGTH_LONG?LONG_DELAY:SHORT_DELAY) > lastShowTime){
                super.show();
                lastShowTime = nowTime;
            }
        }
    }

}
