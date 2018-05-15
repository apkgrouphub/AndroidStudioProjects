package com.example.diary;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by PACHU on 29-04-2017.
 */
public class Audioview extends Activity {
    DB_HELPER db;
    ArrayList<String> ids;
    ListView aud_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audeoview);
        db=new DB_HELPER(this);
        aud_list=(ListView)findViewById(R.id.aud_list);


        Cursor getaud_cursor=db.getaudio(User.c_diary_id,"Audio");
        if (getaud_cursor.getCount()==0)
        {
            Toast.makeText(getApplicationContext(), "This day did not record any Audio", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Found audios", Toast.LENGTH_LONG).show();

            ArrayList<AudProdect> arrayList=new ArrayList<>();
            ids=new ArrayList<>();
            while(getaud_cursor.moveToNext())
            {
                AudProdect p=new AudProdect();
                p.setImgid(R.drawable.ic_audiomic);
                //p.setDate(User.diary_date);
                ids.add(getaud_cursor.getString(0));
                arrayList.add(p);
            }
            AudioLIstAdapter adp=new AudioLIstAdapter(getApplicationContext(),arrayList);
            aud_list.setAdapter(adp);
        }

        try{
            aud_list.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public  void onItemClick(AdapterView<?> adp, View view, int pos, long l)
                {

                    try
                    {
                        String item =ids.get(pos);
                        Cursor gf=db.getfile(item);
                        gf.moveToFirst();
                        String path=gf.getString(2);
                       //Toast.makeText(getApplicationContext(),path, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.parse(path),"audio/*");
                        startActivity(intent);
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
        }


    }
}
