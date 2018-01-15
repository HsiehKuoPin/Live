package com.live.gblive.presenter;

import com.leeorz.lib.base.BasePresenter;
import com.live.gblive.contract.FullRoomContract;
import com.live.gblive.model.FullRoomModel;
import com.live.gblive.model.bean.Room;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/15 16:42
 * description:
 */
public class FullRoomPresenter extends BasePresenter<FullRoomContract.View,FullRoomModel> implements FullRoomContract.Presenter, FullRoomContract.OnRequestListener {
    public FullRoomPresenter(FullRoomContract.View view) {
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
}
