package com.jainpuja.androidmusicplayer_ver2;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


public class darshan extends Activity implements View.OnClickListener,View.OnTouchListener,MediaPlayer.OnCompletionListener,MediaPlayer.OnBufferingUpdateListener {
    public Button backbutton;
    private ProgressDialog mProgressDialog;
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    public TextView title, heading;
    public ImageView image1;
    ProgressDialog pDialog;
    public int length;
    private MyMediaPlayer player = MyMediaPlayer.getInstance();
    Bitmap bitmap;
    public String filename;
    private int mediaFileLengthInMilliseconds;
    private final Handler handler = new Handler() ;
    Drawable replacer;
    public File jainpujadir;
    DownloadFileAsync asyncFetch;
    String imageurl,imagename;
    TelephonyManager mTelephonyMgr;
    ImageButton playbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        mTelephonyMgr.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        setContentView(R.layout.activity_darshan);
        title = (TextView) findViewById(R.id.title);
        backbutton = (Button) findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.animator.left_in, R.animator.right_out);
            }
        });
        title.setText("Dev Darshan");
        heading = (TextView) findViewById(R.id.heading);
        heading.setText(getIntent().getExtras().getString("value"));
        image1 = (ImageView) findViewById(R.id.loadimage);
        player.layout = (RelativeLayout) findViewById(R.id.bottom);
        player.bhajanname = (TextView) findViewById(R.id.Bhajan_name);
        player.buttonPlayPause = (ImageButton) findViewById(R.id.playpausebutton);
        playbutton = (ImageButton) findViewById(R.id.playbutton);
        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Play called");
                //object.initView(getIntent().getExtras().getString("songurl"),getIntent().getExtras().getString("songname"));
                System.out.println("Played Song Url"+getIntent().getExtras().getString("songurl"));
                System.out.println("Played Song Name"+getIntent().getExtras().getString("songname"));
                initView(getIntent().getExtras().getString("songurl"),getIntent().getExtras().getString("songname"));
            }
        });

        if(player.mp!=null)
        {
            System.out.println("Player.mp!=null");
            if(player.mp.isPlaying()) {
                System.out.println("player.mp.isPlaying()");
                player.bhajanname.setText(player.songname);
                Drawable replacer = getResources().getDrawable(R.drawable.pause);
                player.buttonPlayPause.setBackgroundDrawable(replacer);
            }
            else
            {
                player.bhajanname.setText(player.songname);
                Drawable replacer = getResources().getDrawable(R.drawable.play);
                player.buttonPlayPause.setBackgroundDrawable(replacer);
            }
            player.buttonPlayPause.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View view)
                {
                    if(player.mp.isPlaying())
                    {
                        player.mp.pause();
                        Drawable replacer = getResources().getDrawable(R.drawable.play);
                        player.buttonPlayPause.setBackgroundDrawable(replacer);
                        length=player.mp.getCurrentPosition();


                    }
                    else
                    {

                        player.mp.seekTo(length);
                        player.mp.start();
                        Drawable replacer = getResources().getDrawable(R.drawable.pause);
                        player.buttonPlayPause.setBackgroundDrawable(replacer);

                    }


                }
            });

        }
        else
        {
            System.out.println("Player.mp==null");
        }




        imageurl = getIntent().getExtras().getString("url");
        System.out.println("Imageurl:" + imageurl.substring(41, imageurl.length()));
        imagename = imageurl.substring(41, imageurl.length());
        jainpujadir = new File(Environment.getExternalStorageDirectory() + "/JainPuja/JainDarshan");
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
        File file = new File(jainpujadir+"/"+imagename);

        if (file.exists())
        {
            String local_file = jainpujadir +"/"+ imagename  ;
            Bitmap bitmap = BitmapFactory.decodeFile(local_file);
            image1.setImageBitmap(bitmap);
        }
        else
        {
            asyncFetch = new DownloadFileAsync();
            asyncFetch.execute(getIntent().getExtras().getString("url"));

        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent)
    {

    }
    @Override
    public void onBackPressed() {
        if (asyncFetch != null)
        {
            File file = new File(filename);
            file.delete();
            asyncFetch.cancel(true);
        }
        finish();
        super.onBackPressed();
        overridePendingTransition(R.animator.left_in, R.animator.right_out);
    }

    /** This method initialise all the views in project*/
    public void initView(String url,String name)
    {
        //Log.i(TAG, "song_url");
        String song_url = url;
        length = 0;

        if(player.mp == null)
        {

            player.mp = new MediaPlayer();
            player.mp.setOnBufferingUpdateListener(this);
            player.mp.setOnCompletionListener(this);

        }
        player.songname = name;
        player.bhajanname.setText(player.songname);
        player.buttonPlayPause.setOnClickListener(this);
        Drawable replacer = getResources().getDrawable(R.drawable.pause);
        player.buttonPlayPause.setBackgroundDrawable(replacer);
        player.buttonPlayPause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (player.mp.isPlaying()) {
                    player.mp.pause();
                    Drawable replacer = getResources().getDrawable(R.drawable.play);
                    player.buttonPlayPause.setBackgroundDrawable(replacer);
                    length = player.mp.getCurrentPosition();


                } else {

                    player.mp.seekTo(length);
                    player.mp.start();
                    Drawable replacer = getResources().getDrawable(R.drawable.pause);
                    player.buttonPlayPause.setBackgroundDrawable(replacer);

                }


            }
        });




        try
        {
            if(player.mp.isPlaying())
            {
                player.mp.stop();
            }
            player.mp.reset();
            player.mp.setDataSource(song_url);
            player.mp.prepare();
            player.mp.start();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        mediaFileLengthInMilliseconds = player.mp.getDuration();

    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }
    private PhoneStateListener mPhoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            // Test for incoming call, dialing call, active or on hold
            if (state== TelephonyManager.CALL_STATE_RINGING || state==TelephonyManager.CALL_STATE_OFFHOOK)
            {
                // Put here the code to stop your music
                player.mp.pause();
                Drawable replacer = getResources().getDrawable(R.drawable.play);
                player.buttonPlayPause.setBackgroundDrawable(replacer);
            }
//            else if(state==TelephonyManager.CALL_STATE_IDLE )
//            {
//                System.out.println("I am call from CALL_STATE_IDLE");
//                if(player.mp!=null) {
//                    player.mp.start();
//                    Drawable replacer = getResources().getDrawable(R.drawable.pause);
//                    player.buttonPlayPause.setBackgroundDrawable(replacer);
//                }
//            }
            super.onCallStateChanged(state, incomingNumber);
        }
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
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
            System.out.println("filename:" + filename);
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(filename);
                image1.setImageBitmap(bitmap);
            }
            catch (Exception e)
            {
                System.out.println("Exception:"+e);
            }
            dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
            asyncFetch = null;


        }

    }
}

//        player.layout = (RelativeLayout) findViewById(R.id.bottom);
//        player.bhajanname = (TextView) findViewById(R.id.Bhajan_name);
//        player.buttonPlayPause = (ImageButton) findViewById(R.id.playpausebutton);
//        player.songname = getIntent().getExtras().getString("songname");
//        player.bhajanname.setText(player.songname);
//        replacer = getResources().getDrawable(R.drawable.play);
//        player.buttonPlayPause.setBackgroundDrawable(replacer);
//       player.mp = new MediaPlayer();


//        player.buttonPlayPause.setOnClickListener(new View.OnClickListener()
//        {
//
//            @Override
//            public void onClick(View view)
//            {
//                if(player.mp.isPlaying())
//                {
//                    System.out.println("If called");
//                    player.mp.pause();
//                    player.mp.reset();
//                    Drawable replacer = getResources().getDrawable(R.drawable.play);
//                    player.buttonPlayPause.setBackgroundDrawable(replacer);
//                    length=player.mp.getCurrentPosition();
//                }
//                else
//                {
//                    System.out.println("else called");
//
//                    try {
//                        player.mp.setDataSource(getIntent().getExtras().getString("songurl"));
//                        player.mp.prepare();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    player.mp.start();
//                    Drawable replacer = getResources().getDrawable(R.drawable.pause);
//                    player.buttonPlayPause.setBackgroundDrawable(replacer);
//
//                }
//            }
//        });