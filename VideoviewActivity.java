package com.jainpuja.androidmusicplayer_ver2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


public class VideoviewActivity extends Activity {


    private VideoView videoView;
    private MediaController mController;
    private Uri uriYouTube;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoview);
        //String video_path = "https://www.youtube.com/watch?v=EquB96Gvy8M";
        String video_path = getIntent().getExtras().getString("value");
        //https://www.youtube.com/watch?v=qpQtlAW3mag
        try {
            Uri uri = Uri.parse(video_path);
            uri = Uri.parse("vnd.youtube:" + uri.getQueryParameter("v"));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        catch (Exception e)
        {
            System.out.println("Exception :"+e);
            Toast.makeText(getApplicationContext(), "Please install YouTube app from Play Store to play the videos", Toast.LENGTH_LONG).show();
            finish();
        }




    }

    @Override
    public void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(R.animator.left_in, R.animator.right_out);

    }

}





