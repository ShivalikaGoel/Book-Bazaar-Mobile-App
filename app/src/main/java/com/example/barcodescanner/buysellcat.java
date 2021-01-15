package com.example.barcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class buysellcat extends AppCompatActivity {
    ImageView buy,sell,cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buysellcat);
        buy= findViewById(R.id.buy);
        sell=findViewById(R.id.sell);
        cat=findViewById(R.id.cat);
        buy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent(buysellcat.this,buybookdetails.class);
                startActivity(intent);



            }
        });

        sell.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View veiw)
            {
                Intent intent=new Intent(buysellcat.this,bookinput.class);
                startActivity(intent);

            }


        });
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(buysellcat.this,catactfinal.class);
                startActivity(intent);

            }
        });

    }
}