package com.punuo.sys.app.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaFormat;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by asus on 2017/11/8.
 */

public class MyProvider extends ContentProvider {

    private Context mContext;
    DBHepler mDBhelper=null;
    SQLiteDatabase db=null;

    public static final String AUTOHORITY ="com.punuo.sys.app.provider";

    public static final int User_Code=1;
    public static final int Job_Code=2;

    private static final UriMatcher mMatcher;

    static {
        mMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(AUTOHORITY,"user",User_Code);
        mMatcher.addURI(AUTOHORITY,"job",Job_Code);
    }

    @Override
    public boolean onCreate() {
        mContext=getContext();
        mDBhelper=new DBHepler(getContext());
        db=mDBhelper.getWritableDatabase();
        db.execSQL("delete from user");
        db.execSQL("insert into user values(1,'Carson');");
        db.execSQL("insert into user values(2,'Kobe');");

        db.execSQL("delete from job");
        db.execSQL("insert into job values(1,'Android');");
        db.execSQL("insert into job values(2,'IOS');");

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String table=getTableName(uri);

        return db.query(table,projection,selection,selectionArgs,null,null,sortOrder,null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        String table=getTableName(uri);

        db.insert(table,null,values);

        mContext.getContentResolver().notifyChange(uri,null);

        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    private String getTableName(Uri uri) {
        String tableName=null;
        switch (mMatcher.match(uri)){
            case User_Code:
                tableName=DBHepler.USER_TABLE_NAME;
                break;
            case Job_Code:
                tableName=DBHepler.JOB_TABLE_NAME;
                break;
        }
        return tableName;
    }
}
