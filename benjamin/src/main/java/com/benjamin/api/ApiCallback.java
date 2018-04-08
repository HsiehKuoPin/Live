package com.benjamin.api;

public interface ApiCallback<T> {
    void onSuccess(ApiResult<T> var1);

    void onFail(Throwable var1);
}