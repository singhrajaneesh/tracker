package com.jainpuja.androidmusicplayer_ver2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class videos extends ListActivity
{
    public TextView title;
    static String KEY_ID, KEY_NAME, KEY_COST, KEY_DESC, KEY_IMAGE, KEY_CATEGORY,heading;
    ListView list;
    LazyAdapter adapter;
    public Button backbutton;
    public List<String> url,name;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();
        KEY_ID = getIntent().getExtras().getString("value");
        heading = getIntent().getExtras().getString("value1");
        url = new ArrayList<String>();
        title = (TextView) findViewById(R.id.title);
        backbutton = (Button) findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.animator.left_in, R.animator.right_out);

            }
        });
        KEY_NAME = "VIDEOHINTITLE";
        KEY_COST = "VIDEOENGTITLE";
        KEY_DESC = "";
        KEY_IMAGE = "VIDEOTHUMBNAIL";
        KEY_CATEGORY = "VIDEOSONG";
        title.setText(heading);
//        switch (KEY_ID) {
//            case "VIDEOCAT1": {
//
//                title.setText("Acharya Shree 108 Vidya Sagar ji Maharaj");
//
//
//
//            }
//            break;
//            case "VIDEOCAT2": {
//                title.setText("Acharya Shree 108 Kshama Sagar ji Maharaj");
////                KEY_NAME = "VIDEOHINTITLE";
////                KEY_COST = "VIDEOENGTITLE";
////                KEY_DESC = "";
////                KEY_IMAGE = "VIDEOTHUMBNAIL";
////                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "VIDEOCAT3": {
//                title.setText("Acharya Shree 108 Saurabh Sagarji Maharaj");
////                KEY_NAME = "VIDEOHINTITLE";
////                KEY_COST = "VIDEOENGTITLE";
////                KEY_DESC = "";
////                KEY_IMAGE = "VIDEOTHUMBNAIL";
////                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "VIDEOCAT4": {
////                title.setText("Acharya Shree 108 Pulak Sagar ji Maharaj");
////                KEY_NAME = "VIDEOHINTITLE";
////                KEY_COST = "VIDEOENGTITLE";
////                KEY_DESC = "";
////                KEY_IMAGE = "VIDEOTHUMBNAIL";
////                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "VIDEOCAT5": {
//                title.setText("Acharya Shree 108 Sudha Sagar ji Maharaj");
////                KEY_NAME = "VIDEOHINTITLE";
////                KEY_COST = "VIDEOENGTITLE";
////                KEY_DESC = "";
////                KEY_IMAGE = "VIDEOTHUMBNAIL";
////                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "VIDEOCAT6": {
//                title.setText("Acharya Shree 108 Gyan Sagar ji Maharaj");
////                KEY_NAME = "VIDEOHINTITLE";
////                KEY_COST = "VIDEOENGTITLE";
////                KEY_DESC = "";
////                KEY_IMAGE = "VIDEOTHUMBNAIL";
////                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "VIDEOCAT7": {
//                title.setText("Acharya Shree 108 Vishuddha Sagar ji Maharaj");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "VIDEOCAT8": {
//                title.setText("Acharya Shree 108 Pushpdant Sagar ji Maharaj");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "VIDEOCAT9": {
//                title.setText("Acharya Shree 108 Nayan Sagar ji Maharaj");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "VIDEOCAT10": {
//                title.setText("Acharya Shree 108 Pragya Sagar ji Maharaj");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "VIDEOCAT11": {
//                title.setText("Acharya Shree 108 Prasanna Sagar ji Maharaj");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "VIDEOCAT12": {
//                title.setText("Acharya Shree 108 Praman Sagar ji Maharaj");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "VIDEOCAT13": {
//                title.setText("Acharya Shree 108 Punya Sagar ji Maharaj");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "VIDEOCAT14": {
//                title.setText("Acharya Shree 108 Vardhaman Sagar ji Maharaj");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "VIDEOCAT15": {
//                title.setText("Acharya Shree 108 Vibhav Sagar ji Maharaj");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "VIDEOCAT16": {
//                title.setText("Acharya Shree 108 Shrut Sagar ji Maharaj");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "VIDEOCAT17": {
//                title.setText("Acharya Shree 108 Pramukh Sagar ji Maharaj");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "VIDEOCAT18": {
//                title.setText("Acharya Shree 108 Viharsh Sagar ji Maharaj");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "VIDEOCAT19": {
//                title.setText("Acharya Shree 108 Vishok Sagar ji Maharaj");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "VIDEOCAT20": {
//                title.setText("Acharya Shree 108 Arun Sagar ji Maharaj");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//            }
//            break;
//            case "BHAJANVIDEOCAT1": {
//                title.setText("Video - Namokar Mantra");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "BHAJANVIDEOCAT2": {
//                title.setText("Video - Bhaktambar Strotat");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "BHAJANVIDEOCAT3": {
//                title.setText("Video - Aarti and Pooja");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//            case "BHAJANVIDEOCAT4": {
//                title.setText("Video - Bhagwan Chalisa");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//            }
//            break;
//            case "BHAJANVIDEOCAT5": {
//                title.setText("Video - Bhajan");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//            }
//            break;
//            case "BHAJANVIDEOCAT6": {
//                title.setText("Video - Teerth Kshetra");
//                KEY_NAME = "VIDEOHINTITLE";
//                KEY_COST = "VIDEOENGTITLE";
//                KEY_DESC = "";
//                KEY_IMAGE = "VIDEOTHUMBNAIL";
//                KEY_CATEGORY = "VIDEOSONG";
//
//
//            }
//            break;
//        }
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
//            name.add(parser.getValue(e, KEY_NAME));
            menuItems.add(map);
        }
        list = getListView();
        adapter = new LazyAdapter(this, menuItems);
        list.setAdapter(adapter);
    }


    @Override
    protected void onStart()
    {
        super.onStart();
//        Log.i(TAG, "On Start .....");
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                Intent in = new Intent(getApplicationContext(), VideoviewActivity.class);
                    in.putExtra("value", url.get(position));
                    startActivity(in);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.animator.left_in, R.animator.right_out);

    }

}