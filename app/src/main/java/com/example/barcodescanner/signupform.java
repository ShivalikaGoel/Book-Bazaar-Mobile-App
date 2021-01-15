package com.example.barcodescanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class signupform extends AppCompatActivity {

    EditText name;
    EditText enbm;
    EditText phnum;
    EditText email;
    EditText pass;
    EditText cpass;
    Button sub;

    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupform);
        name=findViewById(R.id.name);
        enbm =findViewById(R.id.enrollnum);
        phnum=findViewById(R.id.phnum);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        cpass=findViewById(R.id.cpass);
        sub= ( Button) findViewById(R.id.sub);

        sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(validate()){
                    String user=name.getText().toString();
                    String enm=enbm.getText().toString();
                    String phone=phnum.getText().toString();
                    String checkemail = email.getText().toString();
                    String password = pass.getText().toString();
                    background bg = new background(signupform.this);
                    bg.execute(user,enm,phone,checkemail, password);
//                    Toast.makeText(signupform.this,"We have recieved your information!",Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(signupform.this,Login.class);
//                    startActivity(intent);
//                    // startActivity(new Intent(RegistrationForm.this, Home.class));
                }
            }

            private boolean validate()
            {
                boolean temp=true;
                String user=name.getText().toString();
                String userid=enbm.getText().toString();
                String phone=phnum.getText().toString();
                String checkemail = email.getText().toString();
                String p=pass.getText().toString();
                String cp=cpass.getText().toString();
                if(user.length()==0)
                {
                    Toast.makeText(signupform.this,"Name Cannot Be Empty",Toast.LENGTH_SHORT).show();
                    temp=false;
                }
                else if(userid.length()==0)
                {
                    Toast.makeText(signupform.this,"User Name Cannot Be Empty",Toast.LENGTH_SHORT).show();
                    temp=false;
                }
                else if(phone.length()!=10)
                {
                    Toast.makeText(signupform.this,"Invalid Phone Number",Toast.LENGTH_SHORT).show();
                    temp=false;
                }

                else if(!EMAIL_ADDRESS_PATTERN.matcher(checkemail).matches()){
                    Toast.makeText(signupform.this,"Invalid Email Address",Toast.LENGTH_SHORT).show();
                    temp=false;
                }
                else if(pass.length()==0)
                {
                    Toast.makeText(signupform.this,"Enter Password",Toast.LENGTH_SHORT).show();
                    temp=false;
                }
                else if(!p.equals(cp)){
                    Toast.makeText(signupform.this,"Password Not matching",Toast.LENGTH_SHORT).show();
                    temp=false;
                }


                return temp;


            }

        });
    }
}