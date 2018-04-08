package com.benjamin.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;

public class ApiManager {
    private static ApiManager apiManager;
    private List<ApiObj> apiList = new ArrayList();

    public ApiManager() {
    }

    public static synchronized ApiManager getInstance() {
        if(apiManager == null) {
            apiManager = new ApiManager();
        }

        return apiManager;
    }

    public <T> Observable<T> add(Object tag, Observable<T> observable) {
        this.apiList.add(new ApiManager.ApiObj(tag, observable));
        return observable;
    }

    public void cancel(Object tag) {
        Iterator iterator = this.apiList.iterator();

        while(iterator.hasNext()) {
            ApiManager.ApiObj api = (ApiManager.ApiObj)iterator.next();
            if(api.getTag() == tag) {
                iterator.remove();
            }
        }

    }

    private class ApiObj {
        private Object tag;
        private Observable observable;

        public ApiObj(Object tag, Observable observable) {
            this.tag = tag;
            this.observable = observable;
        }

        public Object getTag() {
            return this.tag;
        }

        public Observable getObservable() {
            return this.observable;
        }
    }
}