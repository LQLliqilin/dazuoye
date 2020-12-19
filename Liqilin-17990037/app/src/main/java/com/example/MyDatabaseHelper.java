package com.example;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public static final String USER="create table User("
            +"id integer primary key autoincrement,"
            +"username text,"
            +"address text,"
            +"password text)";

    public static final String SHOP="create table Shop("
            +"id integer primary key autoincrement,"
            +"name text,"
            +"img integer,"
            +"price text,"
            +"jieshao text)";
    public static final String SHOPCAR="create table ShopCar("
            +"id integer primary key autoincrement,"
            +"name text,"
            +"username text,"
            +"img integer,"
            +"price text,"
            +"number integer,"
            +"jieshao text)";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER);
        db.execSQL(SHOP);
        db.execSQL(SHOPCAR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
