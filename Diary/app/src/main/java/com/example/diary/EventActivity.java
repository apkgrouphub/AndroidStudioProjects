package com.example.diary;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {
    FloatingActionButton fab_make_ev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        fab_make_ev=(FloatingActionButton)findViewById(R.id.fab_make_ev);
        fab_make_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Event_Add_Activity.class);
                startActivity(i);
            }
        });
        //ListView lstView=(ListView)findViewById(R.id.lstview);
        //ArrayList<String> al=new ArrayList<>();
       // al.add("Event name",);
       // ArrayAdapter<String> Arradapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, )
    }
}
