package com.benjamin.base;

import com.benjamin.api.ApiManager;

public class BaseModel {
    public BaseModel() {
    }

    void cancelRequest() {
        ApiManager.getInstance().cancel(this);
    }
}