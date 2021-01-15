package com.example.barcodescanner;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomGVAdapter extends BaseAdapter{

    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public CustomGVAdapter(Context context, String[] NameList, int[] Images) {
        // TODO Auto-generated constructor stub
        result=NameList;
        this.context=context;
        imageId=Images;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView textView;
        ImageView imageView;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.grid_layout, null);
        holder.textView =(TextView) rowView.findViewById(R.id.texts);
        holder.imageView =(ImageView) rowView.findViewById(R.id.images);
        holder.textView.setText(result[position]);
        holder.imageView.setImageResource(imageId[position]);

        rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String subject;
                String semester;
                String author;
//                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_SHORT).show();
                if(result[position]=="Data Structures through C"){
                    subject="ITC";
                    semester="2";
                    author="yashwant";
                }
                else if(result[position]=="Computer System Architecture"){
                    subject="COA";
                    semester="4";
                    author="morris mano";
                }
                else if(result[position]=="Database System Concepts"){
                    subject="DBMS";
                    semester="3";
                    author="korth";
                }
                else if(result[position]=="Software Engineering"){
                    subject="oose";
                    semester="4";
                    author="kkagarwal";
                }
                else if(result[position]=="Operating System Concepts"){
                    subject="os";
                    semester="4";
                    author="galvin";
                }
                else if(result[position]=="Signals and Systems"){
                    subject="ss";
                    semester="4";
                    author="oppenheim";
                }
                else if(result[position]=="OP-AMPS and Linear Circuits"){
                    subject="Communication Systems";
                    semester="3";
                    author="Ramakant A. Gayakwad";
                }
                Log.d("hey","hello this idsndke");
//                 Intent intent= new Intent(CustomGVAdapter.this,fetchbuydetails.class);
//                CustomGVAdapter.this.context.startActivity(new Intent(CustomGVAdapter.this.context,fetchbuydetails.class));

            }
        });

        return rowView;
    }

}