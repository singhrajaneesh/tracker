package com.jainpuja.androidmusicplayer_ver2;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
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


public class welcome extends Activity
{
    public WebView webView;
    public TextView title,jainbhajan,version;
    public ScrollView homescrollView, updatescrollview, aboutusscrollView, facebookscrollview;
    public Button homebtn, updatebtn, facebookbtn, aboutusbtn, yesbtn,okbtn, nobtn,audiobtn,jinwanibtn,pujabtn,calbtn,siddhabtn,videobtn,misslenbtn ;
    final Context context = this;
    private ProgressDialog mProgressDialog;
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    public ProgressBar bar;
    public File jainpujadir;
    public String filename, xmlpath,Outputfilename;
    public String value = null;
    File xmlfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Create title text
        title = (TextView) findViewById(R.id.title);
        title.setText("Home");

        //create a scroll view
        homescrollView = (ScrollView) findViewById(R.id.homeScroller);
        updatescrollview = (ScrollView) findViewById(R.id.updateScroller);
        facebookscrollview = (ScrollView) findViewById(R.id.webScroller);
        aboutusscrollView = (ScrollView) findViewById(R.id.aboutusScroller);
        nobtn = (Button) findViewById(R.id.no_btn);
        nobtn.setText("  No  ");
        yesbtn = (Button) findViewById(R.id.yes_btn);
        yesbtn.setText("  Yes  ");
        okbtn = (Button) findViewById(R.id.ok_btn);
        okbtn.setText("  Ok  ");
        File Externaldirectory = Environment.getExternalStorageDirectory();
        System.out.println(Externaldirectory);
        xmlfile = new File(Externaldirectory.getAbsolutePath() + "/JainPuja/jainpuja.xml");

        jainbhajan = (TextView) findViewById(R.id.audiolabel);





        if(xmlfile.exists())
        {
            title.setText("Home");
            updatescrollview.setVisibility(View.INVISIBLE);
            facebookscrollview.setVisibility(View.INVISIBLE);
            aboutusscrollView.setVisibility(View.INVISIBLE);



        }
        else
        {
            title.setText("Update");
            homescrollView.setVisibility(View.INVISIBLE);
            updatescrollview.setVisibility(View.VISIBLE);
            facebookscrollview.setVisibility(View.INVISIBLE);
            aboutusscrollView.setVisibility(View.INVISIBLE);
            nobtn.setVisibility(View.INVISIBLE);
            yesbtn.setVisibility(View.INVISIBLE);
            okbtn.setVisibility(View.VISIBLE);



        }


        //create a home btn
        homebtn = (Button) findViewById(R.id.home);
        homebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                title.setText("Home");
                Intent myIntent = new Intent(welcome.this, home.class);
                welcome.this.startActivity(myIntent);
                finish();
                homescrollView.setVisibility(View.VISIBLE);
                updatescrollview.setVisibility(View.INVISIBLE);
                facebookscrollview.setVisibility(View.INVISIBLE);
                aboutusscrollView.setVisibility(View.INVISIBLE);

             //  overridePendingTransition(R.animator.left_in, R.animator.right_out);



            }
        }
        );

        //create a audio btn
        audiobtn = (Button) findViewById(R.id.audio);
        audiobtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(welcome.this, audio.class);
                value = "CATEGORY";
                myIntent.putExtra("value", value);
                welcome.this.startActivity(myIntent);

            }
        }
        );

        jinwanibtn = (Button) findViewById(R.id.info);
        jinwanibtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(welcome.this, Jinwani.class);
                value = "JINWANI";
                myIntent.putExtra("value", value);
                welcome.this.startActivity(myIntent);
                overridePendingTransition(R.animator.right_in, R.animator.left_out);

            }

        }
        );
        pujabtn =  (Button) findViewById(R.id.puja);
        pujabtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(welcome.this, audio.class);
                value = "AARTI";
                myIntent.putExtra("value", value);
                welcome.this.startActivity(myIntent);
                overridePendingTransition(R.animator.right_in, R.animator.left_out);

            }
        }
        );
        calbtn =  (Button) findViewById(R.id.calender);
        calbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(welcome.this, audio.class);
                value = "CALENDAR";
                myIntent.putExtra("value", value);
                welcome.this.startActivity(myIntent);

            }
        }
        );
        siddhabtn =  (Button) findViewById(R.id.siddha);
        siddhabtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(welcome.this, middle.class);
                value = "SIDDHA";
                myIntent.putExtra("value", value);
                welcome.this.startActivity(myIntent);
                overridePendingTransition(R.animator.right_in, R.animator.left_out);

            }
        }
        );
        videobtn =  (Button) findViewById(R.id.video);
        videobtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(welcome.this, middle.class);
                value = "VIDEO";
                myIntent.putExtra("value", value);
                welcome.this.startActivity(myIntent);
                overridePendingTransition(R.animator.right_in, R.animator.left_out);

            }
        }
        );
        misslenbtn = (Button) findViewById(R.id.missleneous);
        misslenbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(welcome.this, audio.class);
                value = "DEVDHARSHAN";
                myIntent.putExtra("value", value);
                welcome.this.startActivity(myIntent);
                overridePendingTransition(R.animator.right_in, R.animator.left_out);

            }
        }
        );


        //create a update btn
        updatebtn = (Button) findViewById(R.id.update);
        updatebtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                title.setText("Update");
                homescrollView.setVisibility(View.INVISIBLE);
                updatescrollview.setVisibility(View.VISIBLE);
                TextView updatetxt = (TextView)findViewById(R.id.update_txt);
                overridePendingTransition(R.animator.right_in, R.animator.left_out);

                if(xmlfile.exists())

                {

                  updatetxt.setText("Would you like to update latest bhajans, videos and data (approx 4.5 MBs)? \n \nThis will require an internet connection and may take a minute or two.");
                  nobtn.setVisibility(View.VISIBLE);
                  yesbtn.setVisibility(View.VISIBLE);
                  okbtn.setVisibility(View.INVISIBLE);
                }

                else
                {
                    nobtn.setVisibility(View.INVISIBLE);
                    yesbtn.setVisibility(View.INVISIBLE);
                    okbtn.setVisibility(View.VISIBLE);
                }

                facebookscrollview.setVisibility(View.INVISIBLE);
                aboutusscrollView.setVisibility(View.INVISIBLE);

            }


        }
        );
        //create a no button

        nobtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                Intent myIntent = new Intent(welcome.this, home.class);
                welcome.this.startActivity(myIntent);
                finish();
                overridePendingTransition(R.animator.right_in, R.animator.left_out);
            }

        }
        );

        //create a yes button

        yesbtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                startDownload();
                title.setText("Home");
                homescrollView.setVisibility(View.VISIBLE);
                updatescrollview.setVisibility(View.INVISIBLE);
                facebookscrollview.setVisibility(View.INVISIBLE);
                aboutusscrollView.setVisibility(View.INVISIBLE);


            }

        });
        okbtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                startDownload();
                title.setText("Home");
                homescrollView.setVisibility(View.VISIBLE);
                updatescrollview.setVisibility(View.INVISIBLE);
                facebookscrollview.setVisibility(View.INVISIBLE);
                aboutusscrollView.setVisibility(View.INVISIBLE);


            }

        });

        //create a facebook btn
        facebookbtn = (Button) findViewById(R.id.facebook);
        facebookbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                title.setText("Facebook");
                homescrollView.setVisibility(View.INVISIBLE);
                updatescrollview.setVisibility(View.INVISIBLE);
                facebookscrollview.setVisibility(View.VISIBLE);
                aboutusscrollView.setVisibility(View.INVISIBLE);
                webView = (WebView) findViewById(R.id.webView1);
                WebSettings webSettings = webView.getSettings();
                webSettings.setBuiltInZoomControls(true);
                webSettings.setJavaScriptEnabled(true);
                webView.setWebViewClient(new Callback());
                webView.loadUrl("https://www.facebook.com/ShreeJainMandal");//HERE IS THE MAIN CHANGE
                webSettings.setSupportZoom(false);



            }


        });

        //create a aboutus btn
        aboutusbtn = (Button) findViewById(R.id.about_us);
        aboutusbtn.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View v)
            {
                title.setText("About Us");
                homescrollView.setVisibility(View.INVISIBLE);
                updatescrollview.setVisibility(View.INVISIBLE);
                facebookscrollview.setVisibility(View.INVISIBLE);
                aboutusscrollView.setVisibility(View.VISIBLE);




                PackageManager manager = context.getPackageManager();
                PackageInfo info = null;
                try
                {
                    info = manager.getPackageInfo(context.getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException e)
                {
                    e.printStackTrace();
                }
                String versionno = "ver : "+info.versionName ;
                String manufacturer ="Device Manufacturer : "+ Build.MANUFACTURER;
                String model = "Device model : "+Build.MODEL;
                int densityDpi = getApplicationContext().getResources().getDisplayMetrics().densityDpi;
                String dendpi = "Density :"+String.valueOf(densityDpi);
                switch(getApplicationContext().getResources().getDisplayMetrics().densityDpi){
                    case DisplayMetrics.DENSITY_LOW:
                        dendpi = dendpi+"(LDPI)";
                        break;
                    case DisplayMetrics.DENSITY_MEDIUM:
                        dendpi = dendpi+"(MDPI)";
                        break;
                    case DisplayMetrics.DENSITY_HIGH:
                        dendpi = dendpi+"(HDPI)";
                        break;
                    case DisplayMetrics.DENSITY_XHIGH:
                        dendpi = dendpi+"(XHDPI)";
                        break;
                    case DisplayMetrics.DENSITY_XXHIGH:
                        dendpi = dendpi+"(XXHDPI)";
                        break;
                    case DisplayMetrics.DENSITY_XXXHIGH:
                        dendpi = dendpi+"(XXXHDPI)";
                        break;
                }



                DisplayMetrics displaymetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                int height = displaymetrics.heightPixels;
                int width = displaymetrics.widthPixels;
                String screenres = "Height : "+String.valueOf(height)+"  "+ "Width : "+String.valueOf(width);


                final String appinfo ="App version : "+versionno+"\n"+manufacturer+"\n"+model+"\n"+dendpi+"\n"+screenres;
                version = (TextView) findViewById(R.id.hindilabel10);
                version.setText(versionno);
                version.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),appinfo, Toast.LENGTH_LONG).show();
                    }
                });

            }
        });


    }

    private void startDownload()
    {
        String url = "http://www.jainpuja.com/mobileapp/Archive6_4.zip";//
        new DownloadFileAsync().execute(url);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_DOWNLOAD_PROGRESS:
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setMessage("Downloading file..");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.show();
                return mProgressDialog;
            default:
                return null;
        }
    }

    class Callback extends WebViewClient {


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
        }

    }

    public class DownloadFileAsync extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(DIALOG_DOWNLOAD_PROGRESS);
            jainpujadir = new File(Environment.getExternalStorageDirectory() + "/JainPuja");
            if (!jainpujadir.exists())
                jainpujadir.mkdirs();
            else
                Log.d("error", "dir. already exists");
        }

        @Override
        protected String doInBackground(String... aurl) {
            int count;

            try {

                URL url = new URL(aurl[0]);
                System.out.println("Exception:"+url);
                URLConnection conexion = url.openConnection();
                conexion.connect();

                int lenghtOfFile = conexion.getContentLength();
                System.out.println("lenghtOfFile:"+lenghtOfFile);
                filename = jainpujadir + "/Archive.zip";

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
            Log.d("ANDRO_ASYNC", progress[0]);
            mProgressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        @Override
        protected void onPostExecute(String unused)
        {
            dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
            try
            {
                unzip();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
    public void unzip() throws IOException
    {
        mProgressDialog = new ProgressDialog(welcome.this);
        mProgressDialog.setMessage("Unzipping File...\n\nThis might take a while");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        new UnZipTask().execute(filename, String.valueOf(jainpujadir));
    }


    private class UnZipTask extends AsyncTask<String, Void, Boolean>
    {
        @SuppressWarnings("rawtypes")
        @Override
        protected Boolean doInBackground(String... params)
        {
            String filePath = params[0];
            String destinationPath = params[1];


            try
            {

                String zipFile = filePath;//Environment.getExternalStorageDirectory() + "/files.zip";
                String unzipLocation = Environment.getExternalStorageDirectory() + "/JainPuja/";
                Decompress d = new Decompress(zipFile, unzipLocation);
                d.unZipIt(zipFile, unzipLocation);

            }
            catch (Exception e)
            {
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result)
        {
            mProgressDialog.dismiss();
            File file = new File(filename);
            file.delete();
        }




        private void createDir(String  dir)
        {
            File dirx = new File(dir);
            if (dirx.exists())
            {
                return;
            }
            if (!dirx.mkdirs())
            {
                throw new RuntimeException("Can not create dir " + dir);
            }
        }
    }

    @Override

    public void onBackPressed()
    {
        super.onBackPressed();

        overridePendingTransition(R.animator.left_in, R.animator.right_out);
    }
}





















