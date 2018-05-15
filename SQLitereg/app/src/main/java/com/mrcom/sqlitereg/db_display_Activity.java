package com.mrcom.sqlitereg;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class db_display_Activity extends AppCompatActivity
{
    public static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE=200;
    public static final int MEDIA_TYPE_VIDEO=2;
    public static final String DIRECTORY_NAME="SQLiteReg";
    public static final String SUB_DIRECTORY_NAME="LOVE";
    private Uri fileUri;

    public static final String LOG_TAG="Record_log";
    private  String mFilename;
    private Handler timerHandler;
    private Runnable timerRunnable;
    long startTime=0;
    MediaRecorder mediaRecorder;

    Button view,btn_rec,btn_cam_rec,btn_vid_view,btn_pus_res,btn_delete;
    TextView text_rec;
    VideoView vid_vew;

    DB_HELPER mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_display_);
        view=(Button)findViewById(R.id.btn_View);
        btn_rec=(Button)findViewById(R.id.btn_rec);
        btn_vid_view=(Button)findViewById(R.id.btn_vid_strt_stp);
        btn_delete=(Button)findViewById(R.id.btn_del);
        btn_cam_rec=(Button)findViewById(R.id.btn_cam_rec);
        btn_pus_res=(Button)findViewById(R.id.btn_pus_res);
        text_rec=(TextView) findViewById(R.id.text_rec);
        vid_vew=(VideoView)findViewById(R.id.vid_view);
        mydb=new DB_HELPER(this);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Cursor res=mydb.getAlldata();
                if(res.getCount()==0)
                {
                    //message
                    Showmsg("Error","There are no tables are created");
                    return;
                }
                StringBuffer bfr=new StringBuffer();
                while(res.moveToNext())
                {
                    bfr.append("id :"+res.getString(0)+"\n");
                    bfr.append("name :"+res.getString(1)+"\n");
                    bfr.append("password :"+res.getString(2)+"\n");
                    bfr.append("age :"+res.getString(3)+"\n\n");
                }
                Showmsg("data",bfr.toString());
            }
        });

        btn_pus_res.setOnClickListener(new View.OnClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
             Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });


        btn_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(btn_rec.getText().toString().equals("Start"))
                {
                    startTime = System.currentTimeMillis();
                    timerHandler.postDelayed(timerRunnable, 0);
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy_hh-mm");
                    String formattedDate = df.format(c.getTime());
                    mFilename=Environment.getExternalStorageDirectory().getAbsolutePath()+"/AUD_"+formattedDate+".mp3";

                    btn_rec.setText("Stop");
                    StartRecording();


                }
                else if(btn_rec.getText().toString().equals("Stop"))
                {
                    btn_rec.setText("Start");
                    stopRecording();
                    timerHandler.removeCallbacks(timerRunnable);
                    text_rec.setText("Tap And Hold Start To Record");
                }
            }
        });

        btn_vid_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_vid_view.getText().toString().equals("Play"))
                {

                    btn_vid_view.setText("Stop Playing");
                    vid_vew.start();
                }
            }
        });

        timerHandler = new Handler();
        timerRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                long millis = System.currentTimeMillis() - startTime;

                int seconds = (int) (millis / 1000);
                int minutes = seconds / 60;
                int hr=minutes/60;
                seconds = seconds % 60;

                text_rec.setText(String.format("%d:%d:%02d",hr, minutes, seconds));
                timerHandler.postDelayed(this, 1000);
	             }
        };

        btn_cam_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {


                    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                    // create a file to save the video
                    fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);

                    // set the image file name
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                    // set the video image quality to high
                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

                    // start the Video Capture Intent
                    startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cur=mydb.getAlldata();
                if(cur.getCount()==0)
                {
                    //message
                    Toast.makeText(getApplicationContext(),"There are no data's in the table",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    boolean isDeleted = mydb.deleteAll();
                    if (isDeleted)
                    {
                        Toast.makeText(getApplicationContext(), "Delete all data in the table", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private boolean isDeviceSupportCamera()
    {
        if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) { return true; } else { return false;}
    }
    public void Showmsg(String title,String msg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }

    public void StartRecording()
    {
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mediaRecorder.setOutputFile(mFilename);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try
        {
            mediaRecorder.prepare();

        }
        catch (IOException e)
        {
            Log.e(LOG_TAG,"prepare failed");
        }
        mediaRecorder.start();
    }
    private void stopRecording()
    {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder=null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // After camera screen this code will executed

        if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //fileUri=data.getData();
                vid_vew.setVideoURI(fileUri);
                // Video captured and saved to fileUri specified in the Intent
                Toast.makeText(this, "Video saved.", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the video capture
                Toast.makeText(this, "User cancelled the video capture.", Toast.LENGTH_LONG).show();
            } else {
                // Video capture failed, advise user
                Toast.makeText(this, "Video capture failed.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private static Uri getOutputMediaFileUri(int type)
    {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type)
    {
        File mediaStorDir=new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_NAME),SUB_DIRECTORY_NAME);
        if(!mediaStorDir.exists())
        {
            if(!mediaStorDir.mkdirs())
            {
                //Log.d(DIRECTORY_NAME,"Not created "+DIRECTORY_NAME+"Directory");
                return  null;
            }
        }

        Date date= new Date();
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy_hh-mm", Locale.getDefault()).format(date.getTime());

        File mediaFile;

        if(type == MEDIA_TYPE_VIDEO)
        {
            // For unique video file name appending current timeStamp with file name
            mediaFile = new File(mediaStorDir.getAbsolutePath() + "/" + "VID_"+ timeStamp + ".mp4");
        }
        else
        {
            return null;
        }

        return mediaFile;
    }
}
