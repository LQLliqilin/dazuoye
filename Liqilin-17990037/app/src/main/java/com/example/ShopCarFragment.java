package com.example;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.liqilin.R;
import com.example.liqilin.databinding.FragmentPosBinding;

import java.util.ArrayList;
import java.util.List;

public class ShopCarFragment extends Fragment {
    private FragmentPosBinding binding;
    private ShopCarAdapter shopCarAdapter;
    private SQLiteDatabase writableDatabase;
    private List<ShopCarEntity> entities;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentPosBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        writableDatabase = new MyDatabaseHelper(requireContext(), "MyData.db", null, 1).getWritableDatabase();
        entities=new ArrayList<>();
        binding.rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        shopCarAdapter=new ShopCarAdapter(R.layout.car_item,entities);
        binding.rv.setAdapter(shopCarAdapter);
        query();
    }
    private void query(){
        Cursor cursor;
        entities.clear();
        cursor = writableDatabase.rawQuery("select * from ShopCar where username=?", new String[]{Contants.username});
        if (cursor.moveToFirst()){
            do {
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String price=cursor.getString(cursor.getColumnIndex("price"));
                int img=cursor.getInt(cursor.getColumnIndex("img"));
                String miaoshu=cursor.getString(cursor.getColumnIndex("jieshao"));
                int number=cursor.getInt(cursor.getColumnIndex("number"));
                entities.add(new ShopCarEntity(name,img,miaoshu,price,number));
            }while (cursor.moveToNext());
        }
        cursor.close();
        shopCarAdapter.setNewData(entities);
        shopCarAdapter.notifyDataSetChanged();
    }
}