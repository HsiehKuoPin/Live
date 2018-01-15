package com.live.gblive.contract;

import com.leeorz.lib.base.BaseRequestListener;
import com.leeorz.lib.base.BaseView;
import com.live.gblive.model.bean.Room;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/15 16:19
 * description:
 */
public interface FullRoomContract {
    interface View extends BaseView {
        void getRoomSuccess(Room room);

        void getRoomFail(String message);
    }


    interface Presenter {
        void enterRoom(String uid);
    }

    interface Model {
        void loadEnterRoom(String uid, OnRequestListener listener);
    }

    interface OnRequestListener extends BaseRequestListener {
        void onGetRoomSuccess(Room room);

        void onGetRoomFail(String message);
    }
}
