package com.live.gblive.ui.fragment;

import android.os.Bundle;

import com.live.gblive.R;
import com.live.gblive.base.MvpFragment;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/8 17:17
 * description:
 */
public class LiveListFragment extends MvpFragment {

    public static LiveListFragment newInstance() {

        Bundle args = new Bundle();

        LiveListFragment fragment = new LiveListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getRootViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {

    }

}
