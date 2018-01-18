package com.live.gblive.model;

import com.leeorz.lib.api.ApiManager;
import com.live.gblive.contract.RoomContract;
import com.live.gblive.model.bean.Room;
import com.live.gblive.model.bean.RoomLine;
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
public class RoomModel extends BaseProtocol implements RoomContract.Model {
    @Override
    public void loadEnterRoom(String uid, final RoomContract.OnRequestListener listener) {
        ApiManager.getInstance().add(this, mAPIService.enterRoom(uid))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MObserver(new MObserver.ApiCallback<Room>() {
                    @Override
                    public void onSuccess(Room room) {
                        if (room != null) {
                            listener.onGetRoomSuccess(room);
                            String url = null;
//                            RoomLine roomLine = room.getRoom_lines().get(0);
                            RoomLine roomLine = room.getLive().getWs();

                            RoomLine.FlvBean flv = roomLine.getFlv();
                            if (flv != null) {
                                url = flv.getValue(false).getSrc();
                            } else {
                                url = roomLine.getHls().getValue(false).getSrc();
                            }
                            listener.playUrl(url);
                        }
                    }

                    @Override
                    public void onFail(Throwable e) {
                        listener.onGetRoomFail(e.getMessage());
                    }
                }));
    }
}
