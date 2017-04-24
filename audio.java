package com.jainpuja.androidmusicplayer_ver2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import java.nio.FileUtils;

public class audio extends ListActivity
{

    static String KEY_ID,KEY_NAME,KEY_COST,KEY_DESC,KEY_IMAGE,KEY_CATEGORY,KEY_URL;
    public TextView title;
    ListView list;
    LazyAdapter adapter;
    public List<String> name;
    public List<String> category;
    public List<String> songname,songurl,aartiurl,imageurl;
    ArrayAdapter<String> listAdapter;
    private MyMediaPlayer player = MyMediaPlayer.getInstance();
    public Button backbutton;
    public int length;



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        String path = Environment.getExternalStorageDirectory()+ "/jainpuja/jainpuja.xml";
        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();
        title = (TextView) findViewById(R.id.title);
        KEY_ID = getIntent().getExtras().getString("value");
        backbutton = (Button) findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.animator.left_in, R.animator.right_out);

            }
        });

        name = new ArrayList<String>();
        category = new ArrayList<String>();
        songname = new ArrayList<String>();
        songurl = new ArrayList<String>();
        aartiurl  = new ArrayList<String>();
        imageurl  = new ArrayList<String>();
        switch(KEY_ID) {
                case "CATEGORY": {
                    title.setText("Bhajans");
                    KEY_NAME     =  "CATNAME";
                    KEY_COST     =  "CATHINDINAME";
                    KEY_DESC     =  "TOTALBHAJAN";
                    KEY_IMAGE    =  "CATICON";
                    KEY_CATEGORY =  "CATID";
                    overridePendingTransition(R.animator.right_in, R.animator.left_out);
                }

                break;
                case "AARTI":
                {

                    title.setText("Puja and Aarti");
                    KEY_NAME  = "AARTINAME";
                    KEY_COST  = "AARTHINNAME";
                    KEY_DESC  = "AARTIURL";
                    KEY_IMAGE = "AARTIICON";
                    overridePendingTransition(R.animator.right_in, R.animator.left_out);
                }
                break;

                case "CALENDAR":
                {
                    title.setText("Jain Calendar");
                    KEY_NAME  = "MONTHHINNAME";
                    KEY_COST  = "MONTHENGNAME";
                    KEY_DESC  = "MONTHIMAGE";
                    KEY_IMAGE = "MONTHICON";
                    overridePendingTransition(R.animator.right_in, R.animator.left_out);
                }
                break;
                case "DEVDHARSHAN":

                {
                    title.setText("Dev Darshan");
                    KEY_NAME  = "DHARSHANSONG";
                    KEY_COST  = "DHARSHANTITLE";
                    KEY_DESC  = "DHARSHANPIC";
                    KEY_IMAGE = "DHARSHANPICNAME";
                    KEY_URL   = "DHARSHANURL";
                    overridePendingTransition(R.animator.right_in, R.animator.left_out);

                }
                break;
                case "SIDDHAKSHATRA":

                {
                    title.setText("Siddha Kshetra");
                    KEY_NAME = "KSHETRAHINNAME";
                    KEY_COST = "KSHETRANAME";
                    KEY_DESC = "KSHETRAURL";
                    KEY_IMAGE = "KSHETRAICON";
                    overridePendingTransition(R.animator.right_in, R.animator.left_out);


                }
                break;
                case "KALYANAKKSHATRA":

                {
                    title.setText("Kalyanak Kshetra");
                    KEY_NAME = "KSHETRAHINNAME";
                    KEY_COST = "KSHETRANAME";
                    KEY_DESC = "KSHETRAURL";
                    KEY_IMAGE = "KSHETRAICON";
                    overridePendingTransition(R.animator.right_in, R.animator.left_out);


                }
                break;
                case "ATISHAYKSHATRA":

                {
                    title.setText("Atishay Kshetra");
                    KEY_NAME = "KSHETRAHINNAME";
                    KEY_COST = "KSHETRANAME";
                    KEY_DESC = "KSHETRAURL";
                    KEY_IMAGE = "KSHETRAICON";
                    overridePendingTransition(R.animator.right_in, R.animator.left_out);


                }
                break;
                case "BHAJANVIDCATEGORY":

                {
                    title.setText("Bhajan Video");
                    KEY_NAME = "VIDHINCATNAME";
                    KEY_COST = "VIDCATNAME";
                    KEY_DESC = "TOTALVIDEO";
                    KEY_IMAGE = "VIDCATICON";
                    KEY_CATEGORY = "VIDCATID";
                    overridePendingTransition(R.animator.right_in, R.animator.left_out);


                }
                break;
                case "VIDCATEGORY":

                {
                    title.setText("Muni Pravachan");
                    KEY_NAME = "VIDHINCATNAME";
                    KEY_COST = "VIDCATNAME";
                    KEY_DESC = "TOTALVIDEO";
                    KEY_IMAGE = "VIDCATICON";
                    KEY_CATEGORY = "VIDCATID";
                    KEY_URL   = "";
                    overridePendingTransition(R.animator.right_in, R.animator.left_out);

                }
                break;
                default:
                    KEY_ID = null;
                    overridePendingTransition(R.animator.right_in, R.animator.left_out);
                    break;

            }
            XMLParser parser = new XMLParser();
            String xml = parser.getxmlfromSDcard(path); // getting XML
            Document doc = parser.getDomElement(xml); // getting DOM element
            NodeList nl = doc.getElementsByTagName(KEY_ID);

            for (int i = 0; i < nl.getLength(); i++)
            {
                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();
                Element e = (Element) nl.item(i);
                // adding each child node to HashMap key => value
                map.put(KEY_NAME, parser.getValue(e, KEY_NAME));
                map.put(KEY_COST, parser.getValue(e, KEY_COST));
                map.put(KEY_DESC, parser.getValue(e, KEY_DESC));
                map.put(KEY_IMAGE, parser.getValue(e, KEY_IMAGE));
                map.put(KEY_URL, parser.getValue(e, KEY_URL));
                name.add(parser.getValue(e, KEY_COST));
                category.add(parser.getValue(e, KEY_CATEGORY));
                songname.add(parser.getValue(e, KEY_NAME));
                songurl.add(parser.getValue(e, KEY_URL));
                aartiurl.add(parser.getValue(e, KEY_DESC));
                menuItems.add(map);
            }
        System.out.println("category"+category);
        list = getListView();
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header, list,
                false);

        list.addHeaderView(header, null, false);

        ViewGroup footer = (ViewGroup) inflater.inflate(R.layout.footer, list,
                false);

        list.addFooterView(footer, null, false);
        adapter=new LazyAdapter(this, menuItems);
        list.setSelector(R.drawable.your_selecter);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                if (KEY_ID.equals("CATEGORY")) {

                    Intent in = new Intent(getApplicationContext(), songs.class);
                    in.putExtra("value", category.get(position - 1));
                    in.putExtra("value1", name.get(position - 1));
                    startActivity(in);
                    overridePendingTransition(R.animator.right_in, R.animator.left_out);


                } else if (KEY_ID.equals("VIDCATEGORY") || KEY_ID.equals("BHAJANVIDCATEGORY")) {

                    Intent in = new Intent(getApplicationContext(), videos.class);
                    in.putExtra("value", category.get(position - 1));
                    in.putExtra("value1", name.get(position - 1));
                    startActivity(in);
                    overridePendingTransition(R.animator.right_in, R.animator.left_out);

                } else if (KEY_NAME.equals("MONTHHINNAME"))

                {
                    System.out.println("KEY_ID:" + KEY_ID);
                    Intent in = new Intent(getApplicationContext(), Jaincalender.class);

                    in.putExtra("value", aartiurl.get(position - 1));
                    startActivity(in);
                    overridePendingTransition(R.animator.right_in, R.animator.left_out);
                }
                else if (KEY_NAME.equals("AARTINAME") || KEY_NAME.equals("KSHETRAHINNAME"))
                {

                    Intent in = new Intent(getApplicationContext(), subactivity.class);
                    in.putExtra("value", aartiurl.get(position - 1));
                    startActivity(in);
                    overridePendingTransition(R.animator.right_in, R.animator.left_out);
                }
                else
                {

                    Intent in = new Intent(getApplicationContext(), darshan.class);
                    in.putExtra("songname", name.get(position - 1));
                    in.putExtra("url", aartiurl.get(position - 1));
                    in.putExtra("value", songname.get(position - 1));
                    in.putExtra("songurl", songurl.get(position - 1));
                    startActivity(in);
                    overridePendingTransition(R.animator.right_in, R.animator.left_out);
                }
            }
        });



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.animator.left_in, R.animator.right_out);

    }


 }



