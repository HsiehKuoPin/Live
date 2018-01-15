package com.live.gblive.contract;

import com.leeorz.lib.base.BaseRequestListener;
import com.leeorz.lib.base.BaseView;
import com.live.gblive.model.bean.Banner;
import com.live.gblive.model.bean.RecommendSection;

import java.util.List;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/11 14:39
 * description:
 */
public interface RecommendContract {

    interface View extends BaseView {
        void getRecommendSuccess(List<RecommendSection> recommendSectionList);
        void getRecommendFail(String message);
        void getAppStartInfoSuccess(List<Banner> bannerList);
        void getAppStartInfoFail(String message);
    }


    interface Presenter {
        void getRecommend();
        void getAppStartInfo();
    }

    interface Model {
        void loadRecommend(OnRequestListener listener);
        void loadAppStartInfo(OnRequestListener listener);
    }

    interface OnRequestListener extends BaseRequestListener {
        void onRecommendSuccess(List<RecommendSection> recommendSectionList);
        void onRecommendFail(String message);

        void onAppStartInfoSuccess(List<Banner> bannerList);
        void onAppStartInfoFail(String message);
    }
}
