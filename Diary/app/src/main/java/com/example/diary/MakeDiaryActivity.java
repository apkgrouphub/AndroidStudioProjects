package com.example.diary;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.diary.DB_HELPER.diary_table;
import static com.example.diary.DB_HELPER.reg_col_dob;
import static com.example.diary.DB_HELPER.reg_col_fullname;

public class MakeDiaryActivity extends AppCompatActivity
{

    //video variables
    public static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE=200;
    public static final int MEDIA_TYPE_VIDEO=2;
    public static final String DIRECTORY_NAME="Diary";
    public static final String SUB_DIRECTORY_NAME="dir_video";
    private Uri fileUri;

    FloatingActionButton fab_add,fab_vidrecord,fab_audrecord,fab_fileattach;
    Animation fab_open,fab_close,fab_Rclock,fab_Ranticlock;
    boolean isopen =false;
    Calendar c = Calendar.getInstance();
    TextView date;
    EditText remark,dry_content;
    DB_HELPER db;
    String user_id=LoginActivity.user_id;

    public static final String LOG_TAG="Record_log";
    private  String mFilename;
    private Handler timerHandler;
    private Runnable timerRunnable;
    long startTime=0;
    MediaRecorder mediaRecorder;

    SimpleDateFormat df = new SimpleDateFormat("EEEE|dd-MMMM-yyyy");
    String formattedDate = df.format(c.getTime());
    String nowtime= df.format(c.getTime());
    String dry_id;
    Boolean mitem=false;
    String diary_id;
    MenuItem saorup;
    public static String vidfilename;
    public static  String vfpath;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_diary);

        db=new DB_HELPER(this);
        date=(TextView)findViewById(R.id.date);
        remark=(EditText)findViewById(R.id.text_remark);
        dry_content=(EditText)findViewById(R.id.body);
        date.setText(formattedDate);
        fab_add=(FloatingActionButton)findViewById(R.id.fabadd);
        fab_vidrecord=(FloatingActionButton)findViewById(R.id.fabvidreco);
        fab_audrecord=(FloatingActionButton)findViewById(R.id.fabaudreco);
        fab_fileattach=(FloatingActionButton)findViewById(R.id.fabfileatta);


        Cursor cd=db.getdate(date.getText().toString());


        /*if(!getIntent().getExtras().getString("id").equals("-1"))
        {
                remark.setInputType(InputType.TYPE_NULL);
                dry_content.setInputType(InputType.TYPE_NULL);
                fab_add.setVisibility(View.INVISIBLE);
                Cursor c=db.getdata(getIntent().getExtras().getString("id"));
                c.moveToFirst();
                remark.setText(c.getString(3));
                dry_content.setText(c.getString(4));
                dry_content.setSingleLine(false);
                date.setText(c.getString(2));
        }*/
        if (getIntent().getExtras().getString("id").equals("-1")) {
            if (cd.getCount() == 1) {
                //Toast.makeText(getApplicationContext(), " Digitel Diary Coming Soon. With VideoRecording ", Toast.LENGTH_LONG).show();
                cd.moveToFirst();
                dry_id = cd.getString(0);
                User.dryid = cd.getString(0);
                remark.setText(cd.getString(3));
                dry_content.setText(cd.getString(4));


            }
        }
                fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fabfirstclick);
                fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fabsecondclick);
                fab_Rclock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rot_clockwise);
                fab_Ranticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rot_anticlockwise);

                fab_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (isopen) {
                            fab_audrecord.startAnimation(fab_close);
                            fab_vidrecord.startAnimation(fab_close);
                            //fab_fileattach.startAnimation(fab_close);
                            fab_add.startAnimation(fab_Ranticlock);
                            //fab_fileattach.setClickable(false);
                            fab_audrecord.setClickable(false);
                            fab_vidrecord.setClickable(false);
                            isopen = false;
                        } else {
                            fab_audrecord.startAnimation(fab_open);
                            fab_vidrecord.startAnimation(fab_open);
                            //fab_fileattach.startAnimation(fab_open);
                            fab_add.startAnimation(fab_Rclock);
                            //fab_fileattach.setClickable(true);
                            fab_audrecord.setClickable(true);
                            fab_vidrecord.setClickable(true);
                            isopen = true;
                        }
                    }
                });

                fab_vidrecord.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Toast.makeText(getApplicationContext(), " Digitel Diary Coming Soon. With VideoRecording ", Toast.LENGTH_LONG).show();
                        //showMessage("Coming Soon","Video recording feature is not available now");
                        Cursor cd=db.getdate(date.getText().toString());
                        if (getIntent().getExtras().getString("id").equals("-1")) {
                            if (cd.getCount() == 1)
                            {

                                try {
                                    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                                    // create a file to save the video
                                    fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
                                    User.videofilepath = String.valueOf(fileUri);
                                    vfpath=fileUri.toString();
                                    // set the image file name
                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                                    // set the video image quality to high
                                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

                                    // start the Video Capture Intent
                                    startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Please create and save your diary", Toast.LENGTH_LONG).show();
                            }
                        }


                    }
                });

                fab_audrecord.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(getApplicationContext(), " Digitel Diary Coming Soon. With AudioRecording ", Toast.LENGTH_LONG).show();
                        //showMessage("Coming Soon","Audio recording feature is not available now");
                        Cursor cd=db.getdate(date.getText().toString());
                        if (getIntent().getExtras().getString("id").equals("-1")) {
                            if (cd.getCount() == 1)
                            {
                                Intent r = new Intent(getApplicationContext(), Audio_Rec_Activity.class);
                                startActivity(r);

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Please create and save your diary", Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                });

               /* fab_fileattach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(getApplicationContext(), " Digitel Diary Coming Soon. With FileAttachment ", Toast.LENGTH_LONG).show();
                        showMessage("Coming Soon", "File attachment feature is not available now");
                       // Intent in=new Intent(getApplicationContext(),Diary_View.class);
                        //startActivity(in);
                    }
                });*/
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public static class LineEditText extends EditText
    {
        // we need this constructor for LayoutInflater
        public LineEditText(Context context, AttributeSet attrs)
        {
            super(context, attrs);
            mRect = new Rect();
            mPaint = new Paint();
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setColor(Color.rgb(131,131,131));
        }

        private Rect mRect;
        private Paint mPaint;

        @Override
        protected void onDraw(Canvas canvas) {

            int height = getHeight();
            int line_height = getLineHeight();

            int count = height / line_height;

            if (getLineCount() > count)
                count = getLineCount();

            Rect r = mRect;
            Paint paint = mPaint;
            int baseline = getLineBounds(0, r);

            for (int i = 0; i < count; i++) {

                canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
                baseline += getLineHeight();

                super.onDraw(canvas);
            }

        }
    }

    //video recording
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // After camera screen this code will executed
        if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // Video captured and saved to fileUri specified in the Intent
                //Toast.makeText(this, "Video saved.", Toast.LENGTH_LONG).show();
                Cursor get=db.getdate(date.getText().toString());
                if(get.getCount()==1)
                {
                    Boolean isaddfile=db.insertfile(dry_id,vfpath,"Video");
                    if (isaddfile)
                    {
                        Toast.makeText(this, "Video saved.", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(this, "Pleace create and save your diary", Toast.LENGTH_LONG).show();
                    }



                }
                else
                {
                    Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
                }
                //insert file to db

            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the video capture
                Toast.makeText(this, "User cancelled the video capture.", Toast.LENGTH_LONG).show();
            } else {
                // Video capture failed, advise user
                Toast.makeText(this, "Video capture failed.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static Uri getOutputMediaFileUri(int type)
    {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    public static File getOutputMediaFile(int type)
    {
        Date date= new Date();
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy_hh-mm", Locale.getDefault()).format(date.getTime());

        File mediaFile;

        if(type == MEDIA_TYPE_VIDEO)
        {
            // For unique video file name appending current timeStamp with file name
            File diaryFolder=new File(Environment.getExternalStoragePublicDirectory("Diary"),"Video");
            if(!diaryFolder.exists())
            {
                if(!diaryFolder.mkdirs())
                {
                    //Log.d(DIRECTORY_NAME,"Not created "+DIRECTORY_NAME+"Directory");
                    return  null;
                }
            }
            mediaFile = new File(diaryFolder.getAbsolutePath() + "/" + "VID_"+ timeStamp + ".mp4");
        }
        else
        {
            return null;
        }
        return mediaFile;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        if(!getIntent().getExtras().getString("id").equals("-1"))
        {
            getMenuInflater().inflate(R.menu.main,menu);
            return  false;
        }
        getMenuInflater().inflate(R.menu.main,menu);
        saorup=menu.findItem(R.id.save);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.save:


                    if (!remark.getText().toString().equals(""))
                    {
                        if (dry_content.getText().toString().equals(""))
                        {
                            ShowString("Error","Pleace Write your diary");
                        }
                        else
                        {
                            Cursor cd=db.getdate(date.getText().toString());
                            if (cd.getCount()==1)
                            {
                                cd.moveToFirst();
                                diary_id=cd.getString(0);
                                //ShowString("test","this is a test");
                                try {
                                    boolean isUpdated = db.updatedairy(
                                            diary_id,
                                            remark.getText().toString(),
                                            dry_content.getText().toString());

                                    if (isUpdated) {
                                        ShowString("Diary", "Your diary updated");
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                try {
                                    boolean isInserted = db.insertDiary_noteData(
                                            user_id,
                                            date.getText().toString(),
                                            remark.getText().toString(),
                                            dry_content.getText().toString());

                                    if (isInserted)
                                    {
                                        ShowString("Diary", "Your diary saved");
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                    else
                    {
                        if (dry_content.getText().toString().equals(""))
                        {
                            ShowString("Error","pleace write your diary");
                        }
                        else
                        {
                            Showmsg("Warning","Are you sure You want to \n save the diary without remark");
                        }
                    }

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //database and data is saved

    public void Showmsg(String title,String msg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                try {
                    String remark_no_text="No Remark";
                    boolean isInserted = db.insertDiary_noteData(
                            user_id,
                            date.getText().toString(),
                            remark_no_text,
                            dry_content.getText().toString());

                    if (isInserted)
                    {
                        ShowString("Diary","Your diary saved");
                        remark.setText("");
                        dry_content.setText("");
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                return;
            }
        });
        builder.show();
    }
    public void ShowString(String title,String msg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}
