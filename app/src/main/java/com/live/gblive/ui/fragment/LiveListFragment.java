package com.live.gblive.ui.fragment;

import android.os.Bundle;
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
public class LiveListFragment extends MvpFragment {
    @BindView(R.id.tv_text)
    TextView mTextView;
    private String slug;

    public static LiveListFragment newInstance(String slug) {
        Bundle args = new Bundle();
        LiveListFragment fragment = new LiveListFragment();
        fragment.slug = slug;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_live_list;
    }

    @Override
    public void initView() {
        mTextView.setText(this.getClass().getName()+":"+slug);
    }

    @Override
    public void initData() {

    }

}
