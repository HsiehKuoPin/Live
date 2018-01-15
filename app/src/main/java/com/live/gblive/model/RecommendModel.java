package com.live.gblive.model;

import com.leeorz.lib.api.ApiManager;
import com.live.gblive.contract.RecommendContract;
import com.live.gblive.model.bean.AppStart;
import com.live.gblive.model.bean.Recommend;
import com.live.gblive.model.protocol.BaseProtocol;
import com.live.gblive.model.protocol.MObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/11 16:00
 * description:
 */
public class RecommendModel extends BaseProtocol implements RecommendContract.Model{

    @Override
    public void loadRecommend(final RecommendContract.OnRequestListener listener) {
        ApiManager.getInstance().add(this,mAPIService.getRecommend())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MObserver(new MObserver.ApiCallback<Recommend>() {
                    @Override
                    public void onSuccess(Recommend value) {
                        listener.onRecommendSuccess(value.getSampleData());
                    }

                    @Override
                    public void onFail(Throwable e) {
                        listener.onRecommendFail(e.getMessage());
                    }
                }));
    }

    @Override
    public void loadAppStartInfo(final RecommendContract.OnRequestListener listener) {
        ApiManager.getInstance().add(this,mAPIService.getAppStartInfo())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MObserver(new MObserver.ApiCallback<AppStart>() {
                    @Override
                    public void onSuccess(AppStart value) {
                        listener.onAppStartInfoSuccess(value.getBanners());
                    }

                    @Override
                    public void onFail(Throwable e) {
                        listener.onAppStartInfoFail(e.getMessage());
                    }
                }));
    }
}
