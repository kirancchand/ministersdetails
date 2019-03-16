package com.example.kannan.ministersdetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddData extends AppCompatActivity {
    DataBaseHelper myDb;

 Button b1;
 EditText t1,t4,t5,t6;
 Spinner t2,t3;
//int mj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adddata);
        myDb = new DataBaseHelper(getApplicationContext());
        myDb.createTables();
        myDb=new DataBaseHelper(this);

        b1=(Button)findViewById(R.id.add);
        t1=(EditText)findViewById(R.id.name);
        t2=(Spinner) findViewById(R.id.dept32);
        t3=(Spinner) findViewById(R.id.rank);
        t4=(EditText)findViewById(R.id.gl);
        t5=(EditText)findViewById(R.id.ph);
        t6=(EditText)findViewById(R.id.phone);

        AdData();

        String[] cate = new String[]{"-SELECT RANK-","CPO","SCPO","SCPO(G)","ASI(G)","ASI","SI(G)","SI","INSPECTOR","INSPECTOR(G)","DySP","Senior DySP","SP"};
         ArrayAdapter<String> adapter2=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,cate);
        t3.setAdapter(adapter2);

        String[] items = new String[]{"-SELECT DEPARTMENT-","ICT", "SCRB", "TELE","MOIS","GAZETTE","SIB"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        t2.setAdapter(adapter);






        //String k=String.valueOf(t2.getSelectedItem());


//System.out.print(t2.getSelectedItem());

        //getAllData();
    }




    public void AdData(){
          b1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                 String text = t2.getSelectedItem().toString();
                  String text2 = t3.getSelectedItem().toString();
                  //Toast.makeText(AddData.this, "count ="+text, Toast.LENGTH_LONG).show();

                  String text3=t1.getText().toString();
                  String text4=t5.getText().toString();
                  String text5=t6.getText().toString();
                  String text6=t4.getText().toString();
           //int mn = Integer.parseInt(t5.getText().toString());
           //   while ((mn/= 10) >= 1) {
              //  mn++;
                //  Toast.makeText(getApplicationContext(),"length"+text4.length(),Toast.LENGTH_LONG).show();
 int len=text4.length();
              // int re=mn+1;
               //  if (re == 10) {
                  //Toast.makeText(getApplicationContext(),"length"+len,Toast.LENGTH_LONG).show();
                  if(TextUtils.isEmpty(text3)) {
                      t1.setError("Name cannot be empty");
                      return;
                  }

                  else if (len!=10 ) {
                      //Toast.makeText(getApplicationContext(),"length"+text4.length(),Toast.LENGTH_LONG).show();

                      t5.setError("Phone number cannot be empty or less than 10");
                      return;
                  }

                  else if (TextUtils.isEmpty(text5)) {
                      t6.setError("Place cannot be empty");
                      return;
                  }
                  else if (TextUtils.isEmpty(text6)) {
                      t4.setText("No Gl Number ");
                      boolean isInserted = myDb.insertData(t1.getText().toString(), text, text2, t4.getText().toString(), t5.getText().toString(), t6.getText().toString());
                      if (isInserted == true)
                          Toast.makeText(AddData.this, "Data Inserted", Toast.LENGTH_LONG).show();
                      else
                          Toast.makeText(AddData.this, "Data not Inserted", Toast.LENGTH_LONG).show();

                      t1.getText().clear();
                      t2.setSelection(0);
                      t3.setSelection(0);
                      t4.getText().clear();
                      t5.getText().clear();
                      t6.getText().clear();


                      // }
                      //   else
                      //  Toast.makeText(AddData.this, "Reinsert your phone number", Toast.LENGTH_LONG).show();

                      return;
                  }


else{

                          boolean isInserted = myDb.insertData(t1.getText().toString(), text, text2, t4.getText().toString(), t5.getText().toString(), t6.getText().toString());
                          if (isInserted == true)
                              Toast.makeText(AddData.this, "Data Inserted", Toast.LENGTH_LONG).show();
                          else
                              Toast.makeText(AddData.this, "Data not Inserted", Toast.LENGTH_LONG).show();

                          t1.getText().clear();
                          t2.setSelection(0);
                          t3.setSelection(0);
                          t4.getText().clear();
                          t5.getText().clear();
                          t6.getText().clear();


                          // }
                          //   else
                          //  Toast.makeText(AddData.this, "Reinsert your phone number", Toast.LENGTH_LONG).show();

                      }
                  }
           });


          }
}
