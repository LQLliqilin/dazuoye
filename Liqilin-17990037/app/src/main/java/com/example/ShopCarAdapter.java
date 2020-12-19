package com.example;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.liqilin.R;

import java.math.BigDecimal;
import java.util.List;
public class ShopCarAdapter extends BaseQuickAdapter<ShopCarEntity, BaseViewHolder> {
    public ShopCarAdapter(int layoutResId, @Nullable List<ShopCarEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCarEntity item) {
        ImageView iv=helper.getView(R.id.iv);
        TextView name=helper.getView(R.id.tv_name);
        TextView miaoshu=helper.getView(R.id.tv_miaoshu);
        TextView price=helper.getView(R.id.tv_price);
        TextView tvnumber=helper.getView(R.id.tv_number);
        TextView tvAll=helper.getView(R.id.tv_all);
        iv.setImageResource(item.img);
        name.setText(item.name);
        miaoshu.setText(item.jieshao);
        price.setText("￥"+item.price);
        tvnumber.setText("数量："+item.number);
        BigDecimal b   =   new BigDecimal(item.number*Double.valueOf(item.price));
        double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        tvAll.setText("总价"+f1);
    }
}
