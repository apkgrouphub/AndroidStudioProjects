package com.example.diary;

import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by PACHU on 11-04-2017.
 */

public class User {
    public static String userId;
    public static String videofilepath;
    public static String audiofilepath;
    public static String dryid;
    public static String c_diary_id;
    public static String diary_date;
    public static String find_d_id;
    public static String path;

    public static File getOutputMediaFile()
    {
        Date date= new Date();
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy_hh-mm", Locale.getDefault()).format(date.getTime());

        File mFilename;


        //User.audiofilepath=mFilename;
        File diaryFolder=new File(Environment.getExternalStoragePublicDirectory("Diary"),"Audio");
        if(!diaryFolder.exists())
        {
            if(!diaryFolder.mkdirs())
            {
                //Log.d(DIRECTORY_NAME,"Not created "+DIRECTORY_NAME+"Directory");
                return  null;
            }
        }
        mFilename = new File(diaryFolder.getAbsolutePath() + "/" + "AUD_"+ timeStamp + ".mp3");
        return mFilename;
    }



}
