package com.example.barcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class buybookdetails extends AppCompatActivity
{
    String sem,author,name,Branchname;
    EditText auth,subject,semester,branch;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buybookdetails);
//        Button submit;
//        Bundle b=getIntent().getBundleExtra();
//        sem=b.getString();
//        final EditText auth,book;
//        final String[] author = new String[1];
//        final String[] name = new String[1];
//        String a;
        submit=findViewById(R.id.next);
        auth=findViewById(R.id.author);
        subject=findViewById(R.id.name);
        semester=findViewById(R.id.sem);
        branch=findViewById(R.id.branch);
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                author =auth.getText().toString();
                name=subject.getText().toString();
                sem=semester.getText().toString();
                Branchname=branch.getText().toString();
                Log.d("semester",sem);
                Log.d("subject",name);
                Log.d("author",author);
                Log.d("sem",sem);
                Log.d("branch name",Branchname);
                Intent intent=new Intent(buybookdetails.this,buydetails.class);
                Bundle b=new Bundle();
               // b.putString("semester",sem);
                b.putString("subject",name);
                b.putString("author",author);
              //  b.putString("branch",Branchname);
                intent.putExtras(b);
                startActivity(intent);
//                Toast.makeText(".this")

            }
        });
    }
}