package com.live.gblive.presenter;

import com.leeorz.lib.base.BasePresenter;
import com.live.gblive.contract.RecommendContract;
import com.live.gblive.model.RecommendModel;
import com.live.gblive.model.bean.RecommendSection;

import java.util.List;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/13 17:55
 * description:
 */
public class RecommendPresenter extends BasePresenter<RecommendContract.View,RecommendModel> implements RecommendContract.Presenter, RecommendContract.OnRequestListener {
    public RecommendPresenter(RecommendContract.View view) {
        super(view);
    }

    @Override
    public void getRecommend() {
        if (getIView() ==null)return;
        mModel.loadRecommend(this);
    }

    @Override
    public void onRecommendSuccess(List<RecommendSection> recommendSectionList) {
        if (getIView() ==null)return;
        getIView().getRecommendSuccess(recommendSectionList);
    }

    @Override
    public void onRecommendFail(String message) {
        if (getIView() ==null)return;
        getIView().getRecommendFail(message);
    }
}
