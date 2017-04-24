package com.jainpuja.androidmusicplayer_ver2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;


public class middle extends Activity {
    public TextView title;
    public ScrollView jainkshetra,jainvideo;
    public Button siddhakshetra,kalyanakkshetra,atishaykshetra,jainvideobtn,munipravachanbtn;
    public String value;
    public Button backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle);
        backbutton = (Button) findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.animator.left_in, R.animator.right_out);

            }
        });

       // Create title text
        title = (TextView) findViewById(R.id.title);
        String value1 = getIntent().getExtras().getString("value");
        jainkshetra = (ScrollView) findViewById(R.id.siddhakshetraScroller);
        jainvideo = (ScrollView) findViewById(R.id.Videoscroller);
        System.out.println("Value:"+value1);

        // create a scroller
        if(value1.equals("SIDDHA"))
        {
            title.setText("Kshetra's");
            jainvideo.setVisibility(View.INVISIBLE);
            jainkshetra.setVisibility(View.VISIBLE);
            overridePendingTransition(R.animator.right_in, R.animator.left_out);
        }
        else
        {
            title.setText("Videos");
            jainvideo.setVisibility(View.VISIBLE);
            jainkshetra.setVisibility(View.INVISIBLE);
            overridePendingTransition(R.animator.right_in, R.animator.left_out);
        }
        siddhakshetra =  (Button) findViewById(R.id.siddha);
        siddhakshetra.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(middle.this, audio.class);
                value = "SIDDHAKSHATRA";
                myIntent.putExtra("value", value);
                middle.this.startActivity(myIntent);

            }
        });
        kalyanakkshetra =  (Button) findViewById(R.id.kalyanak);
        kalyanakkshetra.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(middle.this, audio.class);
                value = "KALYANAKKSHATRA";
                myIntent.putExtra("value", value);
                middle.this.startActivity(myIntent);
                overridePendingTransition(R.animator.right_in, R.animator.left_out);

            }
        });
        atishaykshetra =  (Button) findViewById(R.id.atishay);
        atishaykshetra.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(middle.this, audio.class);
                value = "ATISHAYKSHATRA";
                myIntent.putExtra("value", value);
                middle.this.startActivity(myIntent);
                overridePendingTransition(R.animator.right_in, R.animator.left_out);

            }
        });
        jainvideobtn =  (Button) findViewById(R.id.Jainvideo);
        jainvideobtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(middle.this, audio.class);
                value = "BHAJANVIDCATEGORY";
                myIntent.putExtra("value", value);
                middle.this.startActivity(myIntent);
                overridePendingTransition(R.animator.right_in, R.animator.left_out);

            }
        });
        munipravachanbtn =  (Button) findViewById(R.id.Jainmuni);
        munipravachanbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(middle.this, audio.class);
                value = "VIDCATEGORY";
                myIntent.putExtra("value", value);
                middle.this.startActivity(myIntent);
                overridePendingTransition(R.animator.right_in, R.animator.left_out);

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.animator.left_in, R.animator.right_out);}


}
