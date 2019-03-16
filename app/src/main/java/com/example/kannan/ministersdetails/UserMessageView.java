package com.example.kannan.ministersdetails;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserMessageView extends Activity {
    ListView list;
    MsgDbHlper db;
    ArrayList<msgModel> msgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message_view);
        list = (ListView) findViewById(R.id.list2);
        db = new MsgDbHlper(getApplicationContext());
        db.createTables();
        db=new MsgDbHlper(this);
        MessageFormat mes = new MessageFormat(this.getApplicationContext());
        msgList = mes.getAllData();
        UserMessageView.Helper4 help = new UserMessageView.Helper4(msgList, this.getApplicationContext());
        list.setAdapter(help);

    }



    class Helper4 extends BaseAdapter {

        String idno;
        //SearchView search1;
        private ArrayList<msgModel> mempList;
        private ArrayList<msgModel> objects;
        private Context context;


        public Helper4(ArrayList<msgModel> list, Context cont) {
            this.mempList = list;
            this.context = cont;
        }

        @Override
        public int getCount() {
            return this.mempList.size();
        }

        @Override
        public Object getItem(int position) {
            return this.mempList.get(position);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }


        public View getView(int position, View convertView, ViewGroup parent) {

            UserMessageView.Helper4.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inf.inflate(R.layout.layout3, null);

                holder = new  UserMessageView.Helper4.ViewHolder();

                holder.id = (TextView) convertView.findViewById(R.id.id);
                holder.message = (TextView) convertView.findViewById(R.id.message);
                holder.date = (TextView) convertView.findViewById(R.id.date);
                holder.time = (TextView) convertView.findViewById(R.id.time);


                convertView.setTag(holder);
            } else {
                holder = (UserMessageView.Helper4.ViewHolder) convertView.getTag();
            }



            msgModel stu = msgList.get(position);
            holder.id.setText(stu.getId());
            holder.message.setText(stu.getMessage());
             holder.date.setText(stu.getDate());
            //holder.time.setText(stu.getTime());


            return convertView;



        }


        private class ViewHolder {
            public TextView id;
            public TextView time;
            public TextView message;
            public TextView date;
           // public Button delete;
          //  // public TextView dept1;
        }

    }
}

