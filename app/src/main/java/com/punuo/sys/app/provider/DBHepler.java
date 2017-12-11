package com.punuo.sys.app.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PublicKey;

/**
 * Created by asus on 2017/11/8.
 */

public class DBHepler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="finch.db";

    public static final String USER_TABLE_NAME="user";
    public static final String JOB_TABLE_NAME="job";

    private static final int DATABASE_VERSION=1;

    public DBHepler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS" + USER_TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS" + JOB_TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," + "job TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
