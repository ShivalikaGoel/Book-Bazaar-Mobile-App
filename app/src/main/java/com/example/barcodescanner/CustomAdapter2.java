package com.example.barcodescanner;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.ViewHolder> {

    private Context context;
    private List<MyData2> my_data;

    public CustomAdapter2(Context context, List<MyData2> my_data) {
        Log.d("custom Adapter", "in adapter");
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("custom Adapter view ", "view holder");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardcat, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("bind func", "in bind func");

        Log.d("imglink", my_data.get(position).getImage_link());
        //    holder.description.setText("BOOK CONDITION:  "+my_data.get(position).getDescription());
        holder.name.setText(my_data.get(position).getName());
        // holder.name.setText(my_data.get(position).getName());
        //   holder.cost.setText("COST:  "+my_data.get(position).getCost());
        Glide.with(context).load(my_data.get(position).getImage_link()).centerCrop().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        Log.d("size of data", String.valueOf(my_data.size()));

        return my_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView imageView;
        public CardView cv;
        public RelativeLayout rl;
        public ViewHolder(View itemView) {
            super(itemView);
            //  description = (TextView) itemView.findViewById(R.id.description);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            cv=(CardView)itemView.findViewById(R.id.card_view) ;
            rl= (RelativeLayout)itemView.findViewById(R.id.rl);
            //  name=(TextView) itemView.findViewById(R.id.name);
            //  cost=(TextView) itemView.findViewById(R.id.cost);
            rl.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d("clicking","click");
                    int pos = getAdapterPosition();
                   String subject=   my_data.get(pos).getsubject();
                   String author= my_data.get(pos).getBookauth();
                    Intent intent=new Intent(context,buydetails.class);
                    Bundle b=new Bundle();
                    // b.putString("semester",sem);
                    b.putString("subject",subject);
                    b.putString("author",author);
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
