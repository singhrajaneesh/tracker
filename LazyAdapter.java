package com.jainpuja.androidmusicplayer_ver2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class LazyAdapter extends BaseAdapter
{
    public TextView duration;
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;
    public ImageLoader imageLoader;

    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d)
    {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.list_item, null);

        TextView title = (TextView) vi.findViewById(R.id.hinname); // title
        TextView artist = (TextView) vi.findViewById(R.id.engname); // artist name
        duration = (TextView) vi.findViewById(R.id.totalbhajan); // duration
        ImageView thumb_image = (ImageView) vi.findViewById(R.id.image); // thumb image
        ImageView arrowimage = (ImageView) vi.findViewById(R.id.arrow);

        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);

        // Setting all values in listview
        if (song.get(audio.KEY_NAME) != null)
        {
            title.setText(song.get(audio.KEY_NAME));
            artist.setText(song.get(audio.KEY_COST));
            if(audio.KEY_NAME.equals("DHARSHANSONG"))
            {
                artist.setText("");
            }

            if (audio.KEY_DESC.equals("TOTALBHAJAN") || audio.KEY_DESC.equals("TOTALVIDEO"))
            {
                duration.setText(song.get(audio.KEY_DESC));
            }
            imageLoader.DisplayImage(song.get(audio.KEY_IMAGE), thumb_image);
        }
        else if(song.get(songs.KEY_NAME)!=null)
        {
            title.setText(song.get(songs.KEY_NAME));
            artist.setText(song.get(songs.KEY_COST));
            if (audio.KEY_DESC.equals("TOTALBHAJAN") || audio.KEY_DESC.equals("TOTALVIDEO"))
            {
                duration.setText(song.get(songs.KEY_DESC));
                arrowimage.setBackgroundResource(R.drawable.icon_play);
            }

            imageLoader.DisplayImage(song.get(songs.KEY_IMAGE), thumb_image);
        }
        else
        {
            title.setText(song.get(videos.KEY_NAME));
            artist.setText(song.get(videos.KEY_COST));
            if (audio.KEY_DESC.equals("TOTALBHAJAN") || audio.KEY_DESC.equals("TOTALVIDEO"))
            {
                duration.setText(song.get(videos.KEY_DESC));
            }
            imageLoader.DisplayImage(song.get(videos.KEY_IMAGE), thumb_image);
        }
        return vi;
    }

}
