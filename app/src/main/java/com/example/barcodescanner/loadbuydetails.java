package com.example.barcodescanner;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
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

public class loadbuydetails extends AppCompatActivity {
    String sem,subject,author;
    TextView cost;
    TextView name;
    TextView edition;
    TextView condition;
    ProgressDialog progressDialog;
    ImageView imageView;
    Button btn;
    GridView gridview;
    public class Holder
    {
        TextView textView;
        ImageView imageView;
    }
    private static LayoutInflater inflater=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadbuydetails);
        gridview = (GridView) findViewById(R.id.cgrid);
        inflater = ( LayoutInflater )this.
               getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        Bundle b = getIntent().getExtras();
//        sem=b.getString("semester");
//        subject=b.getString("subject");
//        author=b.getString("author");
//        Log.d("sem",sem);
//        Log.d("sub",subject);
//        Log.d("auth",author);
//        progressDialog = ProgressDialog.show(loadbuydetails.this, "Please wait..", "Details are being fetched");
//        cost=findViewById(R.id.cost);
//        imageView=findViewById(R.id.img);
//        name=findViewById(R.id.name);
//        btn=findViewById(R.id.btn);
//        edition=findViewById(R.id.edition);
//        condition=findViewById(R.id.condition);
//        new background().execute();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"We have notified the seller..Will get back to you soon",Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(loadbuydetails.this,buysellcat.class);
//                startActivity(intent);
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
                Holder holder=new Holder();
                View rowView;
                rowView = inflater.inflate(R.layout.grid_layout, null);
                JSONArray jsonArray= new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonResult = jsonArray.getJSONObject(i);

                    holder.textView =(TextView) rowView.findViewById(R.id.texts);
                    holder.imageView =(ImageView) rowView.findViewById(R.id.images);
                    holder.textView.setText(jsonResult.getString("cost"));

                    Log.d("---------resultobject-", "object");
                    Log.d("jsonresult", String.valueOf(jsonResult));
                    String s,t,u,v,encodedImage;
//                    s = jsonResult.getString("cost");
//                    t=jsonResult.getString("edition");
//                    u=jsonResult.getString("bookcond");
//                    v=jsonResult.getString("name");
                    encodedImage=jsonResult.getString("picture");
//                    Log.d("hey",s);
//                    cost.setText(s);
//                    name.setText(v);
//                    edition.setText(t);
//                    condition.setText(u);
                    byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    holder.imageView.setImageBitmap(decodedByte);
                  //  imageView.setImageBitmap(decodedByte);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }

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
                        +"&&"+URLEncoder.encode("semester","UTF-8")+"="+URLEncoder.encode(sem,"UTF-8");
                Log.d("heydata",data);
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
}
