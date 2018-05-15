package com.example.diary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnsignup,btnlogin;
    EditText etuname,etpass;
    //TextView forg_pass;
    CheckBox rme;
    DB_HELPER db;
    static String user_id;
    SharedPreferences sp;
    SQLiteDatabase db1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db=new DB_HELPER(this);
        btnsignup=(Button)findViewById(R.id.btn_signup);
        btnlogin=(Button)findViewById(R.id.btn_log);
        etuname=(EditText) findViewById(R.id.edt_username);
        etpass=(EditText) findViewById(R.id.edt_password);
        //forg_pass=(TextView)findViewById(R.id.txv_forgot);
        rme=(CheckBox)findViewById(R.id.chkb_remember);

        sp=getSharedPreferences("spLogin",MODE_PRIVATE);
        if(sp.getBoolean("remember",false))
        {
            //User.userId=sp.getString("username","");
            etuname.setText(sp.getString("username",""));
            etpass.setText(sp.getString("pasword",""));
            rme.setChecked(true);

        }



        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if(etuname.getText().toString().equals("")|| etpass.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Pleace Type Your UserName Or Password", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Cursor cr=db.logdata(etuname.getText().toString(),etpass.getText().toString());
                    if (cr.getCount()==1)
                    {
                        while(cr.moveToNext())
                        {
                            user_id=cr.getString(0);
                        }
                       if (rme.isChecked())
                        {
                            sp.edit().putBoolean("remember",true).apply();
                            sp.edit().putString("username",etuname.getText().toString()).apply();
                            sp.edit().putString("pasword",etpass.getText().toString()).apply();
                            //rme.setChecked(true);
                            //Toast.makeText(getApplicationContext(),user_id.toString(), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            sp.edit().putBoolean("remember",false).apply();
                        }
                        User.userId=user_id;
                        Toast.makeText(getApplicationContext(),user_id.toString(), Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                        etuname.setText("");
                        etpass.setText("");
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "name or password is wrong", Toast.LENGTH_SHORT).show();
                    }
                }
                //Intent i=new Intent(getApplicationContext(),MainActivity.class);
                //startActivity(i);
            }


        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cv=db.getAlldata();
                if (cv.getCount()<=0)
                {
                    //btnsignup.setVisibility(View.INVISIBLE);
                    cv.moveToFirst();
                    Intent i1=new Intent(getApplicationContext(),RegisterActivity.class);
                    startActivity(i1);
                }
                else
                {
                    //btnsignup.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Account is created", Toast.LENGTH_SHORT).show();

                }




            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;

    }



}
