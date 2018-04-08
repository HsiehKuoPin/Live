package com.live.gblive.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.benjamin.app.Global;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.live.gblive.R;
import com.live.gblive.base.Constants;
import com.live.gblive.base.MvpFragment;
import com.live.gblive.contract.RecommendContract;
import com.live.gblive.model.bean.Banner;
import com.live.gblive.model.bean.Recommend;
import com.live.gblive.model.bean.RecommendSection;
import com.live.gblive.presenter.RecommendPresenter;
import com.live.gblive.ui.activity.ContentActivity;
import com.live.gblive.ui.adapter.RecommendAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/8 17:17
 * description:
 */
public class RecommendFragment extends MvpFragment<RecommendPresenter> implements RecommendContract.View {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private List<RecommendSection> data;
    private List<Banner> listBanner;

    private ConvenientBanner<Banner> convenientBanner;
    private RecommendAdapter mRecommendAdapter;
    private View mHeaderView;

    public static RecommendFragment newInstance() {

        Bundle args = new Bundle();

        RecommendFragment fragment = new RecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initView() {
        data = new ArrayList<>();
        listBanner = new ArrayList<>();
        mRecommendAdapter = new RecommendAdapter(R.layout.list_live_item, R.layout.list_remmend_item, data);
        mRecommendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Recommend.RoomBean.ListBean listBean = data.get(position).t;
                if (!data.get(position).isHeader){
                    startRoom(getContext(),listBean);
                }
            }
        });
        mRecommendAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RecommendSection recommendSection = data.get(position);
                Global.show(recommendSection.header+position);
            }
        });
        mRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecommendAdapter.addHeaderView(getHeaderView());
        mRecommendAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRecommendAdapter.isFirstOnly(false);//设置重复执行
        mRecyclerview.setAdapter(mRecommendAdapter);
        mPresenter = new RecommendPresenter(this);
    }

    @Override
    public void initData() {
        mPresenter.getRecommend();
        mPresenter.getAppStartInfo();
    }

    @Override
    public void getRecommendSuccess(List<RecommendSection> recommendSectionList) {
        if(recommendSectionList!=null){
            toSetList(data,recommendSectionList,false);
            mRecommendAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getRecommendFail(String message) {
        Global.showFail(message);
    }

    @Override
    public void getAppStartInfoSuccess(List<Banner> bannerList) {
        if (bannerList !=null){
            toSetList(listBanner,bannerList,false);
            convenientBanner.notifyDataSetChanged();
        }
    }

    @Override
    public void getAppStartInfoFail(String message) {
        Global.showFail(message);
    }

    public void startRoom(Context context,Recommend.RoomBean.ListBean listBean){
        Intent intent = new Intent(context, ContentActivity.class);
        int fragmentKey = Constants.ROOM_FRAGMENT;
        if(Constants.SHOWING.equalsIgnoreCase(listBean.getCategory_slug())){
            fragmentKey = Constants.FULL_ROOM_FRAGMENT;
        }
        intent.putExtra(Constants.KEY_FRAGMENT,fragmentKey);
        intent.putExtra(Constants.KEY_UID,String.valueOf(listBean.getUid()));
        intent.putExtra(Constants.KEY_COVER,listBean.getThumb());
        context.startActivity(intent);
    }

    public View getHeaderView() {
        View view = getLayoutInflater().inflate(R.layout.banner, (ViewGroup) mRecyclerview.getParent(), false);
        convenientBanner = view.findViewById(R.id.convenientBanner);
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder<Banner> createHolder() {
                return new ImageHolder();
            }
        }, listBanner)
                .setPageIndicator(new int[]{R.drawable.ic_dot_normal, R.drawable.ic_dot_pressed})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Banner banner = listBanner.get(position);
                if(banner !=null){
                    if(banner.isRoom()){//如果是房间类型就点击进入房间
                        startRoom(banner.getLink_object());
                    }else{//广告类型
                        startWeb(banner.getTitle(),banner.getLink());
                    }
                }
            }
        });
//        convenientBanner.setManualPageable(false);//设置不能手动影响
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(convenientBanner!=null && !convenientBanner.isTurning()) {
            convenientBanner.startTurning(4000);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(convenientBanner!=null){
            convenientBanner.stopTurning();
        }

    }

    private class ImageHolder implements Holder<Banner> {

        private ImageView iv;
        @Override
        public View createView(Context context) {
            iv = new ImageView(context);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return iv;
        }

        @Override
        public void UpdateUI(Context context, int position, Banner data) {
            if (data.getThumb().isEmpty()) Picasso.with(context)
                    .load(R.mipmap.live_default)
                    .into(iv);
            else Picasso.with(context)
                    .load(data.getThumb())
                    .into(iv);
        }
    }
}
