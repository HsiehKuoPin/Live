package com.live.gblive.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.live.gblive.R;
import com.live.gblive.base.MvpFragment;

import butterknife.BindView;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/8 17:17
 * description:
 */
public class HomeFragment extends MvpFragment {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        mTvTitle.setText("直播");
    }

}
