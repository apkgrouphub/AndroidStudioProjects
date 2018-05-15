package com.mrcom.sqlitereg;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends Activity {
    private TextView Output;
    private Button changeDate;
    EditText editText;
    ListView newlist;
    DB_HELPER mydb;
    ArrayList<String> list;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    static final int DATE_PICKER_ID = 1111;
    static final int TIME_PICKER_ID = 2222;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Output=(TextView)findViewById(R.id.output);
        changeDate=(Button)findViewById(R.id.changedate);
        editText=(EditText) findViewById(R.id.editText);
        newlist=(ListView) findViewById(R.id.new_list);
        mydb=new DB_HELPER(this);
        list =new ArrayList<String>();

        TextView opname=(TextView)findViewById(R.id.textname);

        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        changeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // On button click show datepicker dialog
                showDialog(TIME_PICKER_ID);
            }
        });

        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // On button click show datepicker dialog
                showDialog(DATE_PICKER_ID);
            }
        });

        Cursor data=mydb.getAlldata();
        if (data.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"there are no database",Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (data.moveToNext())
            {
                Prodect prdt=new Prodect("riyas","25");
            }
        }

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
       // TimePickerDialog tpd=new TimePickerDialog(this,timeListene)
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener()
    {

        @Override
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay)
        {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;

            // Show selected date
            //Output.setText(new StringBuilder().append(day)
              //      .append("-").append(month + 1).append("-").append(year)
                //    .append(" "));

            editText.setText(new StringBuilder()
                    .append(day).append("-").append(month + 1).append("-")
                    .append(year).append(" "));

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
            Output.setText(new StringBuilder().append(hour_12_format).append(":").append(minute).append(" ").append(AM_PM).append(""));
        }
    };


}
