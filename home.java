package com.jainpuja.androidmusicplayer_ver2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class home extends Activity

{
    // Splash screen timer
    private static int SPLASH_TIME_OUT =2000;

    public TextView title;
    public ImageView image;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        title = (TextView) findViewById(R.id.title);
        title.setText("Jain Puja");




        handler=new Handler();

        handler.postDelayed(new Runnable()

                            {

         /*
          * Showing splash screen with a timer. This will be useful when you
          * want to show case your app logo / company
          */

            @Override
            public void run()

            {


                Intent i = new Intent(home.this, welcome.class);
                startActivity(i);


                finish();


                overridePendingTransition(R.animator.first, R.animator.second);



            }

        },


                SPLASH_TIME_OUT);

    }

    public void clickme(View view)


    {

        Intent intent=new Intent(home.this,welcome.class);
        startActivity(intent);
        overridePendingTransition(R.animator.first, R.animator.second);

        handler.removeCallbacksAndMessages(null);

    }

}
