package com.example.barcodescanner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

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


public class background extends AsyncTask <String, Void,String> {

    AlertDialog dialog;
    Context context;
    public Boolean login = false;
    public background(Context context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Login Status");
    }
    @Override
    protected void onPostExecute(String s) {
        dialog.setMessage(s);
        dialog.show();
        if(s.contains("http://159.65.158.141/auth.php"))
        {
            Intent intent_name = new Intent();
            intent_name.setClass(context.getApplicationContext(),buysellcat.class);
            context.startActivity(intent_name);
        }

        else if(s.equals("sold")){
            Intent intent_name = new Intent();
            intent_name.setClass(context.getApplicationContext(),buysellcat.class);
            context.startActivity(intent_name);
        }
        else if(s.equals("false")){
            Intent intent_name = new Intent();
            intent_name.setClass(context.getApplicationContext(),MainActivity.class);
            context.startActivity(intent_name);
        }

    }
    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String name = voids[0];
        String enrollnum = voids[1];
        String phnum= voids[2];
        String email= voids[3];
        String password=voids[4];
        String connstr = "http://159.65.158.141/connect.php";

        try{
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);


            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));

            String data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")
                    +"&&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")
                    +"&&"+URLEncoder.encode("enrollnum","UTF-8")+"="+URLEncoder.encode(enrollnum,"UTF-8")
                    +"&&"+URLEncoder.encode("phnum","UTF-8")+"="+URLEncoder.encode(phnum,"UTF-8")
                    +"&&"+URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");
            Log.d("hey",name);
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