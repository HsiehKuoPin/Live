package com.live.gblive.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leeorz.lib.base.BaseFragment;
import com.leeorz.lib.base.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/8 14:32
 * description:
 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P mPresenter;
    private View rootView;
    Unbinder unbinder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getRootViewId(),container,false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.destroy();
        }
    }

    public abstract int getRootViewId();

    public void initView() {

    }

    public void initData() {

    }
}
