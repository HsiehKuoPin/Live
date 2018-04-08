package com.benjamin.api;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ApiObserver implements Observer<ApiResult> {
    private ApiCallback callback;

    public ApiObserver(ApiCallback callback) {
        this.callback = callback;
    }

    public void onSubscribe(Disposable d) {
    }

    public void onNext(ApiResult value) {
        if(this.callback != null) {
            this.callback.onSuccess(value);
        }

    }

    public void onError(Throwable e) {
        e.printStackTrace();
        if(this.callback != null) {
            this.callback.onFail(e);
        }

    }

    public void onComplete() {
    }
}