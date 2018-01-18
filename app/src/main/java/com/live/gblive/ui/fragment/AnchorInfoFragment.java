package com.live.gblive.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.live.gblive.R;
import com.live.gblive.base.MvpFragment;
import com.live.gblive.model.bean.Room;
import com.live.gblive.utils.DecimalFormatUtil;
import com.live.gblive.utils.XPicasso;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/17 16:19
 * description:
 */
public class AnchorInfoFragment extends MvpFragment{
    @BindView(R.id.civAvatar)
    CircleImageView civAvatar;
    @BindView(R.id.tvAnchorName)
    TextView tvAnchorName;
    @BindView(R.id.tvAccount)
    TextView tvAccount;
    @BindView(R.id.tvFans)
    TextView tvFans;
    @BindView(R.id.tvWeight)
    TextView tvWeight;
    @BindView(R.id.tvStartLiveTime)
    TextView tvStartLiveTime;
    @BindView(R.id.tvAge)
    TextView tvAge;
    @BindView(R.id.tvEmotionalState)
    TextView tvEmotionalState;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @BindView(R.id.tvOccupation)
    TextView tvOccupation;

    private Room room;

    public static AnchorInfoFragment newInstance() {
        Bundle args = new Bundle();

        AnchorInfoFragment fragment = new AnchorInfoFragment();
//        fragment.room = room;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_anchor;
    }

    public void onUpdateAnchor(Room room){
        this.room = room;
        initData();
    }

    @Override
    public void initData() {
        if (room != null&& getRootView()!=null) {
            XPicasso.load(getActivity(),room.getAvatar()).into(civAvatar);
            tvAnchorName.setText(room.getNick());
            tvAccount.setText(String.valueOf(room.getNo()));
            tvFans.setText(String.valueOf(room.getFollow()));
            tvWeight.setText(DecimalFormatUtil.formatW(room.getWeight()/100));
            tvStartLiveTime.setText(room.getAnnouncement());
        }
    }
}
