package com.jainpuja.androidmusicplayer_ver2;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;




public class subactivity extends Activity implements NumberPicker.OnValueChangeListener
{

    public WebView webView;
    public File jainpujadir;
    public String filename;
    public Button backbutton;
    DownloadFileAsync asyncFetch;
    TouchImageView img;
    TextView totalpage,pageno;
    int i,indexvalue;
    public TextView title;
    File file;

    String [] pics = {"pic-19.png","pic-20.png","pic-21.png","pic-22.png","pic-23.png","pic-24.png","pic-25.png","pic-26.png","pic-27.png","pic-28.png","pic-29.png","pic-30.png","pic-31.png","pic-32.png","pic-33.png","pic-34.png","pic-35.png","pic-36.png","pic-37.png","pic-38.png","pic-39.png","pic-40.png","pic-41.png","pic-42.png","pic-43.png","pic-44.png","pic-45.png","pic-46.png","pic-47.png","pic-48.png","pic-49.png","pic-50.png","pic-51.png","pic-52.png","pic-53.png","pic-54.png","pic-55.png","pic-56.png","pic-57.png","pic-58.png","pic-59.png","pic-60.png","pic-61.png","pic-62.png","pic-63.png","pic-64.png","pic-65.png","pic-66.png","pic-67.png","pic-68.png","pic-69.png","pic-70.png","pic-71.png","pic-72.png","pic-73.png","pic-74.png","pic-75.png","pic-76.png","pic-77.png","pic-78.png","pic-79.png","pic-80.png","pic-81.png","pic-82.png","pic-83.png","pic-84.png","pic-85.png","pic-86.png","pic-87.png","pic-88.png","pic-89.png","pic-90.png","pic-91.png","pic-92.png","pic-93.png","pic-94.png","pic-95.png","pic-96.png","pic-97.png","pic-98.png","pic-99.png","pic-100.png","pic-101.png","pic-102.png","pic-103.png","pic-104.png","pic-105.png","pic-106.png","pic-107.png","pic-108.png","pic-109.png","pic-110.png","pic-111.png","pic-112.png","pic-113.png","pic-114.png","pic-115.png","pic-116.png","pic-117.png","pic-118.png","pic-119.png","pic-120.png","pic-121.png","pic-122.png","pic-123.png","pic-124.png","pic-125.png","pic-126.png","pic-127.png","pic-128.png","pic-129.png","pic-130.png","pic-131.png","pic-132.png","pic-133.png","pic-134.png","pic-135.png","pic-136.png","pic-137.png","pic-138.png","pic-139.png","pic-140.png","pic-141.png","pic-142.png","pic-143.png","pic-144.png","pic-145.png","pic-146.png","pic-147.png","pic-148.png","pic-149.png","pic-150.png","pic-151.png","pic-152.png","pic-153.png","pic-154.png","pic-155.png","pic-156.png","pic-157.png","pic-158.png","pic-159.png","pic-160.png","pic-161.png","pic-162.png","pic-163.png","pic-164.png","pic-165.png","pic-166.png","pic-167.png","pic-168.png","pic-169.png","pic-170.png","pic-171.png","pic-172.png","pic-173.png","pic-174.png","pic-175.png","pic-176.png","pic-177.png","pic-178.png","pic-179.png","pic-180.png","pic-181.png","pic-182.png","pic-183.png","pic-184.png","pic-185.png","pic-186.png","pic-187.png","pic-188.png","pic-189.png","pic-190.png","pic-191.png","pic-192.png","pic-193.png","pic-194.png","pic-195.png","pic-196.png","pic-197.png","pic-198.png","pic-199.png","pic-200.png","pic-201.png","pic-202.png","pic-203.png","pic-204.png","pic-205.png","pic-206.png","pic-207.png","pic-208.png","pic-209.png","pic-210.png","pic-211.png","pic-212.png","pic-213.png","pic-214.png","pic-215.png","pic-216.png","pic-217.png","pic-218.png","pic-219.png","pic-220.png","pic-221.png","pic-222.png","pic-223.png","pic-224.png","pic-225.png","pic-226.png","pic-227.png","pic-228.png","pic-229.png","pic-230.png","pic-231.png","pic-232.png","pic-233.png","pic-234.png","pic-235.png","pic-236.png","pic-237.png","pic-238.png","pic-239.png","pic-240.png","pic-241.png","pic-242.png","pic-243.png","pic-244.png","pic-245.png","pic-246.png","pic-247.png","pic-248.png","pic-249.png","pic-250.png","pic-251.png","pic-252.png","pic-253.png","pic-254.png","pic-255.png","pic-256.png","pic-257.png","pic-258.png","pic-259.png","pic-260.png","pic-261.png","pic-262.png","pic-263.png","pic-264.png","pic-265.png","pic-266.png","pic-267.png","pic-268.png","pic-269.png","pic-270.png","pic-271.png","pic-272.png","pic-273.png","pic-274.png","pic-275.png","pic-276.png","pic-277.png","pic-278.png","pic-279.png","pic-280.png","pic-281.png","pic-282.png","pic-283.png","pic-284.png","pic-285.png","pic-286.png","pic-287.png","pic-288.png","pic-289.png","pic-290.png","pic-291.png","pic-292.png","pic-293.png","pic-294.png","pic-295.png","pic-296.png","pic-297.png","pic-298.png","pic-299.png","pic-300.png","pic-301.png","pic-302.png","pic-303.png","pic-304.png","pic-305.png","pic-306.png","pic-307.png","pic-308.png","pic-309.png","pic-310.png","pic-311.png","pic-312.png","pic-313.png","pic-314.png","pic-315.png","pic-316.png","pic-317.png","pic-318.png","pic-319.png","pic-320.png","pic-321.png","pic-322.png","pic-323.png","pic-324.png","pic-325.png","pic-326.png","pic-327.png","pic-328.png","pic-329.png","pic-330.png","pic-331.png","pic-332.png","pic-333.png","pic-334.png","pic-335.png","pic-336.png","pic-337.png","pic-338.png","pic-339.png","pic-340.png","pic-341.png","pic-342.png","pic-343.png","pic-344.png","pic-345.png","pic-346.png","pic-347.png","pic-348.png","pic-349.png","pic-350.png","pic-351.png","pic-352.png","pic-353.png","pic-354.png","pic-355.png","pic-356.png","pic-357.png","pic-358.png","pic-359.png","pic-360.png","pic-361.png","pic-362.png","pic-363.png","pic-364.png","pic-365.png","pic-366.png","pic-367.png","pic-368.png","pic-369.png","pic-370.png","pic-371.png","pic-372.png","pic-373.png","pic-374.png","pic-375.png","pic-376.png","pic-377.png","pic-378.png","pic-379.png","pic-380.png","pic-381.png","pic-382.png","pic-383.png","pic-384.png","pic-385.png","pic-386.png","pic-387.png","pic-388.png","pic-389.png","pic-390.png","pic-391.png","pic-392.png","pic-393.png","pic-394.png","pic-395.png","pic-396.png","pic-397.png","pic-398.png","pic-399.png","pic-400.png","pic-401.png","pic-402.png","pic-403.png","pic-404.png","pic-405.png","pic-406.png","pic-407.png","pic-408.png","pic-409.png","pic-410.png","pic-411.png","pic-412.png","pic-413.png","pic-414.png","pic-415.png","pic-416.png","pic-417.png","pic-418.png","pic-419.png","pic-420.png","pic-421.png","pic-422.png","pic-423.png","pic-424.png","pic-425.png","pic-426.png","pic-427.png","pic-428.png","pic-429.png","pic-430.png","pic-431.png","pic-432.png","pic-433.png","pic-434.png","pic-435.png","pic-436.png","pic-437.png","pic-438.png","pic-439.png","pic-440.png","pic-441.png","pic-442.png","pic-443.png","pic-444.png","pic-445.png","pic-446.png","pic-447.png","pic-448.png","pic-449.png","pic-450.png","pic-451.png","pic-452.png","pic-453.png","pic-454.png","pic-455.png","pic-456.png","pic-457.png","pic-458.png","pic-459.png","pic-460.png","pic-461.png","pic-462.png","pic-463.png","pic-464.png","pic-465.png","pic-466.png","pic-467.png","pic-468.png","pic-469.png","pic-470.png","pic-471.png","pic-472.png","pic-473.png","pic-474.png","pic-475.png","pic-476.png","pic-477.png","pic-478.png","pic-479.png","pic-480.png","pic-481.png","pic-482.png","pic-483.png","pic-484.png","pic-485.png","pic-486.png","pic-487.png","pic-488.png","pic-489.png","pic-490.png","pic-491.png","pic-492.png","pic-493.png","pic-494.png","pic-495.png","pic-496.png","pic-497.png","pic-498.png","pic-499.png","pic-500.png","pic-501.png","pic-502.png","pic-503.png","pic-504.png","pic-505.png","pic-506.png","pic-507.png","pic-508.png","pic-509.png","pic-510.png","pic-511.png","pic-512.png","pic-513.png","pic-514.png","pic-515.png","pic-516.png","pic-517.png","pic-518.png","pic-519.png","pic-520.png","pic-521.png","pic-522.png","pic-523.png","pic-524.png","pic-525.png","pic-526.png","pic-527.png","pic-528.png","pic-529.png","pic-530.png","pic-531.png","pic-532.png","pic-533.png","pic-534.png","pic-535.png","pic-536.png","pic-537.png","pic-538.png","pic-539.png","pic-540.png","pic-541.png","pic-542.png","pic-543.png","pic-544.png","pic-545.png","pic-546.png","pic-547.png","pic-548.png","pic-549.png","pic-550.png","pic-551.png","pic-552.png","pic-553.png","pic-554.png","pic-555.png","pic-556.png","pic-557.png","pic-558.png","pic-559.png","pic-560.png","pic-561.png","pic-562.png","pic-563.png","pic-564.png","pic-565.png","pic-566.png","pic-567.png","pic-568.png","pic-569.png","pic-570.png","pic-571.png","pic-572.png","pic-573.png","pic-574.png","pic-575.png","pic-576.png","pic-576.png","pic-578.png","pic-579.png","pic-580.png","pic-581.png","pic-582.png","pic-583.png","pic-584.png","pic-585.png","pic-586.png","pic-587.png","pic-588.png","pic-589.png","pic-590.png","pic-591.png","pic-592.png","pic-593.png","pic-594.png","pic-595.png","pic-596.png","pic-597.png","pic-598.png","pic-599.png","pic-600.png","pic-601.png","pic-602.png","pic-603.png","pic-604.png","pic-605.png","pic-606.png","pic-607.png","pic-608.png","pic-609.png","pic-610.png","pic-611.png","pic-612.png","pic-613.png","pic-614.png","pic-615.png","pic-616.png","pic-617.png","pic-618.png","pic-619.png","pic-20.png","pic-621.png","pic-622.png","pic-623.png","pic-624.png","pic-625.png","pic-626.png","pic-627.png","pic-628.png","pic-629.png","pic-630.png","pic-631.png","pic-632.png","pic-633.png","pic-634.png","pic-635.png","pic-636.png","pic-637.png","pic-638.png","pic-639.png","pic-640.png","pic-641.png","pic-642.png","pic-643.png","pic-644.png","pic-645.png","pic-646.png","pic-647.png","pic-648.png","pic-649.png","pic-650.png","pic-651.png","pic-652.png","pic-653.png","pic-654.png","pic-655.png","pic-656.png","pic-657.png","pic-658.png","pic-659.png","pic-660.png","pic-661.png","pic-662.png","pic-663.png","pic-664.png","pic-665.png","pic-666.png","pic-667.png","pic-668.png","pic-669.png","pic-670.png","pic-671.png","pic-672.png","pic-673.png","pic-674.png","pic-675.png","pic-676.png","pic-677.png","pic-678.png","pic-679.png","pic-680.png","pic-681.png","pic-682.png","pic-683.png","pic-684.png","pic-685.png","pic-686.png","pic-687.png","pic-688.png","pic-689.png","pic-690.png","pic-691.png","pic-692.png","pic-693.png","pic-694.png","pic-695.png","pic-696.png","pic-697.png","pic-698.png","pic-699.png","pic-700.png","pic-701.png","pic-702.png","pic-703.png","pic-704.png","pic-705.png","pic-706.png","pic-707.png","pic-708.png","pic-709.png","pic-710.png","pic-711.png","pic-712.png","pic-713.png","pic-714.png","pic-715.png","pic-716.png","pic-717.png","pic-718.png","pic-719.png","pic-720.png","pic-721.png","pic-722.png","pic-723.png","pic-724.png","pic-725.png","pic-726.png","pic-727.png","pic-728.png","pic-729.png","pic-730.png","pic-731.png","pic-732.png","pic-733.png","pic-734.png","pic-735.png","pic-736.png","pic-737.png","pic-738.png","pic-739.png","pic-740.png","pic-741.png","pic-742.png","pic-743.png","pic-744.png","pic-745.png","pic-746.png","pic-747.png","pic-748.png","pic-749.png","pic-750.png","pic-751.png"};
    GestureDetector gesdetector;
    private ProgressDialog mProgressDialog;
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        System.out.println("subactivity start");
        setContentView(R.layout.activity_subactivity);
        String received_value = getIntent().getExtras().getString("value");
        System.out.println("received_value:"+received_value);
        title = (TextView) findViewById(R.id.title);
        totalpage = (TextView)findViewById(R.id.totalpage);
        totalpage.setText("/ 751");

        pageno = (TextView)findViewById(R.id.pageno);
        jainpujadir = new File(Environment.getExternalStorageDirectory() + "/JainPuja/.JainJinwani");
        if (!jainpujadir.exists()) {
            jainpujadir.mkdirs();
            File file1 = new File(jainpujadir,".nomedia");
            try {
                boolean fileCreated = file1.createNewFile();
                System.out.println("fileCreated:"+fileCreated);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else
            Log.d("error", "dir. already exists");



        backbutton = (Button) findViewById(R.id.backbutton);
        webView = (WebView) findViewById(R.id.webView1);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();

            }
        });
       if(received_value.contains("http://"))
       {
           System.out.println("if called");
           if(received_value.contains("aarti")) {
               title.setText("Puja and Aarti");
           }

           else
           {
               title.setText("About Kshetras");
           }
           // Create a web view to load aarti and kshetra pages
           WebSettings webSettings = webView.getSettings();
           webSettings.setBuiltInZoomControls(true);
           webSettings.setJavaScriptEnabled(true);
           webView.setWebViewClient(new Callback());
           webView.loadUrl(received_value);
           webSettings.setSupportZoom(false);



       }
        else
       {
           webView.setVisibility(View.INVISIBLE);
           backbutton.setVisibility(View.VISIBLE);
           title.setText("Jinwani");
           RelativeLayout lL = (RelativeLayout) findViewById(R.id.mylayout);
           img = new TouchImageView(this);
           img.setMaxZoom(3f);
           lL.addView(img);

           for (i = 0; i <= pics.length; i++ ) {
               if (received_value.equals(pics[i]))
               {
                   indexvalue = i;
                   file = new File(jainpujadir+"/"+pics[indexvalue]);

                   if (file.exists())
                   {
                       String local_file = jainpujadir +"/"+ pics[indexvalue]  ;
                       String[] separated = pics[indexvalue].split("-");
                       String pagecount =  separated[1].substring(0, separated[1].length()-4);
                       pageno.setText(pagecount);
                       Bitmap bitmap = BitmapFactory.decodeFile(local_file);
                       img.setImageBitmap(bitmap);
                       Toast.makeText(getApplicationContext(), "Use pinch gesture to zoom in and zoom out", Toast.LENGTH_LONG).show();
                   }
                   else
                   {
                       String[] separated = pics[indexvalue].split("-");
                       String pagecount =  separated[1].substring(0, separated[1].length()-4);
                       pageno.setText(pagecount);
                       String image_url = "http://www.jainpuja.com/mobileapp/jinwani/" + pics[indexvalue];
                       asyncFetch = new DownloadFileAsync();
                       asyncFetch.execute(image_url, pics[indexvalue]);
                   }
                   //RelativeLayout bottomlayout = (RelativeLayout) findViewById(R.id.bottom);
                   ImageButton backward =(ImageButton) findViewById(R.id.backwardbutton);
                   backward.setImageResource(R.drawable.backward);
                   backward.setOnClickListener(new View.OnClickListener() {
                       public void onClick(View v) {
                           if(indexvalue>0) {
                               indexvalue = indexvalue - 1;
                           }
                           file = new File(jainpujadir+"/"+pics[indexvalue]);
                           if (file.exists())
                           {
                               String local_file = jainpujadir +"/"+ pics[indexvalue]  ;
                               String[] separated = pics[indexvalue].split("-");
                               String pagecount =  separated[1].substring(0, separated[1].length()-4);
                               pageno.setText(pagecount);
                               Bitmap bitmap = BitmapFactory.decodeFile(local_file);
                               img.setImageBitmap(bitmap);
                               Toast.makeText(getApplicationContext(), "Use pinch gesture to zoom in and zoom out", Toast.LENGTH_LONG).show();
                           }
                           else
                           {
                               String[] separated = pics[indexvalue].split("-");
                               String pagecount =  separated[1].substring(0, separated[1].length()-4);
                               pageno.setText(pagecount);
                               String image_url = "http://www.jainpuja.com/mobileapp/jinwani/" + pics[indexvalue];
                               asyncFetch = new DownloadFileAsync();
                               asyncFetch.execute(image_url, pics[indexvalue]);
                           }
                       }
                   });

                   ImageButton forward = (ImageButton) findViewById(R.id.forwardbutton);
                   forward.setImageResource(R.drawable.forward);
                   forward.setOnClickListener(new View.OnClickListener() {
                       public void onClick(View v) {
                           indexvalue = indexvalue + 1;
                           file = new File(jainpujadir+"/"+pics[indexvalue]);
                           if (file.exists())
                           {
                               String[] separated = pics[indexvalue].split("-");
                               String pagecount =  separated[1].substring(0, separated[1].length()-4);
                               pageno.setText(pagecount);
                               String local_file = jainpujadir +"/"+ pics[indexvalue]  ;
                               Bitmap bitmap = BitmapFactory.decodeFile(local_file);
                               img.setImageBitmap(bitmap);
                               Toast.makeText(getApplicationContext(), "Use pinch gesture to zoom in and zoom out", Toast.LENGTH_LONG).show();
                           }
                           else
                           {
                               String[] separated = pics[indexvalue].split("-");
                               String pagecount =  separated[1].substring(0, separated[1].length()-4);
                               pageno.setText(pagecount);
                               String image_url = "http://www.jainpuja.com/mobileapp/jinwani/" + pics[indexvalue];
                               asyncFetch = new DownloadFileAsync();
                               asyncFetch.execute(image_url, pics[indexvalue]);
                           }

                       }
                   });
                   Button goBtn = (Button) findViewById(R.id.gobutton);
                   goBtn.setText("  Go  ");
                   goBtn.setOnClickListener(new View.OnClickListener() {

                      @Override
                       public void onClick(View v)
                      {
                           System.out.println("Go Btn clicked");
                           show();
                       }
                   });






                   //totalpage.setText("/ 751");


                   RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                           RelativeLayout.LayoutParams.MATCH_PARENT,
                           RelativeLayout.LayoutParams.MATCH_PARENT
                   );
                   params.setMargins(10, 10, 10, 10);
                   img.setLayoutParams(params);




                   break;
               }

           }
       }
     }

    public void show()
    {

        final Dialog d = new Dialog(subactivity.this);
        d.setTitle("Go To Page No.");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(751);
        np.setMinValue(19);
        int setvalue = indexvalue+19;
        np.setValue(setvalue);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                int value = np.getValue();
                value = value - 19;
                indexvalue = value;
                String[] separated = pics[indexvalue].split("-");
                String pagecount =  separated[1].substring(0, separated[1].length()-4);
                pageno.setText(pagecount);
                file = new File(jainpujadir+"/"+pics[indexvalue]);
                String absolutePath = file.getAbsolutePath();
                System.out.println("absolutePath:"+absolutePath);
                System.out.println("value:"+value);
                if (file.exists())
                {
                    String local_file = jainpujadir +"/"+ pics[indexvalue]  ;
                    Bitmap bitmap = BitmapFactory.decodeFile(local_file);
                    img.setImageBitmap(bitmap);
                    Toast.makeText(getApplicationContext(), "Use pinch gesture to zoom in and zoom out", Toast.LENGTH_LONG).show();
                }
                else
                {
                    String image_url = "http://www.jainpuja.com/mobileapp/jinwani/" + pics[indexvalue];
                    asyncFetch = new DownloadFileAsync();
                    asyncFetch.execute(image_url, pics[indexvalue]);
                }
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }



    @Override
    public void onBackPressed() {
        if (asyncFetch != null)
        asyncFetch.cancel(true);
        finish();
        super.onBackPressed();
        overridePendingTransition(R.animator.left_in, R.animator.right_out);
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_DOWNLOAD_PROGRESS:
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setMessage("Loading Image..");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.show();
                return mProgressDialog;
            default:
                return null;
        }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }

    public class DownloadFileAsync extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(DIALOG_DOWNLOAD_PROGRESS);
            if (!jainpujadir.exists())
                jainpujadir.mkdirs();
            else
                Log.d("error", "dir. already exists");
        }

        @Override
        protected String doInBackground(String... aurl) {
            int count;

            try {

                URL url = new URL(aurl[0]);
                String imagename = aurl[1];
                URLConnection conexion = url.openConnection();
                conexion.connect();
                int lenghtOfFile = conexion.getContentLength();
                System.out.println("lenghtOfFile:"+lenghtOfFile);
                filename = jainpujadir + "/"+imagename;
                InputStream input = new BufferedInputStream(url.openStream());
                OutputStream output = new FileOutputStream(filename);
                byte data[] = new byte[1024];
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
            } catch (Exception e)
            {
                System.out.println("Exception:"+e);
            }
            return null;

        }

        protected void onProgressUpdate(String... progress)
        {

            mProgressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        @Override
        protected void onPostExecute(String unused)
        {
            try
            {


                Bitmap bitmap = BitmapFactory.decodeFile(filename);
                try {

                    img.setImageBitmap(bitmap);
                }
                catch (Exception e)
                {

                }
            }
            catch (Exception e)
            {

            }
            dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
            Toast.makeText(getApplicationContext(), "Use pinch gesture to zoom in and zoom out", Toast.LENGTH_LONG).show();
            mProgressDialog.setProgress(Integer.parseInt("0"));
      }

    }
    class Callback extends WebViewClient {



        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mProgressDialog.dismiss();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon)
        {


            super.onPageStarted(view, url, favicon);
            System.out.println("url:"+url);
            mProgressDialog = new ProgressDialog(subactivity.this);
            mProgressDialog.setMessage("Loading data... \n\nThis might take a while");
            mProgressDialog.show();
        }
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            webView.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), "Web Page not loaded", Toast.LENGTH_LONG).show();
             finish();
        }
    }



}



































//    ListView list;
//    InputSource inputSource = null;
//
//    Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_subactivity);
//        list = (ListView)findViewById(R.id.simpleList);
//        bindDataToListing();
//    }
//
//    private void bindDataToListing() {
//        try {
//            SAXParserFactory saxparser = SAXParserFactory.newInstance();
//            SAXParser parser = saxparser.newSAXParser();
//            XMLReader xmlReader = parser.getXMLReader();
//            File xmlFile = new File( Environment.getExternalStorageDirectory()+ "/jainpuja/jainpuja.xml");
//            FileInputStream xmlFileInputStream = new FileInputStream(xmlFile);
//            inputSource = new InputSource(xmlFileInputStream);
//            ParsingClass pc = new ParsingClass();
//            xmlReader.setContentHandler(pc);
//            xmlReader.parse(new InputSource(xmlFileInputStream));
//            BindingData bindingData = new BindingData(this, pc.thumbnail,pc.songtitle, pc.songengtitle, pc.songlength);
//            list.setAdapter(bindingData);
//        } catch (Exception e) {
//            e.getMessage();
//        }
//    }

