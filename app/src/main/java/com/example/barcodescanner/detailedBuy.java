package com.example.barcodescanner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class detailedBuy extends AppCompatActivity {
    String subject,author,cost,edition,description,imgpath,name,enroll,sellerph,sellername;
    TextView sub,auth,cst,ed,des,nam,sellname,rating;
    ImageView img;
    Button buybutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_buy);
        Bundle b = getIntent().getExtras();
        subject=b.getString("subject");
        author=b.getString("author");
        cost=b.getString("cost");
        edition=b.getString("edition");
        description=b.getString("description");
        imgpath=b.getString("imgpath");
        name=b.getString("name");
        enroll=b.getString("enrollnum");
        //enroll="52627338";
        Log.d("detailssssss",subject+author+cost+edition+imgpath+description);
        sub= findViewById(R.id.subject);
        auth=findViewById(R.id.author);
        cst= findViewById(R.id.cost);
        ed=findViewById(R.id.edition);
        des= findViewById(R.id.desc);
        img=findViewById(R.id.img);
        nam= findViewById(R.id.bname);
        sellname=findViewById(R.id.sellname);
        rating=findViewById(R.id.sellrate);
        buybutton=findViewById(R.id.buy);
        sub.setText(subject);
        auth.setText(author);
        cst.setText(cost);
        ed.setText(edition);
        des.setText(description);
        nam.setText(name);
        Glide.with(this).load(imgpath).centerCrop().into(img);
        new background().execute(enroll);
        buybutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent(detailedBuy.this,sellerdetails.class);
                Bundle b=new Bundle();
                b.putString("name",sellername);
                b.putString("phnum",sellerph);
                Log.d("phnumis",sellerph);
                intent.putExtras(b);
                startActivity(intent);



            }
        });
    }
    public class background extends AsyncTask<String, Void,String> {

        AlertDialog dialog;
        Context context;
        public Boolean login = false;
        @Override
        protected void onPreExecute() {

        }
        @Override
        protected void onPostExecute(String s) {
            if(s!=null)
                Log.d("stringsssss",s);
        }
        @Override
        protected String doInBackground(String... voids) {
            String result = "";
            String enrollnum = voids[0];
            String connstr = "http://159.65.158.141/fetchprofile.php";

            try{
                Log.d("intryhere","detailbuypage");
                URL url = new URL(connstr);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);


                OutputStream ops = http.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                Log.d("enrollnumpassed",enrollnum);
                String data = URLEncoder.encode("enrollnum","UTF-8")+"="+URLEncoder.encode(enrollnum,"UTF-8");
                Log.d("hey",name);
                writer.write(data);
                writer.flush();
                writer.close();
                ops.close();

                InputStream ips = http.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
                String line ="";
                while ((line = reader.readLine()) != null)
                {
                    result += line;
                }
                JSONObject j= new JSONObject(result);
                Log.d("resulthererrrrr", String.valueOf(j.length()));
                rating.setText(j.getString("rating"));
                sellname.setText(j.getString("name"));
                sellerph=j.getString("phnum");
                sellername=j.getString("name");
                reader.close();
                ips.close();
                http.disconnect();
                return result;

            } catch (MalformedURLException e) {
                Log.d("hytttt",e.getMessage());
                result = e.getMessage();
            } catch (IOException e) {
                Log.d("ddddd",e.getMessage());
                result = e.getMessage();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}
