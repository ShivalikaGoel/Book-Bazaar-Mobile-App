package com.example.barcodescanner;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
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

public class fetchbuydetails extends AppCompatActivity {
    String sem,subject,author;
    TextView cost;
    TextView name;
    TextView edition;
    TextView condition;
    ProgressDialog progressDialog;
    ImageView imageView;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetchbuydetails);
        Bundle b = getIntent().getExtras();
      //  sem=b.getString("semester");
        subject=b.getString("subject");
        author=b.getString("author");
//        sem="1";
//        subject="ada";
//        author="riya";
   //     Log.d("sem",sem);
        Log.d("sub",subject);
        Log.d("auth",author);
        progressDialog = ProgressDialog.show(fetchbuydetails.this,
                "Please wait..",
                "Details are being fetched");
        cost=findViewById(R.id.cost);
        imageView=findViewById(R.id.img);
        name=findViewById(R.id.name);
        btn=findViewById(R.id.btn);
        edition=findViewById(R.id.edition);
        condition=findViewById(R.id.condition);
        new background().execute();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"We have notified the seller..Will get back to you soon",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(fetchbuydetails.this,buysellcat.class);
                startActivity(intent);
            }
        });


    }
    public class background extends AsyncTask<String, Void,String>
    {
        @Override
        protected void onPreExecute() {

        }
        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();

            try {

              //  Log.d("heyyyy",result);
                JSONArray jsonArray= new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonResult = jsonArray.getJSONObject(i);
                    Log.d("---------resultobject-", "object");
                    Log.d("jsonresult", String.valueOf(jsonResult));
                //JSONObject jsonResult = new JSONObject(result);
                    String s,t,u,v,encodedImage;
                    s = jsonResult.getString("cost");
                    t=jsonResult.getString("edition");
                    u=jsonResult.getString("bookcond");
                    v=jsonResult.getString("name");

                    encodedImage=jsonResult.getString("picture");
                    Log.d("hey",s);
                    cost.setText(s);
                    name.setText(v);
                    edition.setText(t);
                    condition.setText(u);
                    byte[] decodestring= Base64.decode(encodedImage,Base64.DEFAULT);
                    byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    imageView.setImageBitmap(decodedByte);
                }
//                JSONArray jsonArray = new JSONArray(result);
//                String jsonString = result;
//                JSONObject jsonResult = new JSONObject(result);
//                String s,t,u,v,encodedImage;
//                s = jsonResult.getString("cost");
//                t=jsonResult.getString("edition");
//                u=jsonResult.getString("bookcond");
//                v=jsonResult.getString("name");
//
//                encodedImage=jsonResult.getString("picture");
//                Log.d("hey",s);
//                cost.setText(s);
//                name.setText(v);
//                edition.setText(t);
//                condition.setText(u);
//                byte[] decodestring= Base64.decode(encodedImage,Base64.DEFAULT);
//                byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
//                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                imageView.setImageBitmap(decodedByte);
//                //   name.setText("check");
////                JSONArray data = jsonResult.getJSONArray("response");
////                if(data != null) {
////                    String[] names = new String[data.length()];
////                    String[] birthdays = new String[data.length()];
////                    for(int i = 0 ; i < data.length() ; i++) {
////                        birthdays[i] = data.getString("birthday");
////                        names[i] = data.getString("name");
////                    }
////                }
////               data.getString(0);
//

//                Log.d("waba", result);
//                JSONObject resultObject = new JSONObject(result);
//                JSONArray bookArray = resultObject.getJSONArray("response");
//                JSONObject bookObject = bookArray.getJSONObject(0);
//                JSONObject volumeObject = bookObject.getJSONObject("volumeInfo");
//
//                s = volumeObject.getString("title");
////                    if(s!=null) {
//                pub = volumeObject.getString("publisher");
//                title.setText(s);
//                publisher.setText(pub);
////                        d = volumeObject.getString("description");
////                        desc.setText(d);
//                try {
//                    Log.d("TITLE: ", volumeObject.getString("title"));
//
//
//                } catch (JSONException jse) {
//                    Log.d("TITLE: ", volumeObject.getString("title"));
//                    jse.printStackTrace();
//                }
//                StringBuilder authorBuild = new StringBuilder("");
////                auth=
//                try {
//
//                    JSONArray authorArray = volumeObject.getJSONArray("authors");
//                    for (int a = 0; a < authorArray.length(); a++)
//                    {
////                                if(authorArray.get(a)!='['&&authorArray.get(a)!=']')
////                                {
//                        if (a > 0)
//                            authorBuild.append(", ");
//                        authorBuild.append(authorArray.getString(a));
////                                }
//                    }
//                    auth = volumeObject.getString("authors");
//                    author.setText(auth);
////                    Log.d("TITLE: ", volumeObject.getString("authors"));
//                } catch (JSONException jse) {
//                    jse.printStackTrace();
//                }
////                    }
////                else
//                {
                Log.d("hey","i am ");

//
//
//
//                }

            } catch (Exception e)
            {
                e.printStackTrace();


            }
//            ArrayList<String> User_List = new ArrayList<String>();
//
//
//            try
//            {
//                JSONArray jArray = new JSONArray(s);
//                for (int i = 0; i < jArray.length(); i++)
//                {
//                    JSONObject json_data = jArray.getJSONObject(i);
//                    User_List.add(json_data.getString("response"));
//                }
//
//            }
//            catch (Exception e)
//            {
//                Log.e("hey", "" + e);
//            }

        }
        @Override
        protected String doInBackground(String... voids) {
            String result = "";
            String connstr = "http://159.65.158.141/UploadExample/fetchbuydetails2.php";

            try{
                URL url = new URL(connstr);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);


                OutputStream ops = http.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));

                String data = URLEncoder.encode("author","UTF-8")+"="+URLEncoder.encode(author,"UTF-8")
                        +"&&"+URLEncoder.encode("subject","UTF-8")+"="+URLEncoder.encode(subject,"UTF-8")
                        +"&&"+URLEncoder.encode("semester","UTF-8")+"="+URLEncoder.encode("shut","UTF-8");
                Log.d("heydata",data);
                // String data = URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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
                reader.close();
                ips.close();
                http.disconnect();

               // Log.d("result",result);
                return result;

            } catch (MalformedURLException e) {
                Log.d("hytttt",e.getMessage());
                result = e.getMessage();
            } catch (IOException e) {
                Log.d("ddddd",e.getMessage());
                result = e.getMessage();
            }
            return result;
        }
    }
}

//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.TextView;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//
//public class fetchbuydetails extends AppCompatActivity {
//    String sem,subject,author;
//    TextView ans;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fetchbuydetails);
//        Bundle b = getIntent().getExtras();
//        sem=b.getString("semester");
//        subject=b.getString("subject");
//        author=b.getString("author");
//        Log.d("sem",sem);
//        Log.d("sub",subject);
//        Log.d("auth",author);
//
//        ans=findViewById(R.id.abc);
//        new background().execute();
//
//
//    }
//    public class background extends AsyncTask<String, Void,String>
//    {
//        @Override
//        protected void onPreExecute() {
//
//        }
//        @Override
//        protected void onPostExecute(String result) {
//
//            try {
//                Log.d("hey",result);
////                JSONArray jsonArray = new JSONArray(result);
////                String jsonString = result;
//                JSONObject jsonResult = new JSONObject(result);
//                String s;
//                s = jsonResult.getString("cost");
//                Log.d("hey",s);
//                ans.setText(s);
//
//
////                JSONArray data = jsonResult.getJSONArray("response");
////                if(data != null) {
////                    String[] names = new String[data.length()];
////                    String[] birthdays = new String[data.length()];
////                    for(int i = 0 ; i < data.length() ; i++) {
////                        birthdays[i] = data.getString("birthday");
////                        names[i] = data.getString("name");
////                    }
////                }
////               data.getString(0);
//
//
////                Log.d("waba", result);
////                JSONObject resultObject = new JSONObject(result);
////                JSONArray bookArray = resultObject.getJSONArray("response");
////                JSONObject bookObject = bookArray.getJSONObject(0);
////                JSONObject volumeObject = bookObject.getJSONObject("volumeInfo");
////
////                s = volumeObject.getString("title");
//////                    if(s!=null) {
////                pub = volumeObject.getString("publisher");
////                title.setText(s);
////                publisher.setText(pub);
//////                        d = volumeObject.getString("description");
//////                        desc.setText(d);
////                try {
////                    Log.d("TITLE: ", volumeObject.getString("title"));
////
////
////                } catch (JSONException jse) {
////                    Log.d("TITLE: ", volumeObject.getString("title"));
////                    jse.printStackTrace();
////                }
////                StringBuilder authorBuild = new StringBuilder("");
//////                auth=
////                try {
////
////                    JSONArray authorArray = volumeObject.getJSONArray("authors");
////                    for (int a = 0; a < authorArray.length(); a++)
////                    {
//////                                if(authorArray.get(a)!='['&&authorArray.get(a)!=']')
//////                                {
////                        if (a > 0)
////                            authorBuild.append(", ");
////                        authorBuild.append(authorArray.getString(a));
//////                                }
////                    }
////                    auth = volumeObject.getString("authors");
////                    author.setText(auth);
//////                    Log.d("TITLE: ", volumeObject.getString("authors"));
////                } catch (JSONException jse) {
////                    jse.printStackTrace();
////                }
//////                    }
//////                else
////                {
//                Log.d("hey","i am ");
//
////
////
////
////                }
//
//            } catch (Exception e)
//            {
//                e.printStackTrace();
//
//
//            }
////            ArrayList<String> User_List = new ArrayList<String>();
////
////
////            try
////            {
////                JSONArray jArray = new JSONArray(s);
////                for (int i = 0; i < jArray.length(); i++)
////                {
////                    JSONObject json_data = jArray.getJSONObject(i);
////                    User_List.add(json_data.getString("response"));
////                }
////
////            }
////            catch (Exception e)
////            {
////                Log.e("hey", "" + e);
////            }
//
//        }
//        @Override
//        protected String doInBackground(String... voids) {
//            String result = "";
//            String connstr = "http://159.65.158.141/UploadExample/fetchbuydetails2.php";
//
//            try{
//                URL url = new URL(connstr);
//                HttpURLConnection http = (HttpURLConnection) url.openConnection();
//                http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//                http.setRequestMethod("POST");
//                http.setDoInput(true);
//                http.setDoOutput(true);
//
//
//                OutputStream ops = http.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
//
//                String data = URLEncoder.encode("author","UTF-8")+"="+URLEncoder.encode(author,"UTF-8")
//                        +"&&"+URLEncoder.encode("subject","UTF-8")+"="+URLEncoder.encode(subject,"UTF-8")
//                        +"&&"+URLEncoder.encode("semester","UTF-8")+"="+URLEncoder.encode(sem,"UTF-8");
////                Log.d("hey",name);
//                // String data = URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
//                writer.write(data);
//                writer.flush();
//                writer.close();
//                ops.close();
//
//                InputStream ips = http.getInputStream();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
//                String line ="";
//                while ((line = reader.readLine()) != null)
//                {
//                    result += line;
//                }
//                reader.close();
//                ips.close();
//                http.disconnect();
//
//                Log.d("result",result);
//                return result;
//
//            } catch (MalformedURLException e) {
//                Log.d("hytttt",e.getMessage());
//                result = e.getMessage();
//            } catch (IOException e) {
//                Log.d("ddddd",e.getMessage());
//                result = e.getMessage();
//            }
//            return result;
//        }
//    }
//}