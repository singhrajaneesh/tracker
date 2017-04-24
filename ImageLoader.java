package com.jainpuja.androidmusicplayer_ver2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.ImageView;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ImageLoader
{

    ExecutorService executorService;

    public ImageLoader(Context context)
    {
//        fileCache=new FileCache(context);
        executorService=Executors.newFixedThreadPool(5);
    }

    final int stub_id = R.drawable.jainpuja;
    public void DisplayImage(String url, ImageView imageView)
    {

        url = Environment.getExternalStorageDirectory()+ "/jainpuja/" +url;
        File file = new File(url);
        if(file.exists())
        {
            Bitmap bitmap = BitmapFactory.decodeFile(url);
            imageView.setImageBitmap(bitmap);
        }

        else
        {
            String localfile = Environment.getExternalStorageDirectory()+ "/jainpuja/"+"jainpuja.png"  ;
            Bitmap bitmap = BitmapFactory.decodeFile(localfile);
            imageView.setImageBitmap(bitmap);
        }
    }



}
