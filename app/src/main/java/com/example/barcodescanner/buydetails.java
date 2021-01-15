package com.example.barcodescanner;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class buydetails extends AppCompatActivity {
    String subject,author;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CustomAdapter adapter;
    public List<MyData> data_list;
    public List<MyData> d_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle b = getIntent().getExtras();

        subject=b.getString("subject");
        author=b.getString("author");
        Log.d("buydetailauthor",author);
        Log.d("buydetailssubject",subject);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buydetails);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        data_list = new ArrayList<>();
        d_list=new ArrayList<>();
        Log.d("b4 fcall", "funccall");
        load_data_from_server(0);
        Log.d("after fcall", "funccalldone");
        if(d_list.isEmpty())
            Log.d("emptyyyy","elist");
        gridLayoutManager = new GridLayoutManager(this, 2);


    }
    public void setlist(List<MyData>data){
//        if(!data.isEmpty())
//            Log.d("first", data.get(1).getImage_link());
        d_list=data;
        // Log.d("second", d_list.get(3).getImage_link());
        recyclerView.setLayoutManager(new LinearLayoutManager(buydetails.this));

        adapter = new CustomAdapter(buydetails.this, d_list, subject,author);

        recyclerView.setAdapter(adapter);

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                Log.d("aftersetadapter", d_list.get(1).getImage_link());
//                Log.d("sizeinside",String.valueOf(d_list.size()));
//
//                if (gridLayoutManager.findLastCompletelyVisibleItemPosition() == d_list.size() - 1) {
//                    load_data_from_server(d_list.get(d_list.size() - 1).getId());
//                }
//
//            }
//        });
    }

    private void load_data_from_server(final int id)
    {
        Log.d("check", "function");
        new background().execute(id);
    }
    class background extends AsyncTask<Integer, Void, List<MyData>> {


        protected void onPostExecute(List<MyData>data) {
            super.onPostExecute(data);
            if(data.isEmpty())
                Log.d("dataList empty","empty");
            Log.d("postexe","exe");
            setlist(data);

        }
        @Override
        protected List<MyData> doInBackground(Integer... integers) {
            Log.d("doinbg", "done");
            String result = "";
            // int id=64;
            // int id= params[0];
            int id= integers[0];
            Log.d("id in doibbg" , String.valueOf(id));
            String connstr = "http://159.65.158.141/UploadExample/tryfetchbuydetails.php";
            try {
                Log.d("try", "trydone");
                URL url = new URL(connstr);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);
                OutputStream ops = http.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
                String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(id), "UTF-8")
                        +"&&"+URLEncoder.encode("author","UTF-8")+"="+URLEncoder.encode(author,"UTF-8")
                        +"&&"+URLEncoder.encode("subject","UTF-8")+"="+URLEncoder.encode(subject,"UTF-8");
                Log.d("heydata", data);
                writer.write(data);
                writer.flush();
                writer.close();
                ops.close();

                InputStream ips = http.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "ISO-8859-1"));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                Log.d("resulttttt", result);
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonResult = jsonArray.getJSONObject(i);
                    Log.d("---------resultobject-", "object");
                    Log.d("jsonresult", String.valueOf(jsonResult));
                    Log.d("id", jsonResult.getString("id"));
                    Log.d("bookcond:", jsonResult.getString("bookcondition"));
                    Log.d("imagesssssss", jsonResult.getString("filepath"));
                    id= jsonResult.getInt("id");
                    MyData dat = new MyData(jsonResult.getInt("id"), jsonResult.getString("bookcondition"), "http://159.65.158.141/UploadExample/uploads/"+jsonResult.getString("id")+".jpg" ,jsonResult.getString("cost"),jsonResult.getString("edition"),jsonResult.getString("bookname"),jsonResult.getString("enrollnum"));
                    Log.d("datastring" , dat.getDescription());
                    Log.d("imglink" , dat.getImage_link());
                    data_list.add(dat);
                }
                if(data_list.isEmpty())
                    Log.d("datalist","emptyyy");

            } catch (IOException e) {
                Log.d("qwertyuop", "poiuytrewq");
                e.printStackTrace();
            } catch (JSONException e) {
                Log.d("End of content", "poiuytrewq");
            }

            return data_list;
        }
    };
}