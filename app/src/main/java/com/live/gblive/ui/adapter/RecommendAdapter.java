package com.live.gblive.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.live.gblive.R;
import com.live.gblive.model.bean.Recommend;
import com.live.gblive.model.bean.RecommendSection;
import com.squareup.picasso.Picasso;

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
        super(R.layout.list_live_item, R.layout.list_remmend_item, data);
    }

    @Override
    protected void convertHead(BaseViewHolder holder, RecommendSection item) {
        holder.setText(R.id.tvCategroy, item.header);
        Picasso.with(mContext).load(item.icon).into((ImageView) holder.getView(R.id.iv_header));
        holder.addOnClickListener(R.id.tvMore);
    }

    @Override
    protected void convert(BaseViewHolder holder, RecommendSection item) {
        Recommend.RoomBean.ListBean data = item.t;
        Picasso.with(mContext)
                .load(data.getThumb())
                .placeholder(R.mipmap.live_default)
                .error(R.mipmap.live_default)
                .centerCrop()
                .into((ImageView) holder.getView(R.id.iv));

        holder.setText(R.id.tvTitle,data.getTitle());
        holder.setText(R.id.tvName,data.getNick());
        holder.setText(R.id.tvViewer,data.getViews());
    }


//    public RecommendAdapter(int layoutResId, @Nullable List<Recommend.RoomBean> data) {
//        super(R.layout.list_remmend_item, data);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, Recommend.RoomBean item) {
//        helper.setText(R.id.tvCategroy, item.getName());
//        Picasso.with(mContext).load(item.getIcon()).into((ImageView) helper.getView(R.id.iv));
//        ((RecyclerView)helper.getView(R.id.rc_item)).setLayoutManager(new GridLayoutManager(mContext,2));
//    }
}
