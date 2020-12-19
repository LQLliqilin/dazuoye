package com.example;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.liqilin.R;
import com.example.liqilin.databinding.FragmentIndexBinding;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;


public class IndexFragment extends Fragment {
    private SQLiteDatabase writableDatabase;
    FragmentIndexBinding binding;
    SearchView searchView;
    Banner mBanner;
    List<Integer> imgUrls;
    RecyclerView rv;
    List<String> titles;
    List<ResultEntity> entities;
    SxAdapter sxAdapter;
    MyDialog myDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        entities=new ArrayList<>();
        writableDatabase = new MyDatabaseHelper(requireContext(), "MyData.db", null, 1).getWritableDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentIndexBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.my_bar, menu);
        super.onCreateOptionsMenu(menu, inflater);
//        searchManager = (SearchManager) requireContext().GetSystemService(Context.SearchService);
//        searchView = (SearchView) (menu.FindItem(Resource.Id.action_search).ActionView);
//        searchView.SetSearchableInfo(searchManager.GetSearchableInfo(Activity.ComponentName));
        MenuItem menuItem = menu.findItem(R.id.search);
        MenuItem menuItem2 = menu.findItem(R.id.shop_car);
        menuItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ((MainActivity)requireContext()).selectShopCar();
                return false;
            }
        });
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        //设置搜索的事件
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                Toast t = Toast.makeText(requireContext(), query, Toast.LENGTH_SHORT);
//                t.setGravity(Gravity.TOP,0,0);
//                t.show();
                query(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                query(null);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
//        Toast.makeText(getActivity(), "index is"+getShownIndex()+" && menu text is "+item.getTitle(), 1000).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBanner=view.findViewById(R.id.banner);
        rv=view.findViewById(R.id.rv);
        imgUrls=new ArrayList<>();
        titles=new ArrayList<>();
        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        sxAdapter=new SxAdapter(R.layout.itm,entities,requireContext());
        rv.setAdapter(sxAdapter);
        query(null);
        imgUrls.add(entities.get(0).img);
        imgUrls.add(entities.get(1).img);
        imgUrls.add(entities.get(2).img);
        titles.add(entities.get(0).name);
        titles.add(entities.get(1).name);
        titles.add(entities.get(2).name);
        mBanner.setImageLoader(new com.youth.banner.loader.ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                int p=(Integer)path;
                imageView.setImageResource(p);
            }
        });
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setDelayTime(3000);
        mBanner.setImages(imgUrls);
        mBanner.setBannerTitles(titles);
        mBanner.start();
        sxAdapter.setOnItemCilckListener(new SxAdapter.OnItemCilckListener() {
            @Override
            public void onClick(ResultEntity entity) {
                Intent intent=new Intent(requireContext(),AddActivity.class);
                intent.putExtra("data",entity);
                startActivity(intent);
            }
        });
    }
    private void query(String name){
        Cursor cursor;
        entities.clear();
        if (TextUtils.isEmpty(name)||name.trim().length()==0){
           cursor = writableDatabase.rawQuery("select * from Shop",null);
        }else {

          cursor = writableDatabase.query("Shop", null, "name like '%" + name + "%'",
                  null, null, null, null);
        }
        if (cursor.moveToFirst()){
            do {
                String name1=cursor.getString(cursor.getColumnIndex("name"));
                String price=cursor.getString(cursor.getColumnIndex("price"));
                int img=cursor.getInt(cursor.getColumnIndex("img"));
                String miaoshu=cursor.getString(cursor.getColumnIndex("jieshao"));
                entities.add(new ResultEntity(name1,img,miaoshu,price));
            }while (cursor.moveToNext());
        }
        cursor.close();
        sxAdapter.setNewData(entities);
        sxAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myDialog!=null){
            myDialog.dismiss();
        }
    }
}