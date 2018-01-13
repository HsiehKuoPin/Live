package com.live.gblive.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.live.gblive.R;
import com.live.gblive.base.MvpFragment;
import com.live.gblive.contract.RecommendContract;
import com.live.gblive.model.bean.Recommend;
import com.live.gblive.model.bean.RecommendSection;
import com.live.gblive.presenter.RecommendPresenter;
import com.live.gblive.ui.adapter.RecommendAdapter;
import com.live.gblive.utils.AppMsgUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/8 17:17
 * description:
 */
public class RecommendFragment extends MvpFragment<RecommendPresenter> implements RecommendContract.View {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private List<RecommendSection> data;
    private RecommendAdapter mRecommendAdapter;

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
        data = new ArrayList<>();
        mRecommendAdapter = new RecommendAdapter(R.layout.list_live_item, R.layout.list_remmend_item, data);
        mRecommendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Recommend.RoomBean.ListBean listBean = data.get(position).t;
                AppMsgUtil.show(getActivity(),listBean.getNick());
            }
        });
        mRecommendAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RecommendSection recommendSection = data.get(position);
                AppMsgUtil.show(getActivity(),recommendSection.header);
            }
        });
        mRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerview.setAdapter(mRecommendAdapter);
        mPresenter = new RecommendPresenter(this);
    }

    @Override
    public void initData() {
        mPresenter.getRecommend();
    }

    @Override
    public void getRecommendSuccess(List<RecommendSection> recommendSectionList) {
        if(recommendSectionList!=null){
            toSetList(data,recommendSectionList,false);
            mRecommendAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getRecommendFail(String message) {
        AppMsgUtil.showFail(getActivity(),message);
    }
}
