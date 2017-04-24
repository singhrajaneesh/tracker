package com.jainpuja.androidmusicplayer_ver2;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class Jaincalender extends Activity {


    private ProgressDialog mProgressDialog;
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    public File jainpujadir;
    public String filename;
    TouchImageView img;
    public TextView title;
    public Button backbutton;
    DownloadFileAsync asyncFetch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        String received_value = getIntent().getExtras().getString("value");
        setContentView(R.layout.activity_jaincalender);
        title = (TextView) findViewById(R.id.title);
        title.setText("Jain Calendar");
        RelativeLayout lL = (RelativeLayout) findViewById(R.id.mylayout);
        img = new TouchImageView(this);
        img.setMaxZoom(3f);
        lL.addView(img);
        backbutton = (Button) findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.animator.left_in, R.animator.right_out);
            }
        });
        jainpujadir = new File(Environment.getExternalStorageDirectory() + "/JainPuja/JainCalender");
        if (!jainpujadir.exists()) {
            jainpujadir.mkdirs();
            File file1 = new File(jainpujadir,".nomedia");
            try {
                boolean fileCreated = file1.createNewFile();
                System.out.println("fileCreated:"+fileCreated);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            Log.d("error", "dir. already exists");

        File file = new File(jainpujadir+"/"+received_value);

        if (file.exists())
        {
            String local_file = jainpujadir +"/"+ received_value  ;
            Bitmap bitmap = BitmapFactory.decodeFile(local_file);
            img.setImageBitmap(bitmap);
        }
        else
        {
            String image_url = "http://www.jainpuja.com/mobileapp/images/" + received_value;
            System.out.println("URL for Calendar :" + image_url);
            asyncFetch = new DownloadFileAsync();
            asyncFetch.execute(image_url,received_value);

        }
    }


    @Override
    public void onBackPressed() {
        if (asyncFetch != null)
            asyncFetch.cancel(true);
        finish();
        super.onBackPressed();
        overridePendingTransition(R.animator.left_in, R.animator.right_out);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_DOWNLOAD_PROGRESS:
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setMessage("Loading Image..");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.show();
                return mProgressDialog;
            default:
                return null;
        }
    }
    public class DownloadFileAsync extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(DIALOG_DOWNLOAD_PROGRESS);

        }

        @Override
        protected String doInBackground(String... aurl) {
            int count;

            try {

                URL url = new URL(aurl[0]);
                String imagename = aurl[1];
                URLConnection conexion = url.openConnection();
                conexion.connect();

                int lenghtOfFile = conexion.getContentLength();
                System.out.println("lenghtOfFile:"+lenghtOfFile);
                filename = jainpujadir +"/"+ imagename;
                System.out.println("File name :"+filename);
                InputStream input = new BufferedInputStream(url.openStream());
                OutputStream output = new FileOutputStream(filename);

                byte data[] = new byte[1024];
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
            } catch (Exception e)
            {
                System.out.println("Exception:"+e);
            }
            return null;

        }

        protected void onProgressUpdate(String... progress)
        {
            //Log.d("ANDRO_ASYNC", progress[0]);
            mProgressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        @Override
        protected void onPostExecute(String unused)
        {
            System.out.println("filename:"+filename);
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(filename);
                img.setImageBitmap(bitmap);
            }
            catch (Exception e)
            {
                System.out.println("Exception:"+e);
            }
            dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
            Toast.makeText(getApplicationContext(), "Use pinch gesture to zoom in and zoom out", Toast.LENGTH_LONG).show();

        }

    }

}
