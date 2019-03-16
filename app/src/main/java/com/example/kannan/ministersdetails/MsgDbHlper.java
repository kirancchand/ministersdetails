package com.example.kannan.ministersdetails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MsgDbHlper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ICT.db";
    public static final String TABLE_NAME = "MESSAGE";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "MESSAGE";
    public static final String COL_3 = "DATE";
    //public static final String COL_4 = "TIME";
    SQLiteDatabase db;

    public MsgDbHlper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,MESSAGE TEXT NOT NULL,DATE LONG NOT NULL)");
        Log.e("database", "database table"+TABLE_NAME+"created");

    }
    public void createTables() {
        db.execSQL(" CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,MESSAGE TEXT NOT NULL,DATE LONG NOT NULL)");
        Log.e("database", "database table"+TABLE_NAME+"created");

    }
    public boolean insertData(String t1, Long date) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, t1);
        contentValues.put(COL_3, date);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;

        else
            return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public  void deleteEntry(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME+ " WHERE ID="+id);
        db.close();


      /*if you just have key_name to select a row,you can ignore passing rowid(here-row) and use:

      db.delete(DATABASE_TABLE, KEY_NAME + "=" + key_name, null);
      */

    }

}