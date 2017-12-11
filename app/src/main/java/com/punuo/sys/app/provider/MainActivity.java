package com.punuo.sys.app.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri_user=Uri.parse("content://com.punuo.sys.app.provider/user");

        ContentValues values=new ContentValues();
        values.put("_id",3);
        values.put("name","Iverson");

        ContentResolver resolver=getContentResolver();
        resolver.insert(uri_user,values);

        Cursor cursor=resolver.query(uri_user,new String[]{"_id","name"},null,null,null);
        while (cursor.moveToNext()){
            Log.e(TAG,"query book:"+cursor.getInt(0)+" "+cursor.getString(1));
        }
        cursor.close();

        Uri uri_job=Uri.parse("content://com.punuo.sys.app.provider/job");

        ContentValues values2=new ContentValues();
        values2.put("_id",3);
        values2.put("job","NBA Player");

        ContentResolver resolver2=getContentResolver();
        resolver2.insert(uri_job,values2);

        Cursor cursor2=resolver2.query(uri_job,new String[]{"_id","job"},null,null,null);
        while (cursor2.moveToNext()){
            Log.e(TAG,"query job:"+cursor2.getInt(0)+" "+cursor2.getString(1));
        }
        cursor2.close();

    }
}
