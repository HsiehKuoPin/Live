package com.live.gblive.model;

import com.leeorz.lib.api.ApiManager;
import com.live.gblive.contract.FullRoomContract;
import com.live.gblive.model.bean.Room;
import com.live.gblive.model.protocol.BaseProtocol;
import com.live.gblive.model.protocol.MObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/15 16:24
 * description:
 */
public class FullRoomModel extends BaseProtocol implements FullRoomContract.Model {
    @Override
    public void loadEnterRoom(String uid, final FullRoomContract.OnRequestListener listener) {
        ApiManager.getInstance().add(this,mAPIService.enterRoom(uid))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MObserver(new MObserver.ApiCallback<Room>() {
                    @Override
                    public void onSuccess(Room value) {
                        listener.onGetRoomSuccess(value);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        listener.onGetRoomFail(e.getMessage());
                    }
                }));
    }
}
