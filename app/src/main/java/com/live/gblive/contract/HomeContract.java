package com.live.gblive.contract;

import com.benjamin.base.BaseRequestListener;
import com.benjamin.base.BaseView;
import com.live.gblive.model.bean.LiveCategory;

import java.util.List;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/11 14:39
 * description:
 */
public interface HomeContract {

    interface View extends BaseView {
        void getLiveCategorySuccess(List<LiveCategory> list);
        void getLiveCategoryFail(String message);
    }


    interface Presenter {
        void loadAllCategories();
    }

    interface Model {
        void loadAllCategories(OnRequestListener listener);
    }

    interface OnRequestListener extends BaseRequestListener {
        void onGetLiveCategorySuccess(List<LiveCategory> list);
        void onGetLiveCategoryFail(String message);
    }
}
