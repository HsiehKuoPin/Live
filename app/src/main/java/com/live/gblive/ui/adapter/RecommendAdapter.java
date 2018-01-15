package com.live.gblive.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.live.gblive.R;
import com.live.gblive.model.bean.Recommend;
import com.live.gblive.model.bean.RecommendSection;
import com.live.gblive.utils.XPicasso;

import java.util.List;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/13 15:07
 * description:
 */
public class RecommendAdapter extends BaseSectionQuickAdapter<RecommendSection, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public RecommendAdapter(int layoutResId, int sectionHeadResId, List<RecommendSection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder holder, RecommendSection item) {
        holder.setText(R.id.tvCategroy, item.header)
                .addOnClickListener(R.id.tvMore);
        XPicasso.load(mContext,item.icon).into((ImageView) holder.getView(R.id.iv_header));
    }

    @Override
    protected void convert(BaseViewHolder holder, RecommendSection item) {
        Recommend.RoomBean.ListBean data = item.t;
        XPicasso.load(mContext,data.getThumb()).into((ImageView) holder.getView(R.id.iv));
        holder.setText(R.id.tvTitle,data.getTitle());
        holder.setText(R.id.tvName,data.getNick());
        holder.setText(R.id.tvViewer,data.getViews());
    }

}
