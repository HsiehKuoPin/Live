package com.live.gblive.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.live.gblive.R;
import com.live.gblive.base.MvpFragment;

import butterknife.BindView;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/17 16:54
 * description:
 */
public class RankFragment extends MvpFragment {
    @BindView(R.id.tv_text)
    TextView mTextView;

    public static RankFragment newInstance() {
        Bundle args = new Bundle();
        RankFragment fragment = new RankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_rank;
    }

    @Override
    public void initView() {
        mTextView.setText(getClass().getName());
    }
}
