package com.benjamin.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment implements BaseView {
    protected LayoutInflater mInflater;
    protected View contentView;
    protected String TAG = "";

    public BaseFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.TAG = this.getClass().getSimpleName();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mInflater = inflater;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    protected void gotoActivity(Class<? extends Activity> activity, Bundle bundle) {
        Intent intent = new Intent(this.getActivity(), activity);
        if(bundle != null) {
            intent.putExtras(bundle);
        }

        this.startActivity(intent);
    }

    protected void gotoActivity(Class<? extends Activity> activity) {
        Intent intent = new Intent(this.getActivity(), activity);
        this.startActivity(intent);
    }

    protected void gotoActivityForResult(Class<? extends Activity> activity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this.getActivity(), activity);
        if(bundle != null) {
            intent.putExtras(bundle);
        }

        this.startActivityForResult(intent, requestCode);
    }

    public void onUserTokenInvalid() {
    }
}