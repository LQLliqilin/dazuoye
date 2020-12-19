package com.example;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.liqilin.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SxAdapter extends BaseQuickAdapter<ResultEntity, BaseViewHolder> {
    private Context context;
    private List<Integer> integers;
    public SxAdapter(int layoutResId, @Nullable List<ResultEntity> data,Context context) {
        super(layoutResId, data);
        this.context=context;
        integers=new ArrayList<>();
        integers.add(DensityUtil.dip2px(context,150));
        integers.add(DensityUtil.dip2px(context,180));
        integers.add(DensityUtil.dip2px(context,140));
        integers.add(DensityUtil.dip2px(context,120));
        integers.add(DensityUtil.dip2px(context,170));
    }

    @Override
    protected void convert(BaseViewHolder helper, ResultEntity item) {
        ImageView iv=helper.getView(R.id.iv);
        TextView name=helper.getView(R.id.tv_name);
        TextView miaoshu=helper.getView(R.id.tv_miaoshu);
        TextView price=helper.getView(R.id.tv_price);
        iv.setImageResource(item.img);
                        name.setText(item.name);
                miaoshu.setText(item.jieshao);
                price.setText("￥"+item.price);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(integers.get(0),
                integers.get(new Random().nextInt(4)));
        iv.setLayoutParams(params);
        name.setText(item.name);
        miaoshu.setText(item.jieshao);
        price.setText("￥"+item.price);
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemCilckListener!=null){
                    onItemCilckListener.onClick(item);
                }
            }
        });
    }
    public interface OnItemCilckListener{
        void onClick(ResultEntity entity);
    }
    private OnItemCilckListener onItemCilckListener;
    public void setOnItemCilckListener(OnItemCilckListener onItemCilckListener){
        this.onItemCilckListener=onItemCilckListener;
    }
}
