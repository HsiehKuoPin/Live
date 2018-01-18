package com.live.gblive.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leeorz.lib.base.BaseFragment;
import com.leeorz.lib.base.BasePresenter;
import com.live.gblive.model.bean.LiveInfo;
import com.live.gblive.ui.activity.ContentActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/8 14:32
 * description:
 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected Context mContext;
    protected P mPresenter;
    private View rootView;
    Unbinder unbinder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        rootView = inflater.inflate(getRootViewId(),container,false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.destroy();
        }
    }
    public View getRootView(){
        return rootView;
    }
    public abstract int getRootViewId();

    public void initView() {

    }

    public void initData() {

    }
    public void replaceFragment(@IdRes int id, Fragment fragment) {
        getFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    public void replaceChildFragment(@IdRes int id, Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(id, fragment).commit();
    }
    public <T> void  toSetList(List<T> list, List<T> newList, boolean isMore){

        if(list!=null && newList!=null){
            synchronized (BaseFragment.class){
                if(!isMore){
                    list.clear();
                }
                list.addAll(newList);
            }
        }
    }


    //--------------------------------

    protected Intent getIntent(){
        return getActivity().getIntent();
    }


    protected Intent getFragmentIntent(int fragmentKey){
        Intent intent = getContentActivityIntent();
        intent.putExtra(Constants.KEY_FRAGMENT,fragmentKey);
        return intent;
    }

    protected Intent getContentActivityIntent(){
        return new Intent(mContext, ContentActivity.class);
    }

    protected void startActivity(Class<?> cls){
        startActivity(new Intent(mContext,cls));
    }


    protected void finish(){
        getActivity().finish();
    }


    protected void startWeb(String title,String url){
        Intent intent = getFragmentIntent(Constants.WEB_FRAGMENT);
        intent.putExtra(Constants.KEY_TITLE,title);
        intent.putExtra(Constants.KEY_URL,url);
        startActivity(intent);
    }

    protected void startRoom(LiveInfo liveInfo){

        int fragmentKey = Constants.ROOM_FRAGMENT;
        if(Constants.SHOWING.equalsIgnoreCase(liveInfo.getCategory_slug())){
            fragmentKey = Constants.FULL_ROOM_FRAGMENT;
        }
        Intent intent = getFragmentIntent(fragmentKey);
        intent.putExtra(Constants.KEY_UID,liveInfo.getUid());
        intent.putExtra(Constants.KEY_COVER,liveInfo.getThumb());
        startActivity(intent);
    }

    protected void startLogin(){
        Intent intent = getFragmentIntent(Constants.LOGIN_FRAGMENT);
        startActivity(intent);
    }


    protected void startAbout(){
        Intent intent = getFragmentIntent(Constants.ABOUT_FRAGMENT);
        startActivity(intent);
    }

    //--------------------------------
}
