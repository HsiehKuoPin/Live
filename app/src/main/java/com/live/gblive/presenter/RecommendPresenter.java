package com.live.gblive.presenter;

import com.benjamin.base.BasePresenter;
import com.live.gblive.contract.RecommendContract;
import com.live.gblive.model.RecommendModel;
import com.live.gblive.model.bean.Banner;
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
    public void getAppStartInfo() {
        if (getIView() ==null)return;
        mModel.loadAppStartInfo(this);
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

    @Override
    public void onAppStartInfoSuccess(List<Banner> bannerList) {
        if (getIView() ==null)return;
        getIView().getAppStartInfoSuccess(bannerList);
    }

    @Override
    public void onAppStartInfoFail(String message) {
        if (getIView() ==null)return;
        getIView().getAppStartInfoFail(message);
    }
}
