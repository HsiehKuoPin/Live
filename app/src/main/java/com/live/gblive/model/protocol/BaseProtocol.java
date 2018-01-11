package com.live.gblive.model.protocol;

import com.leeorz.lib.api.API;
import com.leeorz.lib.base.BaseModel;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/11 15:05
 * description:
 */
public class BaseProtocol extends BaseModel {
    public final APIService mAPIService;

    public BaseProtocol() {
        mAPIService = API.getInstance(APIService.class);
    }
}
