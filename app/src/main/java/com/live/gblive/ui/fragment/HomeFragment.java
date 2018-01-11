package com.live.gblive.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.live.gblive.R;
import com.live.gblive.base.MvpFragment;
import com.live.gblive.contract.HomeContract;
import com.live.gblive.model.bean.LiveCategory;
import com.live.gblive.presenter.HomePresenter;
import com.live.gblive.ui.adapter.ViewPagerFragmentAdapter;
import com.live.gblive.utils.AppMsgUtil;
import com.live.gblive.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/8 17:17
 * description:
 */
public class HomeFragment extends MvpFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;

    private List<LiveCategory> listCategory;

    private List<CharSequence> listTitle;

    private List<Fragment> listFragment;

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
        mTvTitle.setText("标题");
        listCategory = new ArrayList<>();
        listTitle = new ArrayList<>();
        listFragment = new ArrayList<>();

        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), listFragment, listTitle);

        mViewPager.setAdapter(viewPagerFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mPresenter = new HomePresenter(this);
    }

    @Override
    public void initData() {
        mPresenter.loadAllCategories();
    }

    @Override
    public void onGetLiveCategorySuccess(List<LiveCategory> list) {
        LogUtil.e(""+ JSON.toJSONString(list.get(0)));
    }

    @Override
    public void onGetLiveCategoryFail(String message) {
        AppMsgUtil.showFail(getActivity(), message);
    }
}
