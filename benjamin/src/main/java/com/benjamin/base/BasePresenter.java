package com.benjamin.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BasePresenter<V extends BaseView, M extends BaseModel> implements BaseRequestListener {
    protected M mModel;
    private Reference<V> mReference;

    public BasePresenter(V v) {
        try {
            Type type = this.getClass().getGenericSuperclass();
            ParameterizedType p = (ParameterizedType) type;
            Class c = (Class) p.getActualTypeArguments()[1];
            this.mModel = (M) Class.forName(c.getName()).newInstance();
            this.mReference = new WeakReference(v);
        } catch (IllegalAccessException var5) {
            var5.printStackTrace();
        } catch (InstantiationException var6) {
            var6.printStackTrace();
        } catch (ClassNotFoundException var7) {
            var7.printStackTrace();
        }

    }

    public V getIView() {
        return this.mReference == null ? null : this.mReference.get();
    }

    public void destroy() {
        this.mReference.clear();
        this.mReference = null;
        this.mModel.cancelRequest();
    }

    public void onTokenInvalid() {
        if (this.getIView() != null) {
            this.getIView().onUserTokenInvalid();
        }
    }
}