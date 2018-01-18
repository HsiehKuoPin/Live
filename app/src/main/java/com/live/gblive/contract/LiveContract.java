package com.live.gblive.contract;

import com.leeorz.lib.base.BaseRequestListener;
import com.leeorz.lib.base.BaseView;
import com.live.gblive.model.bean.LiveInfo;

import java.util.List;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/18 16:50
 * description:
 */
public interface LiveContract {
    interface View extends BaseView {
        void getSlugCategoriesSuccess(List<LiveInfo> liveInfoList);
        void getSlugCategoriesFail(String message);
    }



    interface Model {
        void loadSlugCategories(String slug,OnRequestListener listener);
    }

    interface OnRequestListener extends BaseRequestListener {
        void onGetSlugCategoriesSuccess(List<LiveInfo> liveInfoList);
        void onGetSlugCategoriesFail(String message);
    }

    interface Presenter {
        void loadSlugCategories(String slug);
    }
}
