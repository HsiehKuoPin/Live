package com.live.gblive.presenter;

import com.benjamin.base.BasePresenter;
import com.live.gblive.contract.LiveContract;
import com.live.gblive.model.LiveModel;
import com.live.gblive.model.bean.LiveInfo;

import java.util.List;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/18 16:58
 * description:
 */
public class LivePresenter extends BasePresenter<LiveContract.View,LiveModel> implements LiveContract.Presenter, LiveContract.OnRequestListener {
    public LivePresenter(LiveContract.View view) {
        super(view);
    }

    @Override
    public void loadSlugCategories(String slug) {
        if (getIView() ==null)return;
        mModel.loadSlugCategories(slug,this);
    }



    @Override
    public void onGetSlugCategoriesSuccess(List<LiveInfo> liveInfoList) {
        if (getIView() ==null)return;
        getIView().getSlugCategoriesSuccess(liveInfoList);
    }

    @Override
    public void onGetSlugCategoriesFail(String message) {
        if (getIView() ==null)return;
        getIView().getSlugCategoriesFail(message);
    }
}
