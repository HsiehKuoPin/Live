package com.live.gblive.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.live.gblive.R;
import com.live.gblive.base.MvpFragment;
import com.live.gblive.nim.SessionHelper;

import butterknife.BindView;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/8 17:17
 * description:
 */
public class MineFragment extends MvpFragment {
    @BindView(R.id.btn)
    Button btn;
    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SessionHelper.startP2PSession(mContext,"ww");
            }
        });
    }

}
