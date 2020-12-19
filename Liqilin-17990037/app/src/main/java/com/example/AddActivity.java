package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;


import com.example.liqilin.R;
import com.example.liqilin.databinding.ActivityAddBinding;

import java.io.Serializable;
import java.math.BigDecimal;

public class AddActivity extends AppCompatActivity {
    private ActivityAddBinding binding;
    private int num=1;
    private SQLiteDatabase writableDatabase;
    private  ResultEntity data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimaryDark));
        getSupportActionBar().setTitle("详情");
        writableDatabase = new MyDatabaseHelper(this, "MyData.db", null, 1).getWritableDatabase();
        data = (ResultEntity) getIntent().getSerializableExtra("data");
        binding.iv.setImageResource(data.img);
        binding.tvMiaoshu.setText(data.jieshao);
        binding.tvPrice.setText("单价￥"+data.price);
        binding.tvName.setText(data.name);
        binding.tvAll.setText("总价￥"+data.price);
        binding.tvAdd.setOnClickListener(v -> {
            num++;
            binding.tvNumber.setText("数量："+num);
            BigDecimal   b   =   new BigDecimal(num*Double.valueOf(data.price));
            double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
            binding.tvAll.setText("总价￥"+f1);
        });
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values=new ContentValues();
                values.put("username",Contants.username);
                values.put("name",data.name);
                values.put("img",data.img);
                values.put("price",data.price);
                values.put("username",Contants.username);
                values.put("number",num);
                values.put("jieshao",data.jieshao);
                long login = writableDatabase.insert("ShopCar", null, values);
                if (login!=-1){
                    Toast.makeText(AddActivity.this,"添加成功！",Toast.LENGTH_LONG).show();
                }else { Toast.makeText(AddActivity.this,"添加失败！",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}