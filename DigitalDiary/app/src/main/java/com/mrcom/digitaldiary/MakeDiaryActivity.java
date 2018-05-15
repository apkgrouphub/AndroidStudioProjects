package com.mrcom.digitaldiary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

public class MakeDiaryActivity extends AppCompatActivity
{
    FloatingActionButton fab_add,fab_vidrecord,fab_audrecord,fab_fileattach;
    Animation fab_open,fab_close,fab_Rclock,fab_Ranticlock;
    boolean isopen =false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_diary);

        fab_add=(FloatingActionButton)findViewById(R.id.fabadd);
        fab_vidrecord=(FloatingActionButton)findViewById(R.id.fabvidreco);
        fab_audrecord=(FloatingActionButton)findViewById(R.id.fabaudreco);
        fab_fileattach=(FloatingActionButton)findViewById(R.id.fabfileatta);

        /* fab animation commends*/

        fab_open= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fabfirstclick);
        fab_close= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fabsecondclick);
        fab_Rclock= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_rot_clockwise);
        fab_Ranticlock= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_rot_anticlockwise);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isopen)
                {
                    fab_audrecord.startAnimation(fab_close);
                    fab_vidrecord.startAnimation(fab_close);
                    fab_fileattach.startAnimation(fab_close);
                    fab_add.startAnimation(fab_Ranticlock);
                    fab_fileattach.setClickable(false);
                    fab_audrecord.setClickable(false);
                    fab_vidrecord.setClickable(false);
                    isopen=false;
                }
                else
                {
                    fab_audrecord.startAnimation(fab_open);
                    fab_vidrecord.startAnimation(fab_open);
                    fab_fileattach.startAnimation(fab_open);
                    fab_add.startAnimation(fab_Rclock);
                    fab_fileattach.setClickable(true);
                    fab_audrecord.setClickable(true);
                    fab_vidrecord.setClickable(true);
                    isopen=true;
                }
            }
        });

        fab_vidrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(), " Digitel Diary Coming Soon. With VideoRecording ", Toast.LENGTH_LONG).show();
                showMessage("Coming Soon","Video recording feature is not available now");
            }
        });

        fab_audrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), " Digitel Diary Coming Soon. With AudioRecording ", Toast.LENGTH_LONG).show();
                showMessage("Coming Soon","Audio recording feature is not available now");
            }
        });

        fab_fileattach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), " Digitel Diary Coming Soon. With FileAttachment ", Toast.LENGTH_LONG).show();
                showMessage("Coming Soon","File attachment feature is not available now");
            }
        });

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
}
