package com.example.diary;

import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Audio_Rec_Activity extends AppCompatActivity {
    ImageView img1;
    TextView run_time;
    FloatingActionButton aud_rec;

    public static final String LOG_TAG="Record_log";
    //private  String mFilename;
    File mFilename;
    MediaRecorder mediaRecorder;
    private Uri fileUri;

    private Handler timerHandler;
    private Runnable timerRunnable;
    long startTime=0;
    Boolean playing=false;
    Boolean recording=false;
    File audiofile=User.getOutputMediaFile();
    DB_HELPER db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio__rec_);
        db=new DB_HELPER(this);
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

                run_time.setText(String.format("%d:%d:%02d",hr, minutes, seconds));
                timerHandler.postDelayed(this, 1000);
            }
        };
        run_time=(TextView)findViewById(R.id.textView);
        //img1=(ImageView)findViewById(R.id.img_pause);
        aud_rec=(FloatingActionButton) findViewById(R.id.fab_rec);
        /*img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playing) {
                    playing=false;
                    img1.setImageResource(R.drawable.aud_rec2);
                    //mediaRecorder.resume();
                }else
                {
                    playing=true;
                    img1.setImageResource(R.drawable.aud_rec1);
                }

            }
        });*/

        aud_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (recording) {
                   recording=false;
                   //img1.setVisibility(View.INVISIBLE);
                   startTime = System.currentTimeMillis();
                   timerHandler.postDelayed(timerRunnable, 0);
                   aud_rec.setImageResource(R.drawable.ic_audiomic);
                   //img1.setImageResource(R.drawable.aud_rec2);
                   timerHandler.removeCallbacks(timerRunnable);
                   run_time.setText("00:00:00");
                   stopRecording();

                   Boolean isadddata=db.insertfile(User.dryid,"file://"+User.audiofilepath,"Audio");
                   if (isadddata)
                   {
                       Toast.makeText(getApplicationContext(),"Audio Is Saved",Toast.LENGTH_SHORT).show();
                   }
                   else
                   {
                       Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                   }


               }
               else
               {
                   recording=true;
                   //img1.setVisibility(View.VISIBLE);
                   startTime = System.currentTimeMillis();
                   timerHandler.postDelayed(timerRunnable, 0);
                   aud_rec.setImageResource(R.drawable.ic_stop);
                   //img1.setImageResource(R.drawable.aud_rec2);
                   Calendar c = Calendar.getInstance();
                   SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy_hh-mm");
                   String formattedDate = df.format(c.getTime());
                   mFilename= audiofile;
                   User.audiofilepath=String.valueOf(mFilename);
                   StartRecording();
               }
            }
        });
    }

    public void StartRecording()
    {
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mediaRecorder.setOutputFile(User.audiofilepath);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try
        {
            mediaRecorder.prepare();

        }
        catch (IOException e)
        {
            Toast.makeText(getApplicationContext(),"Recording Failed",Toast.LENGTH_SHORT).show();
        }
        mediaRecorder.start();
    }
    private void stopRecording()
    {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder=null;
    }

}
