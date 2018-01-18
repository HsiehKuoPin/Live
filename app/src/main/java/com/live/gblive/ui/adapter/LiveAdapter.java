package com.live.gblive.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.live.gblive.R;
import com.live.gblive.model.bean.LiveInfo;
import com.live.gblive.utils.XPicasso;

import java.util.List;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/18 16:38
 * description:
 */
public class LiveAdapter extends BaseQuickAdapter<LiveInfo, BaseViewHolder> {
    public LiveAdapter(int layoutResId, @Nullable List<LiveInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, LiveInfo data) {
        XPicasso.load(mContext, data.getThumb()).into((ImageView) holder.getView(R.id.iv));
        holder.setText(R.id.tvTitle, data.getTitle());
        holder.setText(R.id.tvName, data.getNick());
        holder.setText(R.id.tvViewer, data.getViews());
    }

}
