package com.example.barcodescanner;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

public class catactfinal extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CustomAdapter2 adapter;
    public List<MyData2> data_list;
    public List<MyData2> d_list;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catactfinal);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        data_list = new ArrayList<>();
        d_list=new ArrayList<>();
        Log.d("b4 fcall", "funccall");
        load_data_from_server(0);
        Log.d("after fcall", "funccalldone");
        if(d_list.isEmpty())
            Log.d("emptyyyy","elist");
        gridLayoutManager = new GridLayoutManager(catactfinal.this, 2);


    }
    public void setlist(List<MyData2>data){

        d_list=data;
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new CustomAdapter2(catactfinal.this, d_list);
        recyclerView.setAdapter(adapter);
    }

    private void load_data_from_server(final int id)
    {
        Log.d("check", "function");
        new background().execute(id);
    }
    class background extends AsyncTask<Integer, Void, List<MyData2>> {


        protected void onPostExecute(List<MyData2>data) {
            super.onPostExecute(data);
            if(data.isEmpty())
                Log.d("dataList empty","empty");
            Log.d("postexe","exe");
            setlist(data);

        }
        @Override
        protected List<MyData2> doInBackground(Integer... integers) {
            Log.d("doinbg", "done");
            String result = "";
            int id= integers[0];
            Log.d("id in doibbg" , String.valueOf(id));
            String connstr = "http://159.65.158.141/UploadExample/catact.php";
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
                String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(id), "UTF-8");
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
                   // Log.d("id", jsonResult.getString("id"));
                    // Log.d("bookcond:", jsonResult.getString("bookcondition"));
                  //  Log.d("imagesssssss", jsonResult.getString("filepath"));
                  //  id= jsonResult.getInt("id");
                    MyData2 dat = new MyData2( jsonResult.getInt("isbn"),jsonResult.getString("any_value(bookname)"), "http://159.65.158.141/UploadExample/uploads/"+jsonResult.getString("any_value(id)")+".jpg" , jsonResult.getString("any_value(bookauth)"),jsonResult.getString("any_value(subject)"));

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