package com.example.kannan.ministersdetails;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.kannan.ministersdetails.MainActivity.Helper1.i2;

public class AdminUpdate extends AppCompatActivity {
    public  int idno1;
    public String de;
    TextView CRank;
    EditText name1,phone1,glno1,place,places;
    Button update,ok;
    String rank2;
    Spinner spinner,ranks,department1;
    DataBaseHelper db1;
    adminview h1;





    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update);
        name1=(EditText) findViewById(R.id.name1);
      //CRank=(TextView)findViewById(R.id.currentRank);
        ranks=(Spinner) findViewById(R.id.rank);
        department1=(Spinner) findViewById(R.id.deptart);
        phone1=(EditText) findViewById(R.id.place1);

        glno1=(EditText) findViewById(R.id.dept);
        place=(EditText) findViewById(R.id.phone);
       update=(Button)findViewById(R.id.update);
       ok=(Button)findViewById(R.id.ok);
        employee emp=new employee(getApplicationContext()) ;
        Employee_Model emp1=new Employee_Model();
        emp1= emp.getAllData1();
        name1.setText(emp1.getName());
        phone1.setText(emp1.getPhone());
        glno1.setText(emp1.getNo());
        place .setText(emp1.getPlace());
        final String pno=place.getText().toString();
       rank2=emp1.getRank().toString();
       db1=new DataBaseHelper(this);

ok.setVisibility(View.GONE);
        String[] cate = new String[]{"-SELECT RANK-","CPO","SCPO","SCPO(G)","ASI(G)","ASI","SI(G)","SI","INSPECTOR","INSPECTOR(G)","DySP","Senior DySP","SP"};
        ArrayAdapter<String> adapter2=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,cate);
       ranks.setAdapter(adapter2);


        String[] items = new String[]{"-SELECT DEPARTMENT-","ICT", "SCRB", "TELE","MOIS","GAZETTE","SIB"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        department1.setAdapter(adapter);

        for(int i = 0; i <department1.getCount(); i++) {
            if ( department1.getItemAtPosition(i).toString().equals(emp1.getDept().toString())) {
                department1.setSelection(i);
               //Toast.makeText(this,"rank position "+i,Toast.LENGTH_LONG).show();
                break;
            }
        }
        for(int j = 0; j <ranks.getCount(); j++) {
            if ( ranks.getItemAtPosition(j).toString().equals(emp1.getRank().toString())) {
                ranks.setSelection(j);
             //   Toast.makeText(this,"rank position "+i,Toast.LENGTH_LONG).show();
                break;
            }

        }
update.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Updatedata1();
      //  h1.refresh();
    }
});






    }
    public void Updatedata1() {

                String R1 = ranks.getSelectedItem().toString();
                String d1 = department1.getSelectedItem().toString();

        String text3=name1.getText().toString();
        String text4=place.getText().toString();
        String text5=glno1.getText().toString();
        String text6=phone1.getText().toString();
        int len=text6.length();
        // int re=mn+1;
        //  if (re == 10) {
      //  Toast.makeText(getApplicationContext(),"length"+len,Toast.LENGTH_LONG).show();
        if(TextUtils.isEmpty(text3)) {
            name1.setError("Name cannot be empty");
            return;
        }

        else if (len!=10 ) {
            //Toast.makeText(getApplicationContext(),"length"+text4.length(),Toast.LENGTH_LONG).show();

            phone1.setError("Phone number cannot be empty or less than 10");
            return;
        }

        else if (TextUtils.isEmpty(text4)) {
            place.setError("Place cannot be empty");
            return;
        }
        else if (TextUtils.isEmpty(text5)) {
            glno1.setText("No Gl Number ");
        }


            boolean isUpdate=db1.Updatedata(i2, name1.getText().toString(),d1,R1,glno1.getText().toString(), phone1.getText().toString(), place.getText().toString());


                if(isUpdate==true) {
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                    ok.setVisibility(View.VISIBLE);
                   ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i=new Intent(AdminUpdate.this,adminview .class);
                            startActivity(i);
                        }
                    });
                }
                 else
                   Toast.makeText(getApplicationContext(), " Not Updated", Toast.LENGTH_LONG).show();
            }



    }



