package com.live.gblive.model.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/13 15:50
 * description:
 */
public class RecommendSection extends SectionEntity<Recommend.RoomBean.ListBean> {
    public String icon;

    public RecommendSection(boolean isHeader, String header, String icon) {
        super(isHeader, header);
        this.icon = icon;
    }

    public RecommendSection(Recommend.RoomBean.ListBean listBean) {
        super(listBean);
    }
}
