package com.example.barcodescanner;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private Button login;
    EditText enm;
    EditText pass;
    TextView forgotp;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login= (Button)findViewById(R.id.log);
        enm=(EditText)findViewById(R.id.enm);
        pass=(EditText)findViewById(R.id.password);
        forgotp=(TextView)findViewById(R.id.fp);
        signup=(TextView)findViewById(R.id.signup);
        login.setOnClickListener(new View.OnClickListener()

        {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Login.this,buysellcat.class);
                startActivity(intent);
            }
        });
        forgotp.setOnClickListener(new View.OnClickListener()

        {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Login.this,forgotpass.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener()

        {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Login.this,signupform.class);
                startActivity(intent);
            }
        });

    }
}