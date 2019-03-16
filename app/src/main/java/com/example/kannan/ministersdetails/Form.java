package com.example.kannan.ministersdetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Form extends AppCompatActivity {
    Button bt1,bt2,bt3;
    TextView txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        bt1=(Button)findViewById(R.id.bt1);
        bt2=(Button)findViewById(R.id.bt2);
        bt3=(Button)findViewById(R.id.bt3);
        txt1=(TextView)findViewById(R.id.txt1);
        bt1.setOnClickListener(new View.OnClickListener() {
                                   public void onClick(View v) {
                                       startActivity(new Intent(Form.this, AddData.class));

                                   }
                               });
            bt2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(Form.this, adminview.class));

                }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Form.this,Message.class));

            }
        });



    }
}