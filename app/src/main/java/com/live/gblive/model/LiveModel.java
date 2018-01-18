package com.live.gblive.model;

import android.text.TextUtils;

import com.leeorz.lib.api.ApiManager;
import com.live.gblive.contract.LiveContract;
import com.live.gblive.model.bean.LiveListResult;
import com.live.gblive.model.protocol.BaseProtocol;
import com.live.gblive.model.protocol.MObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/18 16:54
 * description:
 */
public class LiveModel extends BaseProtocol implements LiveContract.Model{


    @Override
    public void loadSlugCategories(String slug, LiveContract.OnRequestListener listener) {
        if (TextUtils.isEmpty(slug)){
            getLiveList(listener);
        }else {
            getLiveListBySlug(slug,listener);
        }
    }

    private void getLiveListBySlug(String slug, final LiveContract.OnRequestListener listener) {
        ApiManager.getInstance().add(this,mAPIService.getSlugCategories(slug))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MObserver(new MObserver.ApiCallback<LiveListResult>() {
                    @Override
                    public void onSuccess(LiveListResult value) {
                        listener.onGetSlugCategoriesSuccess(value.getData());
                    }

                    @Override
                    public void onFail(Throwable e) {
                        listener.onGetSlugCategoriesFail(e.getMessage());
                    }
                }));
    }

    private void getLiveList(final LiveContract.OnRequestListener listener) {
        ApiManager.getInstance().add(this,mAPIService.getLiveListResult())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MObserver(new MObserver.ApiCallback<LiveListResult>() {
                    @Override
                    public void onSuccess(LiveListResult value) {
                        listener.onGetSlugCategoriesSuccess(value.getData());
                    }

                    @Override
                    public void onFail(Throwable e) {
                        listener.onGetSlugCategoriesFail(e.getMessage());
                    }
                }));
    }
}
