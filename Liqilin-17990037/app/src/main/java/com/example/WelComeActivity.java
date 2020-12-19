package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.liqilin.R;


public class WelComeActivity extends AppCompatActivity {
    private SQLiteDatabase writableDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);
        setStatusTransparent();
        getSupportActionBar().hide();
        writableDatabase = new MyDatabaseHelper(this, "MyData.db", null, 1).getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery("select * from Shop",null);
        if (cursor.getCount()==0){
           addData();
        }
        new CountDownTimer(2000,2000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(WelComeActivity.this,LoginActivity.class));
                finish();
            }
        }.start();
    }
    /**
     * 说明：Android 4.4+ 设置状态栏透明
     */
    protected void setStatusTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 5.0+ 实现
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 4.4 实现
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    private void addData(){
        ContentValues values=new ContentValues();
        values.put("name","aj");
        values.put("img",R.drawable.aj);
        values.put("price","99.9");
        values.put("jieshao","高级aj");
         writableDatabase.insert("Shop", null, values);
         values.clear();
        values.put("name","aj1");
        values.put("img",R.drawable.aj1);
        values.put("jieshao","高级aj1");
        values.put("price","103.9");
        writableDatabase.insert("Shop", null, values);
        values.clear();
        values.put("name","aj2");
        values.put("img",R.drawable.aj2);
        values.put("price","88.9");
        values.put("jieshao","高级aj2");
        writableDatabase.insert("Shop", null, values);
        values.clear();
        values.put("name","aj3");
        values.put("img",R.drawable.aj3);
        values.put("price","77.9");
        values.put("jieshao","高级aj3");
        writableDatabase.insert("Shop", null, values);
        values.clear();
        values.put("name","aj4");
        values.put("img",R.drawable.aj4);
        values.put("price","55.9");
        values.put("jieshao","高级aj4");
        writableDatabase.insert("Shop", null, values);
        values.clear();
        values.put("name","滑板鞋");
        values.put("price","66.9");
        values.put("img",R.drawable.aj);
        values.put("jieshao","高级滑板鞋");
        writableDatabase.insert("Shop", null, values);
        values.clear();
        values.put("name","高级耐克");
        values.put("img",R.drawable.naike3);
        values.put("price","220.9");
        values.put("jieshao","高级耐克");
        writableDatabase.insert("Shop", null, values);
        values.clear();
        values.put("name","高级耐克1");
        values.put("price","159.9");
        values.put("img",R.drawable.naike1);
        values.put("jieshao","男士耐克1");
        writableDatabase.insert("Shop", null, values);
        values.clear();
        values.put("name","高级耐克2");
        values.put("price","201.9");
        values.put("img",R.drawable.naike2);
        values.put("jieshao","男士耐克2");
        writableDatabase.insert("Shop", null, values);
        values.clear();
        values.put("name","高级耐克3");
        values.put("price","101.9");
        values.put("img",R.drawable.naike3);
        values.put("jieshao","男士耐克3");
        writableDatabase.insert("Shop", null, values);
        values.clear();
        values.put("name","高级耐克4");
        values.put("price","100.9");
        values.put("img",R.drawable.naike4);
        values.put("jieshao","男士耐克4");
        writableDatabase.insert("Shop", null, values);
        values.clear();
        values.put("name","高级耐克5");
        values.put("price","70.9");
        values.put("img",R.drawable.naike5);
        values.put("jieshao","男士耐克5");
        writableDatabase.insert("Shop", null, values);
        values.clear();
        values.put("name","乔丹");
        values.put("price","80.9");
        values.put("img",R.drawable.aj);
        values.put("jieshao","男士乔丹");
        writableDatabase.insert("Shop", null, values);
        values.clear();
        values.put("name","乔丹1");
        values.put("price","56.9");
        values.put("img",R.drawable.qiaodan1);
        values.put("jieshao","男士乔丹1");
        writableDatabase.insert("Shop", null, values);
        values.clear();
        values.put("name","乔丹2");
        values.put("price","152.9");
        values.put("img",R.drawable.qiaodan2);
        values.put("jieshao","男士乔丹2");
        writableDatabase.insert("Shop", null, values);
        values.clear();
        values.put("name","乔丹3");
        values.put("price","89.9");
        values.put("img",R.drawable.qiaodan3);
        values.put("jieshao","男士乔丹3");
        writableDatabase.insert("Shop", null, values);
        values.clear();
    }
}