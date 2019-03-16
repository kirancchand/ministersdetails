package com.example.kannan.ministersdetails;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static android.view.View.OnClickListener;
import static com.example.kannan.ministersdetails.Message.Helper1.i3;

public class Message extends Activity {
    EditText msg;
    ListView list;
    Button send,delete;
    MSGHELP db;
    ArrayList<msgModel> msgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        list = (ListView) findViewById(R.id.list2);
        msg=(EditText)findViewById(R.id.NEWMESSAGE);
        send=(Button)findViewById(R.id.send);

        db = new MSGHELP(getApplicationContext());
        db.createTables();
       db=new MSGHELP(this);
        MessageFormat mes = new MessageFormat(this.getApplicationContext());
        msgList = mes.getAllData();
        Helper3 help = new Helper3(msgList, this.getApplicationContext());
        list.setAdapter(help);

        send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               // long t2=System.currentTimeMillis();
                Calendar cal = new GregorianCalendar();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                String date = df.format(cal.getTime());
                // Long t2=Long.parseLong(date);;

                String t1 = msg.getText().toString();
                boolean isInserted = db.insertData(t1,date);
                if (isInserted == true)
                    Toast.makeText(Message.this, "Message Sent", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Message.this, "Message Not Sent", Toast.LENGTH_LONG).show();

                msg.getText().clear();
                refresh();
            }
        });

    }
    public void refresh()
    {
        MessageFormat mes = new MessageFormat(this.getApplicationContext());
        msgList = mes.getAllData();
        Helper3 help = new Helper3(msgList, this.getApplicationContext());
        list.setAdapter(help);
    }
    public static  class Helper1
    {
        static String i3;
    }

    class Helper3 extends BaseAdapter {

        String idno;
        //SearchView search1;
        private ArrayList<msgModel> mempList;
        private ArrayList<msgModel> objects;
        private Context context;


        public Helper3(ArrayList<msgModel> list, Context cont) {
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

            Message.Helper3.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inf.inflate(R.layout.layout3, null);

                holder = new  Message.Helper3.ViewHolder();

                holder.id = (TextView) convertView.findViewById(R.id.id);
                holder.message = (TextView) convertView.findViewById(R.id.message);
                holder.date = (TextView) convertView.findViewById(R.id.date);
                holder.time = (TextView) convertView.findViewById(R.id.time);
                holder.delete=(Button)convertView.findViewById(R.id.delete);



               // Button ph = holder.delete;
             //   TextView pl = holder.place;
              //  TextView gl = holder.glno;
               // TextView i1 = holder.id;
               //TextView n1 = (TextView) convertView.findViewById(R.id.id);
               // gl.setVisibility(View.GONE);
              // b1.setVisibility(GONE);
              //  pl.setVisibility(View.GONE);
              //  i1.setVisibility(View.GONE);


                convertView.setTag(holder);
            } else {
                holder = (Message.Helper3.ViewHolder) convertView.getTag();
            }
            final Button b1 = holder.delete;
            final TextView i1 = (TextView) convertView.findViewById(R.id.text_id);
            b1.setVisibility(View.GONE);
            convertView.setOnClickListener(new OnClickListener() {

                                               @Override
                                               public void onClick(View v) {
                                                   i3 = i1.getText().toString();

                                                   b1.setVisibility(View.VISIBLE);


                                               }
                                           });
            b1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog al = AskOption();
                    al.show();
                    refresh();

                }
            });

           msgModel stu = msgList.get(position);
            holder.id.setText(stu.getId());
            holder.message.setText(stu.getMessage());
           holder.date.setText(stu.getDate());

          //  holder.time.setText(stu.getTime());


            return convertView;



        }
        private AlertDialog AskOption() {
            AlertDialog myQuittingDialogBox = new AlertDialog.Builder(Message.this)
                    //set message, title, and icon
                    .setTitle("DELETE")
                    .setMessage("Do you wanted to delete this message " )
                    .setIcon(R.drawable.icon)

                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {

                            list.invalidateViews();

                            MsgDbHlper d1 = new MsgDbHlper(getApplicationContext());
                            d1.deleteEntry(i3);
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
            public TextView time;
            public TextView message;
            public TextView date;
            public Button delete;
            // public TextView dept1;
        }

    }
}

