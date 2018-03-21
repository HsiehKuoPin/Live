package com.live.gblive.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.leeorz.lib.base.BaseActivity;
import com.leeorz.lib.base.BasePresenter;
import com.live.gblive.R;
import com.live.gblive.utils.StatusBarCompat;
import com.umeng.message.PushAgent;

import butterknife.ButterKnife;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/8 14:30
 * description:
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mPresenter;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getRootViewId());
        /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){//4.4 全透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);//calculateStatusColor(Color.WHITE, (int) alphaValue)
        }*/
        StatusBarCompat.compat(this,getResources().getColor(R.color.colorPrimaryDark));
        ButterKnife.bind(this);
        initView();
        initData();
        PushAgent.getInstance(this).onAppStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destroy();
        }
    }


    public abstract int getRootViewId();

    public void initView(){}
    public void initData(){}
}
