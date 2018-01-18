package com.live.gblive.presenter;

import com.leeorz.lib.base.BasePresenter;
import com.live.gblive.contract.RoomContract;
import com.live.gblive.model.RoomModel;
import com.live.gblive.model.bean.Room;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/15 16:42
 * description:
 */
public class RoomPresenter extends BasePresenter<RoomContract.View,RoomModel> implements RoomContract.Presenter, RoomContract.OnRequestListener {
    public RoomPresenter(RoomContract.View view) {
        super(view);
    }

    @Override
    public void enterRoom(String uid) {
        if (getIView() ==null)return;
        mModel.loadEnterRoom(uid, this);
    }

    @Override
    public void onGetRoomSuccess(Room room) {
        if (getIView() ==null)return;
        getIView().getRoomSuccess(room);
    }

    @Override
    public void onGetRoomFail(String message) {
        if (getIView() ==null)return;
        getIView().getRoomFail(message);
    }

    @Override
    public void playUrl(String url) {
        if (getIView() ==null)return;
        getIView().playUrl(url);
    }
}
