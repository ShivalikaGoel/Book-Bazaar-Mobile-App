package com.example.barcodescanner;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class forgotpass extends AppCompatActivity {
    EditText enmbr;
    TextView back;
    ImageButton sendLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_forgotpass);
        sendLink = (ImageButton) findViewById(R.id.SendLink);
        enmbr=(EditText)findViewById(R.id.enm);
        Log.d("enrollnumbers", String.valueOf(enmbr));
        back= (TextView)findViewById(R.id.back);
        sendLink.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                String str = random();
                Log.d("stringsss",str);
                boolean ans= validate(str);
                Log.d("stringss2",str);

            }
        });
        back.setOnClickListener(new View.OnClickListener()

        {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(forgotpass.this,Login.class);
                startActivity(intent);
            }
        });


    }
    public static String random() {
        final String characters= "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder randomStringBuilder = new StringBuilder();
        int i=15;
        while(i>0){
            Random random = new Random();
            randomStringBuilder.append(characters.charAt(random.nextInt(characters.length())));
            i--;
        }
        return randomStringBuilder.toString();
    }
    private boolean validate(String str){
        Log.d("riyaval1","validate");
        boolean temp=true;
        String enmb=enmbr.getText().toString();
        Log.d("enumberss",enmb);
        if(enmb.length()==0)
        {
            Toast.makeText(forgotpass.this,"User Name Cannot Be Empty",Toast.LENGTH_SHORT).show();
            Log.d("riyaerror","validate");
            temp=false;
        }
        else{
            Log.d("riyanoerror","validate");
            backgroundfp bg= new backgroundfp(forgotpass.this);
            bg.execute(str,enmb);

        }
        return temp;
    }
}