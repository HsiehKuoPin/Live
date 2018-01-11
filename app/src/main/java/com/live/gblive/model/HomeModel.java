package com.live.gblive.model;

import com.leeorz.lib.api.ApiManager;
import com.live.gblive.contract.HomeContract;
import com.live.gblive.model.bean.LiveCategory;
import com.live.gblive.model.protocol.BaseProtocol;
import com.live.gblive.model.protocol.MObserver;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/11 16:00
 * description:
 */
public class HomeModel extends BaseProtocol implements HomeContract.Model{

    @Override
    public void loadAllCategories(final HomeContract.OnRequestListener listener) {
        ApiManager.getInstance().add(this,mAPIService.getAllCategories())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MObserver(new MObserver.ApiCallback<List<LiveCategory>>() {
                    @Override
                    public void onSuccess(List<LiveCategory> value) {
                        listener.onGetLiveCategorySuccess(value);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        listener.onGetLiveCategoryFail(e.getMessage());
                    }
                }));
    }
}
