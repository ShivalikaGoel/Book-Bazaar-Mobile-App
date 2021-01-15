package com.example.barcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class bookinput extends AppCompatActivity
{
    Button scan,num;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookinput);

        scan =  findViewById(R.id.btn);//get id of button 1
        num =  findViewById(R.id.btn2);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bookinput.this, MainActivity.class);
                startActivity(intent);
                finish();
//                Toast.makeText(getApplicationContext(), "Simple Button 1", Toast.LENGTH_LONG).show();//display the text of button1
            }
        });

        num.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bookinput.this, isbninput.class);
                startActivity(intent);
                finish();
            }
        });






    }
}