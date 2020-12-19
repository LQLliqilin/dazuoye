package com.example;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liqilin.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {
    private  SQLiteDatabase writableDatabase;
    ActivityLoginBinding binding;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String oldName,oldPassword;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        pref=getSharedPreferences("MyUsrData",MODE_PRIVATE);
        editor=pref.edit();
        oldName=pref.getString("name","");
        oldPassword=pref.getString("pwd","");
        binding.username.setText(oldName);
        binding.password.setText(oldPassword);
        writableDatabase = new MyDatabaseHelper(this, "MyData.db", null, 1).getWritableDatabase();
        initListener();
    }

    private void initListener() {
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.username.getText().toString();
                String pwd = binding.password.getText().toString();
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
                    Toast.makeText(LoginActivity.this,"用户名或密码不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor cursor = writableDatabase.rawQuery("select * from User where username=? and password=?", new String[]{name, pwd});
                if (cursor.getCount()!=0){
                    Contants.username=name;
                    editor.putString("name",name);
                    editor.putString("pwd",pwd);
                    editor.apply();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"用户名或密码错误！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }
}