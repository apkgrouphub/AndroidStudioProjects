package com.example.diary;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class diary_show extends AppCompatActivity {
    TextView date;
    EditText dry_content,remark;
    DB_HELPER db;
    FloatingActionButton viewdoc;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_show);
        db=new DB_HELPER(this);
        date=(TextView)findViewById(R.id.ds_date);
        remark=(EditText)findViewById(R.id.ds_remark);
        dry_content=(EditText)findViewById(R.id.ds_content);


        remark.setInputType(InputType.TYPE_NULL);
        dry_content.setInputType(InputType.TYPE_NULL);
        Cursor c=db.getdata(getIntent().getExtras().getString("id"));
        c.moveToFirst();
        User.c_diary_id=c.getString(0);
        id=c.getString(0);
        remark.setText(c.getString(3));
        dry_content.setText(c.getString(4));
        dry_content.setSingleLine(false);
        date.setText(c.getString(2));
        viewdoc=(FloatingActionButton) findViewById(R.id.fabdoc);
        viewdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor fc=db.findfile(id);


                /*if (fc.getCount()<=0)
                {

                    Toast.makeText(getApplicationContext(), " No records ", Toast.LENGTH_LONG).show();
                }
                else
                {*/
                    //fc.moveToFirst();
                    Intent i1 = new Intent(getApplicationContext(), AudioVideo.class);
                    startActivity(i1);
                //}
            }
        });



        /*if (fc.getCount()<=0)
        {
            viewdoc.setVisibility(View.INVISIBLE);
        }
        else
        {
            viewdoc.setVisibility(View.VISIBLE);
        }*/

    }

}
