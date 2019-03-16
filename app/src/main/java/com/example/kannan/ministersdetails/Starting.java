package com.example.kannan.ministersdetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Starting extends AppCompatActivity {
TextView admin,user;
ImageView map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        admin=(TextView)findViewById(R.id.Admin);
       user=(TextView)findViewById(R.id.User);
       map=(ImageView) findViewById(R.id.map);

       admin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(Starting.this, Login.class);
               startActivity(i);
           }
       });
       user.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(Starting.this,MainActivity .class);
               startActivity(i);


           }
       });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Starting.this, MapsActivity.class);
                startActivity(i);
            }
        });





        }
}
