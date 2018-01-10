package com.live.gblive.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.leeorz.lib.base.BaseActivity;
import com.leeorz.lib.base.BasePresenter;
import com.live.gblive.R;
import com.live.gblive.utils.StatusBarCompat;

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
        StatusBarCompat.compat(this,getResources().getColor(R.color.colorPrimaryDark));
        initView();
        initData();
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
