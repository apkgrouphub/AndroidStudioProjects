package com.example.diary;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Event_Add_Activity extends AppCompatActivity {
    EditText event_name,event_place,event_date,event_time;
    Button btn_add_ev;
    private int year,month,day,hour,minute;
    static final int DATE_PICKER_ID = 1111;
    static final int TIME_PICKER_ID = 2222;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event__add_);

        event_name=(EditText)findViewById(R.id.edt_Event_name);
        event_place=(EditText)findViewById(R.id.edt_Event_Location);
        event_date=(EditText)findViewById(R.id.edt_Event_date);
        event_time=(EditText)findViewById(R.id.edt_Event_time);
        btn_add_ev=(Button)findViewById(R.id.btn_add_ev);

        event_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_PICKER_ID);
            }
        });

        event_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_PICKER_ID);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch (id)
        {
            case DATE_PICKER_ID:
                DatePickerDialog dpd=new DatePickerDialog(this, pickerListener, year, month,day);
                return dpd;
            case TIME_PICKER_ID:
                TimePickerDialog tpd=new TimePickerDialog(this,timepick,hour,minute,false);
                return tpd;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay)
        {
            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;
            event_date.setText(new StringBuilder().append(day).append("-").append(month + 1).append("-").append(year).append(" "));
        }
    };
    private TimePickerDialog.OnTimeSetListener timepick=new TimePickerDialog.OnTimeSetListener()
    {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {
            hour=hourOfDay;
            int hour_12_format;
            String AM_PM="AM";
            if (hourOfDay>11)
            {
                AM_PM="PM";
            }
            if (hourOfDay>11)
            {
                hour_12_format=hourOfDay-12;
            }
            else
            {
                hour_12_format=hourOfDay;
            }
            event_time.setText(new StringBuilder().append(hour_12_format).append(":").append(minute).append(" ").append(AM_PM).append(""));
        }
    };
}
