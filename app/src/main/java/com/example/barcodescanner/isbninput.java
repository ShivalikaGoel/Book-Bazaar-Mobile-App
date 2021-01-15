package com.example.barcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class isbninput extends AppCompatActivity {

    EditText number;
    Button b;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isbninput);
        b=findViewById(R.id.submit);
        back=findViewById(R.id.back);
        number=findViewById(R.id.num);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String s=number.getText().toString();
                Bundle bun = new Bundle();
                bun.putString("ISBN number", s);
                Intent intent = new Intent(isbninput.this, Main2Activity.class);

                intent.putExtras(bun); //Put your id to your next Intent
                startActivity(intent);



            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(isbninput.this, bookinput.class);

                //Put your id to your next Intent
                startActivity(intent);
            }
        });

    }
}