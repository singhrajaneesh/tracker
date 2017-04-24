package com.jainpuja.androidmusicplayer_ver2;

import android.media.MediaPlayer;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by ekant on 14/04/15.
 */
public class MyMediaPlayer
{
    MediaPlayer mp;
    RelativeLayout layout;
    public static ImageButton buttonPlayPause;
    public SeekBar seekBarProgress;
    public static TextView bhajanname;
    public static String songurl,songname;


    private static volatile MyMediaPlayer instance = null;
    private  MyMediaPlayer() { }

    public static MyMediaPlayer getInstance() {
        if (instance == null) {
            synchronized (MyMediaPlayer.class)
            {
                if (instance == null)
                {
                    instance = new MyMediaPlayer();
                }
            }
        }

        return instance;
    }
}
