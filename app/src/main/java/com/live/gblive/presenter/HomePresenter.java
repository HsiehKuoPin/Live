package com.live.gblive.presenter;

import com.benjamin.base.BasePresenter;
import com.live.gblive.contract.HomeContract;
import com.live.gblive.model.HomeModel;
import com.live.gblive.model.bean.LiveCategory;

import java.util.List;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/11 14:37
 * description:
 */
public class HomePresenter extends BasePresenter<HomeContract.View,HomeModel> implements HomeContract.Presenter, HomeContract.OnRequestListener {
    public HomePresenter(HomeContract.View view) {
        super(view);
    }

    @Override
    public void loadAllCategories() {
        if (getIView() ==null)return;
        mModel.loadAllCategories(this);
    }

    @Override
    public void onGetLiveCategorySuccess(List<LiveCategory> list) {
        if (getIView() ==null)return;
        getIView().getLiveCategorySuccess(list);
    }

    @Override
    public void onGetLiveCategoryFail(String message) {
        if (getIView() ==null)return;
        getIView().getLiveCategoryFail(message);
    }
}
