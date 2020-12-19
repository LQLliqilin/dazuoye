package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.liqilin.databinding.ActivityRegisterBinding;


public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    private SQLiteDatabase writableDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        writableDatabase = new MyDatabaseHelper(this, "MyData.db", null, 1).getWritableDatabase();
        binding.register.setOnClickListener(v -> {
            String name = binding.username.getText().toString();
            String pwd = binding.password.getText().toString();
            String resPwd=binding.resPassword.getText().toString();
            if (TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)||TextUtils.isEmpty(resPwd)){
                Toast.makeText(RegisterActivity.this,"请补全上面信息！",Toast.LENGTH_SHORT).show();
                return;
            }
            if (!pwd.equals(resPwd)){
                Toast.makeText(RegisterActivity.this,"密码不一致！",Toast.LENGTH_SHORT).show();
                return;
            }
            Cursor cursor = writableDatabase.rawQuery("select * from User where username=?", new String[]{name});
            if (cursor.getCount()>0){
                Toast.makeText(RegisterActivity.this,"该用户已存在！",Toast.LENGTH_SHORT).show();
                return;
            }
            ContentValues values=new ContentValues();
            values.put("username",name);
            values.put("password",pwd);
            long login = writableDatabase.insert("User", null, values);
            if (login!=-1){
                Toast.makeText(RegisterActivity.this,"注册成功，2秒后返回登录！",Toast.LENGTH_LONG).show();
            }
            new CountDownTimer(2000,2000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    finish();
                }
            }.start();
        });
    }
}