package com.example.diary;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by PACHU on 29-04-2017.
 */
public class Videoview extends Activity{
    DB_HELPER db;
    ArrayList<String> ids;
    ListView vid_list;
    public static String filepath;
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoview);
        //Date date= new Date();
        db=new DB_HELPER(this);
        vid_list=(ListView)findViewById(R.id.vid_list);

        //Cursor getaud_cursor=db.getvideo(User.c_diary_id);
        Cursor getaud_cursor=db.video(User.c_diary_id,"Video");

        if (getaud_cursor.getCount()==0)
        {
            Toast.makeText(getApplicationContext(), "This day did not record any Video", Toast.LENGTH_LONG).show();
        }
        else
        {
            //String no=String.valueOf(getvid_cursor.getCount());
            //Toast.makeText(getApplicationContext(), no, Toast.LENGTH_LONG).show();

            ArrayList<AudProdect> arrayList=new ArrayList<>();
            ids=new ArrayList<>();
            while(getaud_cursor.moveToNext())
            {
                AudProdect p=new AudProdect();
                p.setImgid(R.drawable.aud_rec1);
               // p.setDate(User.diary_date);
                ids.add(getaud_cursor.getString(0));
                arrayList.add(p);
            }
            AudioLIstAdapter adp=new AudioLIstAdapter(getApplicationContext(),arrayList);
            vid_list.setAdapter(adp);
        }

        vid_list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adp, View view, int pos, long l)
            {

                try
                {
                    item =ids.get(pos);
                    Cursor gf=db.getfile(item);
                    gf.moveToFirst();
                    String path=gf.getString(2);

                    //Toast.makeText(getApplicationContext(),, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(path),"video/*");
                    startActivity(intent);
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
                }

                //Toast.makeText(getApplicationContext(),item, Toast.LENGTH_LONG).show();
                //intent.putExtra("id",ids.get(pos));
                //startActivity(intent);
            }
        });

    }

    public void getItem(String id)
    {
        //Cursor gf=db.getfile(id);
        //filepath=gf.getString(2);
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
