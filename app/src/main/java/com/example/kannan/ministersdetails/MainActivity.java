package com.example.kannan.ministersdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.kannan.ministersdetails.MainActivity.Helper1.i2;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    DataBaseHelper db;


    ListView list;
    SearchView search;
    ImageButton Message;

    ArrayList<Employee_Model> employeeList = new ArrayList<>();
    ArrayList<Employee_Model> employeeList1 = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list1);
        search = (SearchView) findViewById(R.id.search1);
        Message=(ImageButton)findViewById(R.id.Messages);
       // search.setQueryHint("Enter search");


        //get data as list from database

        employee employee = new employee(this.getApplicationContext());
        employeeList = employee.getAllData();
        Log.e("Main ", employeeList.toString());




        //set adapter
        Helper help = new Helper(employeeList, this.getApplicationContext());
        list.setAdapter(help);

        //set adapter
        Helper help1 = new Helper(employeeList1, this.getApplicationContext());

        Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, UserMessageView.class);
                startActivity(i);

            }
        });
        }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;

    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Employee_Model1 employee1=new Employee_Model1(this.getApplicationContext());
        employeeList1 = employee1.getAllData(newText);
        list.setVisibility(View.GONE);
        Toast.makeText(this,"Text"+newText,Toast.LENGTH_LONG).show();
        Helper help1 = new Helper(employeeList1, this.getApplicationContext());
        list.setAdapter(help1);
        return true;
    }

    public static class Helper1 {
        static String i2;
    }

    class Helper extends BaseAdapter  {

        String idno;
        //SearchView search1;
        private ArrayList<Employee_Model> empList;
        private ArrayList<Employee_Model> objects;
        private Context context;


        public Helper(ArrayList<Employee_Model> list, Context cont) {
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
                convertView = inf.inflate(R.layout.layout1, null);

                holder = new ViewHolder();

                holder.id = (TextView) convertView.findViewById(R.id.text_id);
                holder.name = (TextView) convertView.findViewById(R.id.text_Name);
                holder.rank = (TextView) convertView.findViewById(R.id.text_rank);
                holder.glno = (TextView) convertView.findViewById(R.id.text_glno);
                holder.phone = (TextView) convertView.findViewById(R.id.text_phone);
                holder.place = (TextView) convertView.findViewById(R.id.text_place);
                TextView na = holder.name;

                TextView ph = holder.phone;
                TextView pl = holder.place;
                TextView gl = holder.glno;
                TextView i1 = holder.id;

                gl.setVisibility(View.GONE);
                ph.setVisibility(View.GONE);
                pl.setVisibility(View.GONE);
                i1.setVisibility(View.GONE);


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final TextView gl = (TextView) convertView.findViewById(R.id.text_glno);
            final TextView ph = (TextView) convertView.findViewById(R.id.text_phone);
            final TextView pl = (TextView) convertView.findViewById(R.id.text_place);
            final TextView i1 = (TextView) convertView.findViewById(R.id.text_id);


            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    i2 = i1.getText().toString();


                    Intent i = new Intent(MainActivity.this, EmployeeDetailsView.class);
                    i.putExtra("idno", i2);
                    startActivity(i);


                }

            });

            Employee_Model stu = empList.get(position);
            holder.id.setText(stu.getId());
            holder.name.setText(stu.getName());
            holder.rank.setText(stu.getRank());
            //holder.dept1.setText(stu.getDept());
            holder.glno.setText(stu.getNo());
            holder.phone.setText(stu.getPhone());
            holder.place.setText(stu.getPlace());


            return convertView;


        }


        private class ViewHolder {
            public TextView id;
            public TextView name;
            public TextView rank;
            public TextView glno;
            public TextView phone;
            public TextView place;
            // public TextView dept1;
        }

    }
}







