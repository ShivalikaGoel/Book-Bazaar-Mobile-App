package com.example.barcodescanner;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
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

public class Main2Activity extends AppCompatActivity
{


    EditText author;
    EditText title;
    EditText publisher,sub,sem;
    Button btn;
    String s,auth;
    String pub;
    String isbn;
    String subject,semester;
    String authfinal="";

    ProgressDialog progressDialog;

    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        author = findViewById(R.id.author);
        title = findViewById(R.id.title);
        publisher = findViewById(R.id.desc);
        sub=findViewById(R.id.subject);
        btn=findViewById(R.id.next);
        sem=findViewById(R.id.sem);
        Bundle b = getIntent().getExtras();
        isbn=b.getString("ISBN number");
        Log.d("number",Integer.toString(i));
        i++;
        progressDialog = ProgressDialog.show(Main2Activity.this,
                "Please wait..",
                "Details are being fetched");
        String value =""; // or other values
        if (b != null)
            value = b.getString("ISBN number");
        Log.d("isbn",value);
//        new GetBookInfo().execute(qrCodes.valueAt(0).displayValue);
        new GetBookInfo().execute(value);
//        new back().execute();



    }

    private class GetBookInfo extends AsyncTask<String, String, String> //creates another thread
    {


        protected String doInBackground(String... bookURL)
        {

            publishProgress("Loading...");
            StringBuilder bookBuilder = new StringBuilder();
//search urls
            HttpClient bookClient = new DefaultHttpClient();

            try {

                //get the data
                HttpGet bookGet = new HttpGet("https://www.googleapis.com/books/v1/volumes?" +
                        "q=isbn:" + bookURL[0] + "&key=AIzaSyCvvNtncgGoz_G4cdNiL9oHCx08OXqKNKI");
                bookClient.getConnectionManager().getSchemeRegistry().register(
                        new Scheme("https", SSLSocketFactory.getSocketFactory(), 443)
                );
                HttpResponse bookResponse = bookClient.execute(bookGet);
                StatusLine bookSearchStatus = bookResponse.getStatusLine();
                if (bookSearchStatus.getStatusCode() == 200) {

                    //we have a result
                    HttpEntity bookEntity = bookResponse.getEntity();
                    InputStream bookContent = bookEntity.getContent();
                    InputStreamReader bookInput = new InputStreamReader(bookContent);
                    BufferedReader bookReader = new BufferedReader(bookInput);
                    String lineIn;
                    while ((lineIn = bookReader.readLine()) != null)
                    {
                        bookBuilder.append(lineIn);
                    }
                    Log.d("wabalaba", "lol");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return bookBuilder.toString();

//request book info
        }


        protected void onPostExecute(String result) {

            try {


                Log.d("waba", result);
                JSONObject resultObject = new JSONObject(result);
                JSONArray bookArray = resultObject.getJSONArray("items");
                JSONObject bookObject = bookArray.getJSONObject(0);
                JSONObject volumeObject = bookObject.getJSONObject("volumeInfo");

                s = volumeObject.getString("title");
//                    if(s!=null) {
                pub = volumeObject.getString("publisher");
                title.setText(s);
                publisher.setText(pub);
//                        d = volumeObject.getString("description");
//                        desc.setText(d);
                try {
                    Log.d("TITLE: ", volumeObject.getString("title"));


                } catch (JSONException jse) {
                    Log.d("TITLE: ", volumeObject.getString("title"));
                    jse.printStackTrace();
                }
                StringBuilder authorBuild = new StringBuilder("");
//                auth=
                try {

                    JSONArray authorArray = volumeObject.getJSONArray("authors");
                    for (int a = 0; a < authorArray.length(); a++)
                    {
//                                if(authorArray.get(a)!='['&&authorArray.get(a)!=']')
//                                {
                        if (a > 0)
                            authorBuild.append(", ");
                        authorBuild.append(authorArray.getString(a));
//                                }
                    }
                    auth = volumeObject.getString("authors");
                    author.setText(auth);
//                    Log.d("TITLE: ", volumeObject.getString("authors"));
                } catch (JSONException jse) {
                    jse.printStackTrace();
                }
//                    }
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
            progressDialog.dismiss();
//            Log.d("TITLE: ", s);
            btn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View veiw)
                {
                    if(s==null)
                        s=title.getText().toString();
                    if(auth==null)
                        auth=author.getText().toString();
                    if (pub==null)
                        pub=publisher.getText().toString();
                    if(subject==null)
                        subject=sub.getText().toString();
                    if(semester==null)
                        semester=sem.getText().toString();

                    for(int i=0;i<auth.length();i++)
                    {
                        if(auth.charAt(i)=='['||auth.charAt(i)==']')
                        {
                            i++;

                        }
                        else
                        {
                            authfinal+=auth.charAt(i);

                        }
                    }
                    new background().execute();
                    Log.d("hey","final");

//                    Bundle b=new Bundle();
////                    b.putString("ISBN number", qrCodes.valueAt(0).displayValue);
//                    //Your id
//                    b.putString("Book",s);
//                    b.putString("Author",auth);
//                    b.putString("Publisher",pub);
//                    b.putString("description",d);



//                    intent.putExtras(b); //Put your id to your next Intent





//                    Intent intent=new Intent(Main2Activity.this,sellinput.class);
//                    intent.putExtra(b);
//                    startActivity(intent);

                }


            });


        }
        @Override
        protected void onPreExecute()
        {

        }
    }


    public class background extends AsyncTask <String, Void,String>
    {
        @Override
        protected void onPreExecute()
        {
            Log.d("hey","in pre execute");
        }
        @Override
        protected void onPostExecute(String s)
        {
            Toast.makeText(getApplicationContext(),"Details Saved..",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Main2Activity.this, sellinput.class);
            Bundle b=new Bundle();
            b.putString("isbn",isbn);
            intent.putExtras(b);
            startActivity(intent);
        }
        @Override
        protected String doInBackground(String... voids)
        {
            Log.d("hey","do in backg");
            String result = "";
            String enrollnum = "010010";
            String connstr = "http://159.65.158.141/autofill.php";

            try{
                URL url = new URL(connstr);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);


                OutputStream ops = http.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));

                String data = URLEncoder.encode("enrollnum","UTF-8")+"="+URLEncoder.encode(enrollnum,"UTF-8")
                        +"&&"+URLEncoder.encode("bookisbn","UTF-8")+"="+URLEncoder.encode(isbn,"UTF-8")
                        +"&&"+ URLEncoder.encode("bookname","UTF-8")+"="+URLEncoder.encode(s,"UTF-8")
                        +"&&"+URLEncoder.encode("bookauth","UTF-8")+"="+URLEncoder.encode(authfinal,"UTF-8")
                        +"&&"+URLEncoder.encode("bookpub","UTF-8")+"="+URLEncoder.encode(pub,"UTF-8")
                        +"&&"+URLEncoder.encode("subject","UTF-8")+"="+URLEncoder.encode(subject)
                        +"&&"+URLEncoder.encode("semester","UTF-8")+"="+URLEncoder.encode(semester);
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
                Log.d("resultt",result);
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


    public class back extends AsyncTask <String, Void,String>
    {


        @Override
        protected void onPreExecute()
        {
            Log.d("hey","in pre execute");
        }
        @Override
        protected void onPostExecute(String result)
        {
            JSONObject jsonResult = null;
            try {
                jsonResult = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String s="",t="",u="",v="",w="";
            try {
                s = jsonResult.getString("bookname");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                t=jsonResult.getString("bookauthor");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                u=jsonResult.getString("pub");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                v=jsonResult.getString("subject");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                w=jsonResult.getString("semester");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            title.setText(s);
            author.setText(v);
            publisher.setText(t);
            publisher.setText(u);
            sub.setText(s);
            sem.setText(v);

        }
        @Override
        protected String doInBackground(String... voids)
        {
            Log.d("hey","do in backg");
            String result = "";
//            String name = voids[0];
            String enrollnum = "010010";
//            String phnum= voids[2];
//            String email= voids[3];
//            String password=voids[4];
            String connstr = "http://159.65.158.141/UploadExample/getrequest.php";

            try{
                URL url = new URL(connstr);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);


                OutputStream ops = http.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));

                String data = URLEncoder.encode("isbn","UTF-8")+"="+URLEncoder.encode(isbn,"UTF-8");
//                Log.d("hey",name);
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
                Log.d("resultt",result);
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