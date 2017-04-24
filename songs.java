package com.jainpuja.androidmusicplayer_ver2;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import java.util.logging.Handler;


public class songs extends ListActivity implements View.OnClickListener,View.OnTouchListener,MediaPlayer.OnCompletionListener,MediaPlayer.OnBufferingUpdateListener {
    private static final String TAG = null;
    public TextView title;
    static String KEY_ID,KEY_NAME,KEY_COST,KEY_DESC,KEY_IMAGE,KEY_CATEGORY,heading;
    ListView list1;
    LazyAdapter adapter;
    public List<String> url,name;
    public Button backbutton;
    public int length;
    private MyMediaPlayer player = MyMediaPlayer.getInstance();
    RelativeLayout layout;
    private int mediaFileLengthInMilliseconds;
    public   Handler handler = new Handler() ;
    TelephonyManager mTelephonyMgr;
//    public static int playerflag = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "On Create songs  .....");
        mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        mTelephonyMgr.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);



        setContentView(R.layout.activity_songs);
        layout = (RelativeLayout)findViewById(R.id.top);
        title = (TextView) findViewById(R.id.title);

        url = new ArrayList<String>();
        name = new ArrayList<String>();
        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();
        KEY_ID = getIntent().getExtras().getString("value");

        title.setText(getIntent().getExtras().getString("value1"));
        backbutton = (Button) findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.animator.left_in, R.animator.right_out);
            }
        });

        player.layout = (RelativeLayout) findViewById(R.id.bottom);
        player.bhajanname = (TextView) findViewById(R.id.Bhajan_name);
        player.buttonPlayPause = (ImageButton) findViewById(R.id.playpausebutton);

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




//        switch (KEY_ID)
//        {
//            case "BHAJANCAT1": {
//                title.setText("Namokar Mantra");
//                KEY_NAME = "HINTITLE";
//                KEY_COST = "ENGTITLE";
//                KEY_DESC = "LENGTH";
//                KEY_IMAGE = "THUMBNAIL";
//                KEY_CATEGORY = "SONGURL";
//            }
//            break;
//            case "BHAJANCAT2": {
//                title.setText("Aarti Sangrah");
//                KEY_NAME = "HINTITLE";
//                KEY_COST = "ENGTITLE";
//                KEY_DESC = "LENGTH";
//                KEY_IMAGE = "THUMBNAIL";
//                KEY_CATEGORY = "SONGURL";
//            }
//            break;
//            case "BHAJANCAT3": {
//                title.setText("Mangal Path");
//                KEY_NAME = "HINTITLE";
//                KEY_COST = "ENGTITLE";
//                KEY_DESC = "LENGTH";
//                KEY_IMAGE = "THUMBNAIL";
//                KEY_CATEGORY = "SONGURL";
//            }
//            break;
//            case "BHAJANCAT4": {
//                title.setText("Bhaktambar Strotat");
//                KEY_NAME = "HINTITLE";
//                KEY_COST = "ENGTITLE";
//                KEY_DESC = "LENGTH";
//                KEY_IMAGE = "THUMBNAIL";
//                KEY_CATEGORY = "SONGURL";
//            }
//            break;
//            case "BHAJANCAT5": {
//                title.setText("Shree Mahaveer Swami");
//                KEY_NAME = "HINTITLE";
//                KEY_COST = "ENGTITLE";
//                KEY_DESC = "LENGTH";
//                KEY_IMAGE = "THUMBNAIL";
//                KEY_CATEGORY = "SONGURL";
//            }
//            break;
//            case "BHAJANCAT6": {
//                title.setText("Shree Bhagwan Chalisa");
//                KEY_NAME = "HINTITLE";
//                KEY_COST = "ENGTITLE";
//                KEY_DESC = "LENGTH";
//                KEY_IMAGE = "THUMBNAIL";
//                KEY_CATEGORY = "SONGURL";
//            }
//            break;
//            case "BHAJANCAT7": {
//                title.setText("Various Pujas");
//                KEY_NAME = "HINTITLE";
//                KEY_COST = "ENGTITLE";
//                KEY_DESC = "LENGTH";
//                KEY_IMAGE = "THUMBNAIL";
//                KEY_CATEGORY = "SONGURL";
//            }
//            break;
//            case "BHAJANCAT8": {
//                title.setText("Tumse Laagi Lagan");
//                KEY_NAME = "HINTITLE";
//                KEY_COST = "ENGTITLE";
//                KEY_DESC = "LENGTH";
//                KEY_IMAGE = "THUMBNAIL";
//                KEY_CATEGORY = "SONGURL";
//            }
//            break;
//            case "BHAJANCAT9": {
//                title.setText("Meri Bhavana");
//                KEY_NAME = "HINTITLE";
//                KEY_COST = "ENGTITLE";
//                KEY_DESC = "LENGTH";
//                KEY_IMAGE = "THUMBNAIL";
//                KEY_CATEGORY = "SONGURL";
//            }
//            break;
//            case "BHAJANCAT10": {
//                title.setText("Dus Lakshan Puja");
//                KEY_NAME = "HINTITLE";
//                KEY_COST = "ENGTITLE";
//                KEY_DESC = "LENGTH";
//                KEY_IMAGE = "THUMBNAIL";
//                KEY_CATEGORY = "SONGURL";
//            }
//            break;
//            case "BHAJANCAT11": {
//                title.setText("Prabhu Puja");
//                KEY_NAME = "HINTITLE";
//                KEY_COST = "ENGTITLE";
//                KEY_DESC = "LENGTH";
//                KEY_IMAGE = "THUMBNAIL";
//                KEY_CATEGORY = "SONGURL";
//            }
//            break;
//            default: {
//                title.setText("Prabhu Puja");
//                KEY_NAME = "HINTITLE";
//                KEY_COST = "ENGTITLE";
//                KEY_DESC = "LENGTH";
//                KEY_IMAGE = "THUMBNAIL";
//                KEY_CATEGORY = "SONGURL";
//            }
//            break;
//
//
//
//        }

        KEY_NAME = "HINTITLE";
        KEY_COST = "ENGTITLE";
        KEY_DESC = "LENGTH";
        KEY_IMAGE = "THUMBNAIL";
        KEY_CATEGORY = "SONGURL";

        String path = Environment.getExternalStorageDirectory() + "/jainpuja/jainpuja.xml";
        XMLParser parser = new XMLParser();
        String xml = parser.getxmlfromSDcard(path); // getting XML
        Document doc = parser.getDomElement(xml); // getting DOM element
        NodeList nl = doc.getElementsByTagName(KEY_ID);


        for (int i = 0; i < nl.getLength(); i++) {
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            map.put(KEY_NAME, parser.getValue(e, KEY_NAME));
            map.put(KEY_COST, parser.getValue(e, KEY_COST));
            map.put(KEY_DESC, parser.getValue(e, KEY_DESC));
            map.put(KEY_IMAGE, parser.getValue(e, KEY_IMAGE));
            url.add(parser.getValue(e, KEY_CATEGORY));
            name.add(parser.getValue(e, KEY_COST));
            menuItems.add(map);
        }
        list1 = getListView();
        adapter = new LazyAdapter(this, menuItems);
        list1.setSelector(R.drawable.your_selecter);
        list1.setAdapter(adapter);//






    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "On Start .....");
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                System.out.println("On start called");
                if (url.get(position).contains("mp3")) {
                    player.songname = name.get(position);
                    player.songurl = url.get(position);
                    initView(player.songurl, player.songname);
                    player.layout.setVisibility(View.VISIBLE);
                }
            }
        });


     }
    //
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
    protected void onResume()
    {
        super.onResume();

    }

    @Override
    public void onPause()
    {
        super.onPause();


    }


    /** This method initialise all the views in project*/
    public void initView(String url,String name)
    {
        Log.i(TAG, "song_url");
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
    public boolean onTouch(View v, MotionEvent event)
    {

            /** Seek bar onTouch event handler. Method which seeks MediaPlayer to seekBar primary progress position*/
            if(player.mp.isPlaying())
            {
                SeekBar sb = (SeekBar)v;
                int playPositionInMilliseconds = (mediaFileLengthInMilliseconds / 100) * sb.getProgress();
                player.mp.seekTo(playPositionInMilliseconds);
            }
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        /** MediaPlayer onCompletion event handler. Method which calls then song playing is complete*/
        Drawable replacer = getResources().getDrawable(R.drawable.play);
        player.buttonPlayPause.setBackgroundDrawable(replacer);




    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent)
    {


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.animator.left_in, R.animator.right_out);

    }

}

//if(player.mp == null)
//        {
//        System.out.println("player.mp == null");
//        Drawable replacer = getResources().getDrawable(R.drawable.play);
//        player.buttonPlayPause.setBackgroundDrawable(replacer);
//        player.bhajanname.setText("");
//        player.layout.setVisibility(View.VISIBLE);
//
//
//        }
//        else
//        {
//
//        if(player.mp.isPlaying())
//        {
//        System.out.println("player.mp.isPlaying()");
//        player.layout.setVisibility(View.VISIBLE);
//        Drawable replacer = getResources().getDrawable(R.drawable.pause);
//        player.buttonPlayPause.setBackgroundDrawable(replacer);
//        player.buttonPlayPause.setOnClickListener(new View.OnClickListener()
//        {
//
//@Override
//public void onClick(View view)
//        {
//        if(player.mp.isPlaying())
//        {
//        System.out.println("player.mp.pause()");
//        player.mp.pause();
//        Drawable replacer = getResources().getDrawable(R.drawable.play);
//        player.buttonPlayPause.setBackgroundDrawable(replacer);
//        length=player.mp.getCurrentPosition();
//        }
//        else
//        {
//        System.out.println("player.mp.pause()");
//        player.mp.seekTo(length);
//        player.mp.start();
//        Drawable replacer = getResources().getDrawable(R.drawable.pause);
//        player.buttonPlayPause.setBackgroundDrawable(replacer);
//        //primarySeekBarProgressUpdater();
//        }
//
//
//        }
//        });
//
//        player.bhajanname.setText(player.songname);
//
//        }
//        // doubt full code
//        else
//        {
//        player.layout.setVisibility(View.VISIBLE);
//        Drawable replacer = getResources().getDrawable(R.drawable.play);
//        player.buttonPlayPause.setBackgroundDrawable(replacer);
//        player.buttonPlayPause.setOnClickListener(new View.OnClickListener()
//        {
//
//@Override
//public void onClick(View view)
//        {
//        if(player.mp.isPlaying())
//        {
//        player.mp.pause();
//        Drawable replacer = getResources().getDrawable(R.drawable.play);
//        player.buttonPlayPause.setBackgroundDrawable(replacer);
//        length=player.mp.getCurrentPosition();
//        }
//        else
//        {
//        player.mp.seekTo(length);
//        player.mp.start();
//        Drawable replacer = getResources().getDrawable(R.drawable.pause);
//        player.buttonPlayPause.setBackgroundDrawable(replacer);
//        //primarySeekBarProgressUpdater();
//        }
//
//
//        }
//        });
//
//        player.bhajanname.setText(player.songname);
//        }
//        }
