package com.live.gblive.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.live.gblive.R;
import com.live.gblive.base.MvpFragment;
import com.live.gblive.ui.service.CameraLiveWallpaper;

import butterknife.BindView;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/8 17:17
 * description:
 */
public class FollowFragment extends MvpFragment {

    @BindView(R.id.tv1)
    TextView tv1;

    public static FollowFragment newInstance() {

        Bundle args = new Bundle();

        FollowFragment fragment = new FollowFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getRootViewId() {
        return R.layout.fragment_follow;
    }

    @Override
    public void initView() {
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSelfPermission();
            }
        });
    }

    @Override
    public void initData() {

    }
    private static final int PERMISSIONS_REQUEST_CAMERA = 454;
    static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    /**
     * 检查权限
     */
    void checkSelfPermission() {
        if (ContextCompat.checkSelfPermission(mContext, PERMISSION_CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{PERMISSION_CAMERA},
                    PERMISSIONS_REQUEST_CAMERA);
        } else {
//            setTransparentWallpaper();
            startWallpaper();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    setTransparentWallpaper();
                    startWallpaper();

                } else {
                    Toast.makeText(mContext, getString(R.string._lease_open_permissions), Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    /**
     * 选择壁纸
     */
    void startWallpaper() {
        final Intent pickWallpaper = new Intent(Intent.ACTION_SET_WALLPAPER);
        Intent chooser = Intent.createChooser(pickWallpaper, getString(R.string.choose_wallpaper));
        startActivity(chooser);
    }

    /**
     * 不需要手动启动服务
     */
    void setTransparentWallpaper() {
        mContext.startService(new Intent(mContext, CameraLiveWallpaper.class));
    }
}
