package com.example.kannan.ministersdetails;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.example.kannan.ministersdetails.MSGHELP.DATABASE_NAME;
import static com.example.kannan.ministersdetails.MSGHELP.TABLE_NAME;

public class MessageFormat extends SQLiteOpenHelper {
    SQLiteOpenHelper db;
    private String id;
    private String message;
  private  String date;
  //  private String time;

    public MessageFormat(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    public void  msgDetails(String id, String message, String date) {

        this.id = id;
        this.message = message;
        this.date= date;
        //this.time = time;


}
    public ArrayList<msgModel> getAllData() {
        ArrayList<msgModel> emlist = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);

        while (res.moveToNext()) {
            String id = res.getString(0);   //0 is the number of id column in your database table
            String message = res.getString(1);
          String date = res.getString(2);
            //String time = res.getString(2);


            // Log.e("employee", name);
            msgModel newmsg = new msgModel();
            newmsg.setId(id);
            newmsg.setMessage(message);
            newmsg.setDate(date);
            //newmsg.setTime(time);

            emlist.add(newmsg);
        }
        return emlist;
    }
        public void onCreate(SQLiteDatabase db) {

        }


        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public String getId(){
            return this.id;
        }

        public String getMessage() {
            return this.message;
        }

       public String getDate()
       {       return this.date;
        }
     //   public String getTime() {
           // return this.time;

      //  }


    }