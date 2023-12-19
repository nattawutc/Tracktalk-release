package com.blackpuppydev.tracktalk_release.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class StandardData extends SQLiteOpenHelper {

    private static final String TAG = "StandardData";
    private static final String DATABASE_NAME = "StandardData.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public StandardData(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + StandardEntry.TABLE_NAME + " (" +
                StandardEntry._ID + " INTEGER PRIMARY KEY," +
                StandardEntry.NATIVE_LANGUAGE + " TEXT," +
                StandardEntry.STATUS_SPEECH + " BOOLEAN)");

        db.execSQL("CREATE TABLE " + StandardEntry.TABLE_NAME2 + " (" +
                StandardEntry._ID + " INTEGER PRIMARY KEY," +
                StandardEntry.USERNAME + " TEXT," +
                StandardEntry.PASSWORD + " TEXT," +
                StandardEntry.EMAIL + " TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void writeSetting(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(StandardEntry.NATIVE_LANGUAGE,"th-TH");
        contentValues.put(StandardEntry.STATUS_SPEECH,true);
        db.insertOrThrow(StandardEntry.TABLE_NAME,null,contentValues);

    }

    public void readSetting(){
        Cursor readData = db.rawQuery("select * from " + StandardEntry.TABLE_NAME,null);

        while (readData.moveToNext()){
            Log.d(TAG,"read data : " + readData.getString(1) + " " + readData.getString(2));
        }
    }

    public void update(){

    }

    public void delete(){

    }

//    public boolean update(String nativelang,double perc,String chaday){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues content = new ContentValues();
//        content.put("nativelang",nativelang);
//        content.put("percentagenone",perc);
//        content.put("chaday",chaday);
//        db.update(TABLE_NAME0,content,"_id=?",new String[]{String.valueOf(1)});
//        return true;
//    }


    public static class StandardEntry implements BaseColumns {

        public static final String TABLE_NAME = "standard";
        public static final String NATIVE_LANGUAGE = "native_language";
        public static final String STATUS_SPEECH = "status_speech";

        public static final String TABLE_NAME2 = "user";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String EMAIL = "email";

    }


}
