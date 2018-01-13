package com.live.gblive.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/13 17:02
 * description:
 */
public class DataServer {
    private List<Recommend.RoomBean> room;

    public List<Recommend.RoomBean> getRoom() {
        return room;
    }

    public void setRoom(List<Recommend.RoomBean> room) {
        this.room = room;
    }
    public List<RecommendSection> getSampleData() {
        List<RecommendSection> list = new ArrayList<>();
        for (Recommend.RoomBean roomBean : room) {
            list.add(new RecommendSection(true, roomBean.getName(), roomBean.getIcon()));
            for (Recommend.RoomBean.ListBean listBean : roomBean.getList()) {
                list.add(new RecommendSection(listBean));
            }
        }
        return list;
    }
}
