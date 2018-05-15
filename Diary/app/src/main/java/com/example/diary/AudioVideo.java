package com.example.diary;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class AudioVideo extends TabActivity {
    TabHost TabHostWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_video);

        TabHostWindow = (TabHost)findViewById(android.R.id.tabhost);

        TabHost.TabSpec TabMenu1 = TabHostWindow.newTabSpec("Videos");
        TabHost.TabSpec TabMenu2 = TabHostWindow.newTabSpec("Audios");

        //Setting up tab 1 name.
        TabMenu1.setIndicator("Videos");
        //Set tab 1 activity to tab 1 menu.
        TabMenu1.setContent(new Intent(this,Videoview.class));

        //Setting up tab 2 name.
        TabMenu2.setIndicator("Audios");
        //Set tab 3 activity to tab 1 menu.
        TabMenu2.setContent(new Intent(this,Audioview.class));

        TabHostWindow.addTab(TabMenu1);
        TabHostWindow.addTab(TabMenu2);
    }
}
