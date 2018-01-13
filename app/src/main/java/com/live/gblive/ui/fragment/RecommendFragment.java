package com.live.gblive.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class RecommendFragment extends MvpFragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public static RecommendFragment newInstance() {

        Bundle args = new Bundle();

        RecommendFragment fragment = new RecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initView() {
        mTvTitle.setText("直播");
        mRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }

    @Override
    public void initData() {

    }

}
