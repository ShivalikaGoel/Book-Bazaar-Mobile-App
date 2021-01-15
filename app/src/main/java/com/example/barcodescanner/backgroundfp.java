package com.example.barcodescanner;

import android.app.AlertDialog;
import android.content.Context;
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

public class backgroundfp extends AsyncTask<String, Void,String> {

    AlertDialog dialog;
    Context context;
    public Boolean login = false;
    public backgroundfp(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String password = voids[0];
        Log.d("passwordkey", password);
        String enrollnum = voids[1];
        Log.d("enrollmentnumber",enrollnum);
        String connstr = "http://159.65.158.141/password1.php";
        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);
            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("enrollnum","UTF-8")+"="+URLEncoder.encode(enrollnum,"UTF-8")
                    +"&&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
            Log.d("datastring",data);
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
            Log.d("resultttt",result);
            if(result.contains(".com")){
                connstr = "http://159.65.158.141/sendEmail.php";
                try {
                    URL ur = new URL(connstr);
                    HttpURLConnection htt = (HttpURLConnection) ur.openConnection();
                    htt.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    htt.setRequestMethod("POST");
                    htt.setDoInput(true);
                    htt.setDoOutput(true);
                    OutputStream op = htt.getOutputStream();
                    BufferedWriter writers = new BufferedWriter(new OutputStreamWriter(op, "UTF-8"));
                    String dat = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(result,"UTF-8")
                            +"&&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                    //Log.d("emaildetails",dat);
                    writers.write(dat);
                    writers.flush();
                    writers.close();
                    op.close();

                    InputStream ip = htt.getInputStream();
                    BufferedReader read = new BufferedReader(new InputStreamReader(ip,"ISO-8859-1"));
                    String lines ="";
                    // result = "";
                    while ((lines = read.readLine()) != null)
                    {
                        result += lines;
                    }
                    Log.d("emails",result);
                    read.close();
                    ip.close();
                    htt.disconnect();
                    Log.d("email",result);
                }
                catch (MalformedURLException e) {
                    Log.d("waba",e.getMessage());
                    result = e.getMessage();
                } catch (IOException e) {
                    Log.d("eeee",e.getMessage());
                    result = e.getMessage();
                }

            }
            return result;
        }
        catch (MalformedURLException e) {
            Log.d("hytttt",e.getMessage());
            result = e.getMessage();
        } catch (IOException e) {
            Log.d("ddddd",e.getMessage());
            result = e.getMessage();
        }
        return result;
    }
}