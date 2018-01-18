package com.live.gblive.ui.activity;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.live.gblive.R;
import com.live.gblive.base.Constants;
import com.live.gblive.base.MvpActivity;
import com.live.gblive.ui.fragment.FullRoomFragment;
import com.live.gblive.ui.fragment.RoomFragment;
import com.live.gblive.utils.LogUtil;

import butterknife.BindView;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/15 14:33
 * description:
 */
public class ContentActivity extends MvpActivity {

    @BindView(R.id.fragmentContent)
    FrameLayout mFragmentContent;

    @Override
    public int getRootViewId() {
        return R.layout.activity_content;
    }

    @Override
    public void initView() {
        swichFragment(getIntent());
    }
    public void swichFragment(Intent intent){

        int fragmentKey = intent.getIntExtra(Constants.KEY_FRAGMENT,0);
        switch (fragmentKey){
            case Constants.ROOM_FRAGMENT:
                replaceFragment(RoomFragment.newInstance(intent.getStringExtra(Constants.KEY_UID)));
                break;
            case Constants.LIVE_FRAGMENT: {
                String title = intent.getStringExtra(Constants.KEY_TITLE);
                String slug = intent.getStringExtra(Constants.KEY_SLUG);
                boolean isTabLive = intent.getBooleanExtra(Constants.KEY_IS_TAB_LIVE, false);
//                replaceFragment(LiveFragment.newInstance(title, slug, isTabLive));
                break;
            }case Constants.WEB_FRAGMENT: {
                String title = intent.getStringExtra(Constants.KEY_TITLE);
                String url = intent.getStringExtra(Constants.KEY_URL);
//                replaceFragment(WebFragment.newInstance(url, title));
                break;
            }case Constants.LOGIN_FRAGMENT:
//                replaceFragment(LoginFragment.newInstance());
                break;
            case Constants.ABOUT_FRAGMENT:
//                replaceFragment(AboutFragment.newInstance());
                break;
            case Constants.FULL_ROOM_FRAGMENT:
                String uid = intent.getStringExtra(Constants.KEY_UID);
                String cover = intent.getStringExtra(Constants.KEY_COVER);
                replaceFragment(FullRoomFragment.newInstance(uid,cover));
                break;
            case Constants.SEARCH_FRAGMENT:
//                replaceFragment(SearchFragment.newInstance());
                break;
            default:
                LogUtil.d("Not found fragment:" + Integer.toHexString(fragmentKey));
                break;
        }
    }

    public void replaceFragment(Fragment fragmnet){
        replaceFragment(R.id.fragmentContent,fragmnet);
    }

    public void replaceFragment(@IdRes int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }
}
