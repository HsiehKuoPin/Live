package com.live.gblive.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.benjamin.app.Global;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.live.gblive.R;
import com.live.gblive.base.MvpFragment;
import com.live.gblive.contract.LiveContract;
import com.live.gblive.model.bean.LiveInfo;
import com.live.gblive.presenter.LivePresenter;
import com.live.gblive.ui.adapter.LiveAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/8 17:17
 * description:
 */
public class LiveListFragment extends MvpFragment<LivePresenter> implements LiveContract.View {
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private String slug;
    private List<LiveInfo> mInfoList;
    private LiveAdapter mLiveAdapter;

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
        mPresenter = new LivePresenter(this);
        mInfoList = new ArrayList<>();
        mLiveAdapter = new LiveAdapter(R.layout.list_live_item,mInfoList);
        mLiveAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startRoom(mInfoList.get(position));
            }
        });
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(mLiveAdapter);
    }

    @Override
    public void initData() {
        mPresenter.loadSlugCategories(slug);
    }

    @Override
    public void getSlugCategoriesSuccess(List<LiveInfo> liveInfoList) {
        toSetList(mInfoList,liveInfoList,false);
        mLiveAdapter.notifyDataSetChanged();
    }

    @Override
    public void getSlugCategoriesFail(String message) {
        Global.showFail(message);
    }
}
