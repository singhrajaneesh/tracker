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

public class ListViewCustomAdapter extends  BaseAdapter
    {

        private Activity activity;
        private ArrayList<HashMap<String, String>> data;
        private static LayoutInflater inflater = null;
        public ImageLoader imageLoader;

        public ListViewCustomAdapter(Activity a, ArrayList<HashMap<String, String>> d)
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
            vi = inflater.inflate(R.layout.listitem_row, null);

        TextView title = (TextView) vi.findViewById(R.id.txtViewTitle);
        TextView artist = (TextView) vi.findViewById(R.id.txtViewDescription);
        ImageView thumb_image = (ImageView) vi.findViewById(R.id.imgViewLogo);
        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);
        title.setText(song.get(Jinwani.KEY_NAME));
        artist.setText(song.get(Jinwani.KEY_COST));
        



        return vi;
    }

}
