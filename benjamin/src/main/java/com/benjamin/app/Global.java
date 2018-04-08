package com.benjamin.app;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.benjamin.R;

/**
 * 全局公共类
 * 需在baseApplication 初始化
 */
public class Global {

    public static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static Handler getMainHandler() {
        return mHandler;
    }

    /**
     * 判断当前线程是否是主线程
     *
     * @return true表示当前是在主线程中运行
     */
    public static boolean isUIThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static void runOnUIThread(Runnable run) {
        if (isUIThread()) {
            run.run();
        } else {
            mHandler.post(run);
        }
    }

    private static Toast mToast;

    /**
     * 可以在子线程中调用
     *
     * @param msg toast内容
     */
    public static void showToast(final String msg) {
        runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
                }
                mToast.setText(msg);
                mToast.show();
            }
        });
    }

    private static AppToast toast;

    private static AppToast getToast(int layoutId){
        if(toast == null){
            toast = new AppToast(mContext,layoutId);
        }else{
            if(toast.getLayoutId() != layoutId){
                toast = new AppToast(mContext,layoutId);
            }
        }

        return toast;
    }

    /**
     * 显示toast
     * @param msg
     */
    public static void show(String msg){
        AppToast toast = getToast(R.layout.toast_app_msg);
        toast.setText(msg);
        toast.show();
    }

    /**
     * 显示哭脸toast
     * @param msg
     */
    public static void showFail(String msg){
        AppToast toast = getToast(R.layout.toast_fail_app_msg);
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