package com.example.barcodescanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.google.android.gms.vision.barcode.BarcodeDetector.*;


public class MainActivity extends AppCompatActivity
{
    String TAG="ABC";
    SurfaceView surfaceView;
    CameraSource cameraSource;
    TextView textView;
    TextView author;
    BarcodeDetector barcodeDetector;
    String s;
    Bundle b = new Bundle();
//    int i  =0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.d("number",Integer.toString(i));
//        i++;

        surfaceView =  findViewById(R.id.camerapreview);
        textView =  findViewById(R.id.textView);
        author= findViewById(R.id.author);
        barcodeDetector = new Builder(this) //builds scanner (allformats)
                .setBarcodeFormats(Barcode.ALL_FORMATS).build();
//        barcodeDetector.

        cameraSource = new CameraSource.Builder(this, barcodeDetector) //Manages the camera in conjunction with an underlying Detector
                .setRequestedPreviewSize(640, 480).setAutoFocusEnabled(true).build(); //Returns the preview size that is currently in use by the underlying camera
        //Sets whether to enable camera auto focus

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() { //Access to the underlying surface ,creates new surface and anaysis of changes
            @Override
            public void surfaceCreated(SurfaceHolder holder)
            {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                try
                {
                    cameraSource.start(holder);
                }catch (IOException e)
                {
                    e.printStackTrace();//It helps to trace the exception
                }


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();

            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() { //Recognizes barcodes (in a variety of 1D and 2D formats) in a supplied Frame.
            @Override
            public void release() {


            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections)
            {
                final SparseArray<Barcode> qrCodes= detections.getDetectedItems();

                if(qrCodes.size()!=0)
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);
                            textView.setText(qrCodes.valueAt(0).displayValue);

                            try {
                                barcodeDetector.release();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            b.putString("ISBN number", qrCodes.valueAt(0).displayValue);
                            //Your id

                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                            intent.putExtras(b); //Put your id to your next Intent
                            startActivity(intent);

                            finish();
//
//                        getApplicationContext().;
                            //      new Main2Activity.GetBookInfo().execute(qrCodes.valueAt(0).displayValue);


                        }
                    });

            }
        });

    }

    @Override
    protected void onDestroy()
    {
        Log.d("hey", "hey2");


        super.onDestroy();
    }




}