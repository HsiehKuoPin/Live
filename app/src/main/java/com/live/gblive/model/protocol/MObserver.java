package com.live.gblive.model.protocol;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/11 16:57
 * description:
 */
public class MObserver implements Observer<Object> {
    private ApiCallback mCallback;

    public MObserver(ApiCallback callback) {
        mCallback = callback;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }


    @Override
    public void onNext(Object value) {
        mCallback.onSuccess(value);
    }

    @Override
    public void onError(Throwable e) {
        mCallback.onFail(e);
    }

    @Override
    public void onComplete() {

    }

    public interface ApiCallback<T> {
        void onSuccess(T value);

        void onFail(Throwable e);
    }
}
