package com.live.gblive.model.protocol;

import com.live.gblive.model.bean.AppStart;
import com.live.gblive.model.bean.LiveCategory;
import com.live.gblive.model.bean.LiveListResult;
import com.live.gblive.model.bean.Recommend;
import com.live.gblive.model.bean.Room;
import com.live.gblive.model.bean.SearchRequestBody;
import com.live.gblive.model.bean.SearchResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/11 11:41
 * description:
 */
public interface APIService {
    /**
     * 获取App启动页信息
     * @return
     */
    @GET("json/page/app-data/info.json?v=3.0.1&os=1&ver=4")
    Observable<AppStart> getAppStartInfo();

    /**
     * 获取分类列表
     * @return
     *
     * categories/list.json
     */
    @GET("json/app/index/category/info-android.json?v=3.0.1&os=1&ver=4")
    Observable<List<LiveCategory>> getAllCategories();

    /**
     * 获取推荐列表
     * @return
     */
    @GET("json/app/index/recommend/list-android.json?v=3.0.1&os=1&ver=4")
    Observable<Recommend> getRecommend();

    /**
     * 获取直播列表
     * @return
     */
    @GET("json/play/list.json?v=3.0.1&os=1&ver=4")
    Observable<LiveListResult> getLiveListResult();


    @GET("json/categories/{slug}/list.json?v=3.0.1&os=1&ver=4")
    Observable<LiveListResult> getSlugCategories(@Path("slug") String slug);

    /**
     * 进入房间
     * @param uid
     * @return
     */
    @GET("json/rooms/{uid}/info.json?v=3.0.1&os=1&ver=4")
    Observable<Room> enterRoom(@Path("uid")String uid);

    /**
     * 搜索
     * @param searchRequestBody
     * @return
     */
    @POST("site/search")
    Observable<SearchResult> search(@Body SearchRequestBody searchRequestBody);
}
