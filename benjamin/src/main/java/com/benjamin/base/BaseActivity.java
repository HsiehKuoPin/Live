package com.benjamin.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.benjamin.app.AppConfig;
import com.benjamin.app.AppManager;

public class BaseActivity extends AppCompatActivity implements BaseView {
    public String TAG = "";

    public BaseActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        this.TAG = this.getClass().getName();
        AppManager.getAppManager().addActivity(this);
        AppConfig.init(this);
        super.onCreate(savedInstanceState);
    }

    public void setContentView(View view) {
        super.setContentView(view);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    protected void onDestroy() {
        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }

    protected void gotoActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if(bundle != null) {
            intent.putExtras(bundle);
        }

        this.startActivity(intent);
    }

    protected void gotoActivity(Class clazz) {
        this.gotoActivity((Class)clazz, (Bundle)null);
    }

    protected void gotoActivity(String className) {
        this.gotoActivity((String)className, (Bundle)null);
    }

    protected void gotoActivity(String className, Bundle bundle) {
        try {
            Intent intent = new Intent(this, Class.forName(className));
            if(bundle != null) {
                intent.putExtras(bundle);
            }

            this.startActivity(intent);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    protected void gotoActivityForResult(Class clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, clazz);
        if(bundle != null) {
            intent.putExtras(bundle);
        }

        this.startActivityForResult(intent, requestCode);
    }

    protected void gotoActivityForResult(Class clazz, int requestCode) {
        this.gotoActivityForResult(clazz, (Bundle)null, requestCode);
    }

    protected Activity getActivity() {
        return this;
    }

    public void onUserTokenInvalid() {
    }
}