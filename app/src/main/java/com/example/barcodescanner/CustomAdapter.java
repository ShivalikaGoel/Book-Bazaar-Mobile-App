package com.example.barcodescanner;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

import static android.graphics.Typeface.BOLD;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<MyData> my_data;
    public String subjects;
    public String authors;

    public CustomAdapter(Context context, List<MyData> my_data , String subject , String author) {
        Log.d("custom Adapter", "in adapter");
        this.context = context;
        this.my_data = my_data;
        this.authors= author;
        this.subjects= subject;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("custom Adapter view ", "view holder");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("bind func" , "in bind func");
        Log.d("description",my_data.get(position).getDescription());
        Log.d("imglink",my_data.get(position).getImage_link());

        SpannableStringBuilder str = new SpannableStringBuilder("BOOK CONDITION:  ").append(my_data.get(position).getDescription());
        holder.description.setText(str);
       // holder.description.setText("BOOK CONDITION:  "+my_data.get(position).getDescription());
        holder.edition.setText("EDITION:  "+my_data.get(position).getEdition());
        // holder.name.setText(my_data.get(position).getName());
        holder.cost.setText("COST:  "+my_data.get(position).getCost());
        Glide.with(context).load(my_data.get(position).getImage_link()).centerCrop().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        Log.d("size of data",String.valueOf(my_data.size()));

        return my_data.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder
    {

        public TextView description;
        public TextView edition;
        //  public TextView name;
        public TextView cost;
        public ImageView imageView;
        public RelativeLayout rl;

        public ViewHolder(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.description);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            edition=(TextView) itemView.findViewById(R.id.edition);
            //  name=(TextView) itemView.findViewById(R.id.name);
            cost=(TextView) itemView.findViewById(R.id.cost);
            rl= (RelativeLayout)itemView.findViewById(R.id.rl);
            rl.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d("clicking","click");
                    int pos = getAdapterPosition();
                    String subject=  subjects;
                    String author= authors;
                    String cost=   my_data.get(pos).getCost();
                    String edition= my_data.get(pos).getEdition();
                    String description= my_data.get(pos).getDescription();
                    String imgpath=my_data.get(pos).getImage_link();
                    String name= my_data.get(pos).getName();
                    String enrollnum= my_data.get(pos).getEnrollnum();
                    Intent intent=new Intent(context,detailedBuy.class);
                    Bundle b=new Bundle();
                    // b.putString("semester",sem);
                    b.putString("subject",subject);
                    b.putString("author",author);
                    b.putString("cost",cost);
                    b.putString("edition",edition);
                    b.putString("description",description);
                    b.putString("imgpath",imgpath);
                    b.putString("name",name);
                    b.putString("enrollnum",enrollnum);
                    //  b.putString("branch",Branchname);
                    Log.d("subjecttt",subject);
                    Log.d("authorrrr",author);
                    intent.putExtras(b);
                    context.startActivity(intent);

                    // context.startActivity(new Intent(context,forgotpass.class));
                }

            });
        }

        }
    }
