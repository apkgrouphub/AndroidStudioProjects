package com.example.diary;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab_add,fab_Diary,fab_Event;
    Animation fab_open,fab_close,fab_Rclock,fab_Ranticlock;
    public static ListView diary_list_view;
    ArrayList<String> ids;
    DB_HELPER db;
    boolean isopen =false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DB_HELPER(this);


        diary_list_view=(ListView) findViewById(R.id.lst_view);
        fab_add=(FloatingActionButton)findViewById(R.id.fabadd);
        fab_Diary=(FloatingActionButton)findViewById(R.id.fab_add_diary);
        fab_Event=(FloatingActionButton)findViewById(R.id.fab_add_event);

        fab_open= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fabfirstclick);
        fab_close= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fabsecondclick);
        fab_Rclock= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_rot_clockwise);
        fab_Ranticlock= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_rot_anticlockwise);

        ArrayList<Prodect> arrayList=new ArrayList<>();
        ids=new ArrayList<>();
        Cursor res=db.getAll_Diary_data();
        while(res.moveToNext())
        {
            Prodect p=new Prodect();
            p.setImgid(R.drawable.ic_note_black);
            if(res.getString(3).equals(""))
            {
                p.setRemark("No Remark");
            }else
            {
                p.setRemark(res.getString(3));
            }
            p.setId(res.getString(0));
            ids.add(res.getString(0));
            p.setDate(res.getString(2));
            arrayList.add(p);
        }
        DiaryListAdapter adp=new DiaryListAdapter(getApplicationContext(),arrayList);
        diary_list_view.setAdapter(adp);

        try{
            diary_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public  void onItemClick(AdapterView<?> adp,View  view,int pos,long l)
                {

                    Intent intent=new Intent(MainActivity.this,diary_show.class);
                    intent.putExtra("id",ids.get(pos));
                    User.find_d_id=ids.get(pos);
                    startActivity(intent);
                }
            });
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
        }


        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isopen)
                {
                    fab_Diary.startAnimation(fab_close);
                    //fab_Event.startAnimation(fab_close);
                    fab_add.startAnimation(fab_Ranticlock);
                    fab_Diary.setClickable(false);
                    //fab_Event.setClickable(false);
                    isopen=false;
                }
                else
                {

                    fab_Diary.startAnimation(fab_open);
                    //fab_Event.startAnimation(fab_open);
                    fab_add.startAnimation(fab_Rclock);
                    fab_Diary.setClickable(true);
                    //fab_Event.setClickable(true);
                    isopen=true;
                }
            }
        });

        fab_Diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(getApplicationContext(), " Digitel Diary Coming Soon. With VideoRecording ", Toast.LENGTH_LONG).show();

                    Intent i=new Intent(getApplicationContext(),MakeDiaryActivity.class);
                    i.putExtra("id","-1");
                    startActivity(i);

                fab_Diary.startAnimation(fab_close);
                //fab_Event.startAnimation(fab_close);
                fab_add.startAnimation(fab_Ranticlock);
                fab_Diary.setClickable(false);
                //fab_Event.setClickable(false);
                isopen=false;
            }
        });

       /* fab_Event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), " Digitel Diary Coming Soon. With AudioRecording ", Toast.LENGTH_LONG).show();
               Intent i1=new Intent(getApplicationContext(),EventActivity.class);
                startActivity(i1);

                fab_Diary.startAnimation(fab_close);
                fab_Event.startAnimation(fab_close);
                fab_add.startAnimation(fab_Ranticlock);
                fab_Diary.setClickable(false);
                fab_Event.setClickable(false);
                isopen=false;

               // Cursor c=db.getfile("1","vdo");
               // if(c.getCount()>0)
                //{
                    //c.getString(0)
                    //
                //}
            }
        });*/

    }

    public void Showmsg(String title,String msg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }

}
