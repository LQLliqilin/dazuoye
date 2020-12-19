package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.liqilin.R;
import com.example.liqilin.databinding.ActivityXiangQinBinding;


public class XiangQinActivity extends AppCompatActivity {
    private ActivityXiangQinBinding binding;
    private  ResultEntity data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityXiangQinBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimaryDark));
        getSupportActionBar().setTitle("详情");
        data = (ResultEntity) getIntent().getSerializableExtra("data");
        binding.iv.setImageResource(data.img);
        binding.tvMiaoshu.setText(data.jieshao);
        binding.tvPrice.setText("单价：￥"+data.price);
        binding.tvName.setText(data.name);
    }
}