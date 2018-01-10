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
public class FollowFragment extends MvpFragment {

    public static FollowFragment newInstance() {

        Bundle args = new Bundle();

        FollowFragment fragment = new FollowFragment();
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
