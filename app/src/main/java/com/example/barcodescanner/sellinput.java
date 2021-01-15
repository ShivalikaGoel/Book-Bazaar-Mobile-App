package com.example.barcodescanner;
import  android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.ServerResponse;
import net.gotev.uploadservice.UploadInfo;
import net.gotev.uploadservice.UploadNotificationConfig;
import net.gotev.uploadservice.UploadStatusDelegate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class sellinput extends AppCompatActivity
{

    ImageView imageView;
    Button button;
    File photoFile = null;
    static final int CAPTURE_IMAGE_REQUEST = 1;
    File destination;
    Button sub;
    String isbn;
    Bitmap scaled;
    EditText Condition,Cost,Edition;
    String mCurrentPhotoPath;
    String cost,edition,condition;
    //    ProgressDialog progressDialog;
    private static final String IMAGE_DIRECTORY_NAME = "Images";
    private static final int STORAGE_PERMISSION_CODE = 2342;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellinput);
        Bundle b = getIntent().getExtras();
        isbn = b.getString("isbn");

        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.btnCaptureImage);
        sub = findViewById(R.id.sub);
        Condition = findViewById(R.id.condition);
        Cost = findViewById(R.id.cost);
        Edition = findViewById(R.id.edition);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    Log.d("hey", "cam");
                    captureImage();
                } else {
//                    Log.d("hey", "cam2");
                    captureImage2();
                }
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                progressDialog = ProgressDialog.show(sellinput.this,
//                        "ProgressDialog",
//                        "Wait for  seconds");
//                new backgroundff().execute();
//
//progressDialog.dismiss();
//                return result;
                edition=Edition.getText().toString();
                cost=Cost.getText().toString();
                condition=Condition.getText().toString();
                requestStoragePermission();
                uploadMultipart();

                Log.d("hey","hey");
//                Log.d("cost",cost.getText().toString());
//                Log.d("description",desc.getText().toString());
                Toast.makeText(getApplicationContext(),"Details Recieved",Toast.LENGTH_LONG).show();
            }
        });
    }


    /* Capture Image function for 4.4.4 and lower. Not tested for Android Version 3 and 2 */
    private void captureImage2()
    {

        try {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            photoFile = createImageFile4();
            if (photoFile != null)
            {
//                displayMessage(getBaseContext(), photoFile.getAbsolutePath());
//                Log.i("Mayank", photoFile.getAbsolutePath());
                Uri photoURI = Uri.fromFile(photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, CAPTURE_IMAGE_REQUEST);
            }
        } catch (Exception e)
        {
            displayMessage(getBaseContext(), "Camera is not available." + e.toString());
        }
    }

    private void captureImage()
    {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }
        else
        {
//            Log.d("hey", "gotwrite");
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null)
            {
                // Create the File where the photo should go
                try {

                    photoFile = createImageFile();
//                    Log.d("fileeeee", photoFile.toString());
                    displayMessage(getBaseContext(), photoFile.getAbsolutePath());
//                    Log.d("file", photoFile.getAbsolutePath());
                    Log.i("Mayank", photoFile.getAbsolutePath());

                    // Continue only if the File was successfully created
                    if (photoFile != null)
                    {
                        Log.d("photo", "not null");
                        Uri photoURI = FileProvider.getUriForFile(sellinput.this,
                                "com.example.barcodescanner.fileprovider",
                                photoFile);
                        Log.d("photo", "Uri not  null");
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, CAPTURE_IMAGE_REQUEST);
                    }
                } catch (Exception ex) { Log.d("hey", "gote");
                    // Error occurred while creating the File
                    displayMessage(getBaseContext(), ex.getMessage().toString());
                }


            } else {
                displayMessage(getBaseContext(), "Nullll");
            }
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {


        if (requestCode == CAPTURE_IMAGE_REQUEST && resultCode == RESULT_OK)
        {
//            Bitmap myBitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
            Bitmap d = new BitmapDrawable(this.getResources(), photoFile.getAbsolutePath()).getBitmap();
            int nh = (int) (d.getHeight() * (400.0 / d.getWidth()));
            scaled = Bitmap.createScaledBitmap(d, 400, nh, true);
//            Bitmap converetdImage = getResizedBitmap(d, 500);

//            uploadImage = getStringImage(scaled);
//            imageView.setImageBitmap(scaled);
//            scaled.compress(Bitmap.CompressFormat.PNG, 90, photoFile);
            imageView.setImageBitmap(scaled);

        } else {
            displayMessage(getBaseContext(), "Request cancelled or something went wrong.");
        }
    }
    //    public String getStringImage(Bitmap bmp){
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] imageBytes = baos.toByteArray();
//        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//        return encodedImage;
//    }
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private File createImageFile4()
    {
        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), IMAGE_DIRECTORY_NAME);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists())
        {

            if (!mediaStorageDir.mkdirs())
            {

                displayMessage(getBaseContext(), "Unable to create directory.");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        destination = new File(Environment.getExternalStorageDirectory()+ File.separator + "IMG_" + timeStamp + ".jpg");
        Log.d("hey","hiiiiieeeee");
        Log.d("Destinantion",destination.getAbsolutePath());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        Log.d("MEDIA FILE",mediaFile.getAbsolutePath());
        return mediaFile;

    }

    private File createImageFile() throws IOException
    {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        File destination = new File(Environment.getExternalStorageDirectory()+ File.separator + "IMG_" + timeStamp + ".jpg");
//        Log.d("hey","hiiiiieeeee");
//        Log.d("Destinantion",destination.getAbsolutePath());
        mCurrentPhotoPath = image.getAbsolutePath();
//        Log.d("image ka absolute path",image.getAbsolutePath());
        Log.d("MEDIA FILE",image.getAbsolutePath());
        return image;
    }
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

        }

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        Log.d("read","readaccess");


    }




    public void uploadMultipart()
    {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try {
            Log.d("inside multipart","multi[art");
            String uploadId = UUID.randomUUID().toString();
            FileOutputStream fos = new FileOutputStream(photoFile);
            scaled.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            new MultipartUploadRequest(this, uploadId, Constants.UPLOAD_URL).setMethod("POST")
                    .addFileToUpload(photoFile.getAbsolutePath(), "image") //Adding file
                    .addParameter("name", "helu")
                    .addParameter("edition",edition)
                    .addParameter("cost",cost)
                    .addParameter("condition",condition)
                    .addParameter("isbn",isbn)
                    .addParameter("enrollnum","010010")//Adding text parameter to the request
//                .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(4).setDelegate(new UploadStatusDelegate() {
                @Override
                public void onProgress(Context context, UploadInfo uploadInfo)
                {
                    Log.d("On Progress", String.valueOf(uploadInfo));
                }

                @Override
                public void onError(Context context, UploadInfo uploadInfo, Exception exception)
                {
                    // your code here
                    Log.d("On error", String.valueOf(exception));
                }

                @Override
                public void onCompleted(Context context, UploadInfo uploadInfo, ServerResponse serverResponse)
                {

                    // YourClass obj = new Gson().fromJson(serverResponse.getBodyAsString(), YourClass.class);

                    Log.d("serverResponse", String.valueOf(serverResponse.getBodyAsString()));
                    Intent intent =new Intent(sellinput.this,buysellcat.class);
                    startActivity(intent);

                }

                @Override
                public void onCancelled(Context context, UploadInfo uploadInfo) {
                    // your code here
                    Log.d("On cancelled", String.valueOf(uploadInfo));
                }
            })
                    .startUpload(); //Starting the upload
            Toast.makeText(this,"hi",Toast.LENGTH_SHORT).show();


        } catch (Exception exc)
        {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("path",exc.getMessage());
        }

    }

    private void displayMessage(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if (requestCode == 0)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            {
                captureImage();
            }
        }

    }



}
