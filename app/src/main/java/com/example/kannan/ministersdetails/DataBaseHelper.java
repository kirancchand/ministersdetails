package com.example.kannan.ministersdetails;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ICT.db";
    public static final String TABLE_NAME = "DIRECTORY";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DEPARTMENT";
    public static final String COL_4 = "RANK";
    public static final String COL_5 = "GLNO";
    public static final String COL_6 = "PHONENUMBER";
    public static final String COL_7 = "PLACE";
    SQLiteDatabase db;

    public DataBaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
        db=this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT NOT NULL,DEPARTMENT TEXT NOT NULL,RANK TEXT NOT NULL,GLNO TEXT,PHONENUMBER TEXT UNIQUE NOT NULL,PLACE TEXT)");
        Log.e("database", "database table"+TABLE_NAME+"created");
    }
    public void createTables() {
        db.execSQL(" CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT NOT NULL,DEPARTMENT TEXT NOT NULL,RANK TEXT NOT NULL,GLNO TEXT,PHONENUMBER TEXT UNIQUE NOT NULL,PLACE TEXT)");
        Log.e("database", "database table"+TABLE_NAME+"created");
    }
    public boolean insertData(String name, String department, String rank, String glno, String phno, String place) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, department);
        contentValues.put(COL_4, rank);
        contentValues.put(COL_5, glno);
        contentValues.put(COL_6, phno);
        contentValues.put(COL_7, place);
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
public boolean Updatedata(String id, String name1, String department, String rank, String glno, String phone, String place)
{
    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues args = new ContentValues();
   args.put(DataBaseHelper.COL_1,id);
    args.put(DataBaseHelper.COL_2,name1);
    args.put(DataBaseHelper.COL_3, department);
    args.put(DataBaseHelper.COL_4, rank);
    args.put(DataBaseHelper.COL_5, glno);
    args.put(DataBaseHelper.COL_6, phone);
    args.put(DataBaseHelper.COL_7, place);
     db.update(DataBaseHelper.TABLE_NAME, args, "ID  =?" , new String[]{id});
//db.close();
return true;


}
    // Updating single contact
    public void updateDatas(String i2, String n, String r1, String d1, String s, String position, String value) {
        SQLiteDatabase db = this.getWritableDatabase();

        String update = "UPDATE datas SET NAME = '"+ n +"' WHERE ID = " + position;
        db.execSQL(update);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS directory");

    }
    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return data;
    }

//method to display data

    public Cursor getLitContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return data;
    }

//method to display data

    public Cursor displayData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(" SELECT  * FROM " + TABLE_NAME, null);
        return res;
    }
    public  void deleteEntry(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE ID=" + id);
        db.close();


      /*if you just have key_name to select a row,you can ignore passing rowid(here-row) and use:

      db.delete(DATABASE_TABLE, KEY_NAME + "=" + key_name, null);
      */


    }}