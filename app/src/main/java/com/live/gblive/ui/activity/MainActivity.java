package com.live.gblive.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.leeorz.lib.utils.ToastUtil;
import com.live.gblive.R;
import com.live.gblive.base.MvpActivity;
import com.live.gblive.ui.fragment.FollowFragment;
import com.live.gblive.ui.fragment.HomeFragment;
import com.live.gblive.ui.fragment.LiveFragment;
import com.live.gblive.ui.fragment.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpActivity {

    @BindView(R.id.rbHome)
    RadioButton mRbHome;
    @BindView(R.id.rbLive)
    RadioButton mRbLive;
    @BindView(R.id.rbFollow)
    RadioButton mRbFollow;
    @BindView(R.id.rbMe)
    RadioButton mRbMe;

    private HomeFragment homeFragment;
    private LiveFragment liveFragment;
    private FollowFragment followFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        showFragment(getHomeFragment());
    }

    @OnClick({R.id.rbHome, R.id.rbLive, R.id.rbFollow, R.id.rbMe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rbHome:
                showFragment(getHomeFragment());
                break;
            case R.id.rbLive:
                showFragment(getLiveFragment());
                break;
            case R.id.rbFollow:
                showFragment(getFollowFragment());
                break;
            case R.id.rbMe:
                showFragment(getMineFragment());
                break;
        }
    }

    public void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment).commit();
        } else {
            fragmentTransaction.add(R.id.fragmentContent, fragment).show(fragment).commit();
        }
    }

    public void hideAllFragment(FragmentTransaction fragmentTransaction) {
        fragmentTransaction.hide(getHomeFragment());
        fragmentTransaction.hide(getLiveFragment());
        fragmentTransaction.hide(getFollowFragment());
        fragmentTransaction.hide(getMineFragment());
    }

    public HomeFragment getHomeFragment() {
        if (homeFragment == null) homeFragment = homeFragment.newInstance();
        return homeFragment;
    }

    public LiveFragment getLiveFragment() {
        if (liveFragment == null) liveFragment = liveFragment.newInstance();
        return liveFragment;
    }

    public FollowFragment getFollowFragment() {
        if (followFragment == null) followFragment = followFragment.newInstance();
        return followFragment;
    }

    public MineFragment getMineFragment() {
        if (mineFragment == null) mineFragment = mineFragment.newInstance();
        return mineFragment;
    }

    private long clickBackTime;
    private long lastClickBackTime;

    @Override
    public void onBackPressed() {
        clickBackTime = System.currentTimeMillis();
        if (clickBackTime - lastClickBackTime > 2000) {
            ToastUtil.showShort(mContext, "再点一下退出");
        } else {
            super.onBackPressed();
        }
        lastClickBackTime = clickBackTime;
    }
}
