package com.example.kannan.ministersdetails;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class EmployeeDetailsView extends AppCompatActivity {
public  int idno1;
public String de;
TextView name1,rank1,phone1,glno1,place,dept;
ImageButton call,save,vdo;

    DataBaseHelper db;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details_view);
        name1=(TextView)findViewById(R.id.name1);
        rank1=(TextView)findViewById(R.id.rank1);
       phone1=(TextView)findViewById(R.id.phone);
        dept=(TextView)findViewById(R.id.dept);
        place=(TextView)findViewById(R.id.place);
        call=(ImageButton)findViewById(R.id.call);
        save=(ImageButton)findViewById(R.id.save);
       glno1=(TextView)findViewById(R.id.glno);
       vdo=(ImageButton)findViewById(R.id.vdo);
        employee emp=new employee(getApplicationContext()) ;
        Employee_Model emp1=new Employee_Model();
        emp1= emp.getAllData1();
       name1.setText(emp1.getName());
        rank1.setText(emp1.getRank());
       dept.setText(emp1.getDept());
        phone1.setText(emp1.getPhone());
        glno1.setText(emp1.getNo());
        place .setText(emp1.getPlace());
        final String pno=phone1.getText().toString();
        final String name=name1.getText().toString();
        final String rank=rank1.getText().toString();
       // Toast.makeText(this,"Phone number"+pno,Toast.LENGTH_LONG).show();
       call.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +pno ));
                startActivity(intent);

            }
        });
       save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + pno));
               startActivity(intent);
           }
       });
        vdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Name : "+name+ "\nPhone Number :"+pno+ "\n Rank:"+rank);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

    }



}
