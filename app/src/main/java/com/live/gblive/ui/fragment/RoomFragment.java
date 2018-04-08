package com.live.gblive.ui.fragment;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.benjamin.app.Global;
import com.live.gblive.R;
import com.live.gblive.base.MvpFragment;
import com.live.gblive.contract.RoomContract;
import com.live.gblive.model.bean.Room;
import com.live.gblive.presenter.RoomPresenter;
import com.live.gblive.ui.adapter.ViewPagerFragmentAdapter;
import com.live.gblive.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/17 16:17
 * description:
 */
public class RoomFragment extends MvpFragment<RoomPresenter> implements RoomContract.View {

    @BindView(R.id.frameVideo)
    FrameLayout frameVideo;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.ivShare)
    ImageView ivShare;
    @BindView(R.id.tvRoomTitle)
    TextView tvRoomTitle;
    @BindView(R.id.ivFullScreen)
    ImageView ivFullScreen;
    @BindView(R.id.rlRoomInfo)
    RelativeLayout rlRoomInfo;
    @BindView(R.id.llRoomChat)
    LinearLayout llRoomChat;

    @BindView(R.id.videoContent)
    RelativeLayout videoContent;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.tvFollow)
    TextView tvFollow;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;

    private List<CharSequence> listTitle;

    private List<Fragment> listData;

    private Room room;

    private String uid;

    private AnchorInfoFragment anchorInfoFragment;
    private VideoFragment videoFragment;

    public static RoomFragment newInstance(String uid) {
        Bundle args = new Bundle();
        RoomFragment fragment = new RoomFragment();
        fragment.uid = uid;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_room;
    }

    @Override
    public void initView() {
        updateVideoLayoutParams();
        mPresenter = new RoomPresenter(this);
        listTitle = new ArrayList<>();

        listTitle.add(getText(R.string.room_chat));
        listTitle.add(getText(R.string.room_ranking));
        listTitle.add(getText(R.string.room_anchor));
        listData = new ArrayList<>();
//        listData.add(ChatFragment.newInstance());
        listData.add(RankFragment.newInstance());
        listData.add(RankFragment.newInstance());
        anchorInfoFragment = AnchorInfoFragment.newInstance();
        listData.add(anchorInfoFragment);
        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getFragmentManager(), listData, listTitle);

        viewPager.setAdapter(viewPagerFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(2);
    }

    @Override
    public void initData() {
        mPresenter.enterRoom(uid);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(isLandscape()){
            llRoomChat.setVisibility(View.GONE);
            ivFullScreen.setVisibility(View.GONE);
        }else{
            llRoomChat.setVisibility(View.VISIBLE);
            ivFullScreen.setVisibility(View.VISIBLE);
        }
        updateVideoLayoutParams();
    }

    public void updateVideoLayoutParams(){
        ViewGroup.LayoutParams lp = videoContent.getLayoutParams();
        if(isLandscape()){
            lp.height = DensityUtil.getDisplayMetrics(getActivity()).heightPixels-DensityUtil.getStatusBarHeight(mContext);
        }else{
            lp.height = (int)(DensityUtil.getDisplayMetrics(getActivity()).widthPixels / 16.0f * 9.0f);
        }

        videoContent.setLayoutParams(lp);
    }
    public boolean isLandscape(){
        return getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
    }

    @Override
    public void getRoomSuccess(Room room) {
        this.room = room;
        anchorInfoFragment.onUpdateAnchor(room);
        viewPagerFragmentAdapter.notifyDataSetChanged();
    }
    @Override
    public void getRoomFail(String message) {
        Global.showFail(message);
    }

    @Override
    public void playUrl(String url) {
        if (videoFragment == null) {
            videoFragment = VideoFragment.newInstance(url, true);
        }
        replaceChildFragment(R.id.frameVideo, videoFragment);
    }

    @OnClick({R.id.frameVideo, R.id.ivBack, R.id.ivShare, R.id.ivFullScreen, R.id.videoContent, R.id.tvFollow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.videoContent:
            case R.id.frameVideo:
//                clickFrameVideo();
                break;
            case R.id.ivBack:
                clickBack();
                break;
            case R.id.ivShare:
//                clickShare();
                break;
            case R.id.ivFullScreen:
                clickFullScreen();
                break;
            case R.id.tvFollow:
//                clickFollow();
                break;
        }
    }
    public void clickBack(){
        if(isLandscape()){
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else{
            getActivity().finish();
        }
    }
    public void clickFullScreen(){
        if(isLandscape()){
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else{
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }
}
