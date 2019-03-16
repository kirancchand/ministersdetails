package com.example.kannan.ministersdetails;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.kannan.ministersdetails.MainActivity.Helper1.i2;

public class adminview extends AppCompatActivity {
    DataBaseHelper db;
    // TextView text;
    ListView list;
    SearchView search;
    Button edit,delete;
    ArrayList<Employee_Model> employeeList = new ArrayList<>();
    //String[] Array1 = {"Kannan S Nair", "Kiran V J"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminview);
        //db = new DataBaseHelper(getApplicationContext());
        //db.createTables();
        // text = (TextView) findViewById(R.id.text1);
        list = (ListView) findViewById(R.id.list1);
        search = (SearchView) findViewById(R.id.search1);

        //get data as list from database

        employee employee = new employee(this.getApplicationContext());
        employeeList = employee.getAllData();
        Log.e("Main ", employeeList.toString());

        //set adapter
        Helper2 help = new Helper2(employeeList, this.getApplicationContext());
        list.setAdapter(help);
//        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, R.layout.layout1, R.id.text1, Array1);
//        list.setAdapter(Adapter);
    }
    public void refresh()
    {
        employee employee = new employee(this.getApplicationContext());
        employeeList = employee.getAllData();
        Helper2 help = new Helper2(employeeList, this.getApplicationContext());
        list.setAdapter(help);
    }
    public static  class Helper1
    {
        static String i2;
    }

    class Helper2 extends BaseAdapter {
        String idno;
        SearchView search1;
        private ArrayList<Employee_Model> empList;
        private ArrayList<Employee_Model> objects;
        private Context context;
        String name1;
        // final AlertDialog alertDialog = new AlertDialog.Builder(this).create();


        public Helper2(ArrayList<Employee_Model> list, Context cont) {
            this.empList = list;
            this.context = cont;
        }

        @Override
        public int getCount() {
            return this.empList.size();
        }

        @Override
        public Object getItem(int position) {
            return this.empList.get(position);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }


        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inf.inflate(R.layout.layout2, null);
                // Button edit1=(Button)row.findViewById( R.id.bt_edit);

                holder = new ViewHolder();

                holder.vi = (Button) convertView.findViewById(R.id.bt_View);
                holder.de = (Button) convertView.findViewById(R.id.bt_delete);
                holder.edit1 = (Button) convertView.findViewById(R.id.bt_edit);
                holder.id = (TextView) convertView.findViewById(R.id.text_id);
                holder.name = (TextView) convertView.findViewById(R.id.text_Name);
                holder.dept = (TextView) convertView.findViewById(R.id.dept32);
                holder.rank = (TextView) convertView.findViewById(R.id.text_rank);
                holder.glno = (TextView) convertView.findViewById(R.id.text_glno);
                holder.phone = (TextView) convertView.findViewById(R.id.text_phone);
                holder.place = (TextView) convertView.findViewById(R.id.text_place);


                TextView ph = holder.phone;
                TextView pl = holder.place;
                TextView gl = holder.glno;
                TextView i1 = holder.id;


                //Toast.makeText(this,"id",Toast.LENGTH_LONG).show();
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final Button b1 = holder.vi;
            final Button b2 = holder.edit1;
            final Button b3 = holder.de;

            b1.setVisibility(View.GONE);
            b3.setVisibility(View.GONE);
            b2.setVisibility(View.GONE);
            //final Button b3=(Button)convertView.findViewById(R.id.bt_edit);
            final TextView n1 = (TextView) convertView.findViewById(R.id.text_Name);
            final TextView gl = (TextView) convertView.findViewById(R.id.text_glno);
            final TextView ph = (TextView) convertView.findViewById(R.id.text_phone);
            final TextView pl = (TextView) convertView.findViewById(R.id.text_place);
            final TextView i1 = (TextView) convertView.findViewById(R.id.text_id);
            //final TextView d1 = (TextView) convertView.findViewById(R.id.department3);

            gl.setVisibility(View.GONE);
            ph.setVisibility(View.GONE);
            pl.setVisibility(View.GONE);
            i1.setVisibility(View.GONE);
            //d1.setVisibility(View.GONE);


            convertView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    i2 = i1.getText().toString();
                    name1 = n1.getText().toString();


                    b1.setVisibility(View.VISIBLE);
                    b2.setVisibility(View.VISIBLE);
                    b3.setVisibility(View.VISIBLE);


                    // i1.setVisibility(View.VISIBLE);
                    //  i2=i1.getText().toString();
                    // Toast.makeText(this,"hi",Toast.LENGTH_LONG).show();

                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(adminview.this, EmployeeDetailsView.class);
                            i.putExtra("idno", i2);
                            startActivity(i);
                            refresh();

                            //  b1.setVisibility(View.GONE);
                            // b2.setVisibility(View.GONE);
                            // b3.setVisibility(View.GONE);
                        }
                    });


                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent i = new Intent(adminview.this, AdminUpdate.class);
                            i.putExtra("idno", i2);
                            startActivity(i);
                            refresh();

                        }
                    });
                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog al = AskOption();
                            al.show();
                            refresh();
                        }
                    });



                }

            });



            Employee_Model stu = empList.get(position);
            holder.id.setText(stu.getId());
            holder.name.setText(stu.getName());
            holder.dept.setText(stu.getDept());
            holder.rank.setText(stu.getRank());
            holder.glno.setText(stu.getNo());
            holder.phone.setText(stu.getPhone());
            holder.place.setText(stu.getPlace());

            return convertView;
           // Intent intent = new Intent(adminview.this, Form.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             //Bundle bundle = new Bundle();
             //bundle.putString("MyValue1",);
            //intent.putExtras(bundle);
            //startActivity(intent);



        }

        private AlertDialog AskOption() {
            AlertDialog myQuittingDialogBox = new AlertDialog.Builder(adminview.this)
                    //set message, title, and icon
                    .setTitle("DELETE")
                    .setMessage("Do you wanted to delete " + name1)
                    .setIcon(R.drawable.icon)

                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {

                            list.invalidateViews();

                            DataBaseHelper d1 = new DataBaseHelper(getApplicationContext());
                            d1.deleteEntry(i2);
                            refresh();
                        }
                    })

                    .setNeutralButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                            dialog.dismiss();
                        }
                    })

                    .create();
            return myQuittingDialogBox;

        }


        private class ViewHolder {
            public TextView id;
            public TextView name;
            public TextView rank;
            public TextView glno;
            public TextView phone;
            public TextView place;
            public Button edit1;
            public Button vi;
            public Button de;
            public TextView dept;
        }

    }


}

