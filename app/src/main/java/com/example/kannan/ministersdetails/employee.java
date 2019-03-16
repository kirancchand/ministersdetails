package com.example.kannan.ministersdetails;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static com.example.kannan.ministersdetails.DataBaseHelper.DATABASE_NAME;
import static com.example.kannan.ministersdetails.DataBaseHelper.TABLE_NAME;
import static com.example.kannan.ministersdetails.MainActivity.Helper1.i2;

public class employee extends SQLiteOpenHelper{
    SQLiteOpenHelper db;
  private String id;
    private String name;
    private  String dept;
    private String rank;
    private String no;
    private String phone;
private String place;


    public employee(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
   public void  employeeDetails(String id, String name, String rank, String no, String phone,String place) {

        this.id = id;
        this.name = name;
        this.dept= dept;
        this.rank = rank;
        this.no=no;
        this.phone= phone;
        this.place=place;
    }
    public ArrayList<Employee_Model> getAllData() {
        ArrayList<Employee_Model> emlist = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);

        while(res.moveToNext()) {
            String id = res.getString(0);   //0 is the number of id column in your database table
            String name = res.getString(1);
            String dept = res.getString(2);
            String rank = res.getString(3);
            String no = res.getString(4);
            String phone = res.getString(5);
            String place = res.getString(6);

            Log.e("employee", name);
            Employee_Model  newemp = new Employee_Model();
             newemp.setId(id);
            newemp.setName(name);
            newemp.setRank(rank);
            newemp.setNo(no);
            newemp.setDept(dept);
            newemp.setPhone(phone);
            newemp.setPlace(place);
            Log.e("employee", newemp.toString());
            emlist.add(newemp);
        }
        return emlist;
    }
    public Employee_Model getAllData1() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where id =" +Integer.parseInt(i2),null);
        Employee_Model newemp=null;
        while(res.moveToNext()) {
            String id = res.getString(0);   //0 is the number of id column in your database table
            String name = res.getString(1);
            String dept = res.getString(2);
            String rank = res.getString(3);
            String no = res.getString(4);
            String phone = res.getString(5);
            String place = res.getString(6);

            Log.e("employee", name);
             newemp = new Employee_Model();
            newemp.setId(id);
            newemp.setName(name);
            newemp.setRank(rank);
            newemp.setNo(no);
            newemp.setDept(dept);
          newemp.setPhone(phone);
           newemp.setPlace(place);

        }
        return newemp;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public String getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getRank()
{       return this.rank;
    }
    public String getNo() {
        return this.no;

      }
      public String getDept(){return  this.dept;}

    public String getPhone(){
        return this.phone;
    }
    public String getPlace()
    {
    return this.phone;
    }

    public String getGlno() {
        return this.no;
    }

}
