package com.example.barcodescanner;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class sellerdetails extends AppCompatActivity {
    String name,phnum;
    public static final int REQUEST_CALL=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerdetails);
        Bundle b = getIntent().getExtras();
        name= b.getString("name");
        phnum=b.getString("phnum");
        phnum= "9818287260";
        final ImageView imgCall= findViewById(R.id.phone);
        final ImageView watsapp= findViewById(R.id.watsapp);
        final TextView nametext= findViewById(R.id.name);
        final TextView phnumtext= findViewById(R.id.phnum);
        nametext.setText(name);
        phnumtext.setText(phnum);

        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                imgCall.startAnimation(animFadein);
                makephonecall();

            }
        });
        watsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri= Uri.parse("smsto:"+ phnum);
                Intent i= new Intent(Intent.ACTION_SENDTO,uri);
                i.setPackage("com.whatsapp");
                startActivity(i);

            }
        });


    }

    private void makephonecall(){
        if(ContextCompat.checkSelfPermission(sellerdetails.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(sellerdetails.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
        }
        else{
            String dial= "tel:"+phnum;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makephonecall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
