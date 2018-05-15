package com.example.diary;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText fullname,dateob,email,username,passwrd,phonenum;
    CheckBox showpassword;
    Button signup,view;
    DB_HELPER db;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db=new DB_HELPER(this);



        fullname=(EditText)findViewById(R.id.edt_fullname);
        dateob=(EditText)findViewById(R.id.edt_date);
        email=(EditText)findViewById(R.id.edt_email);
        username=(EditText)findViewById(R.id.edt_regusername);
        passwrd=(EditText)findViewById(R.id.edt_regpass);
        phonenum=(EditText)findViewById(R.id.edt_phone);
        showpassword=(CheckBox)findViewById(R.id.chk_showpass);
        signup=(Button)findViewById(R.id.btn_signup);
        //view=(Button)findViewById(R.id.button);

        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(fullname.getText().toString().equals("")||
                        dateob.getText().toString().equals("")||
                        email.getText().toString().equals("")||
                        username.getText().toString().equals("")||
                        passwrd.getText().toString().equals("")||
                        phonenum.getText().toString().equals(""))
                {
                    Showmsg("Rgistration failed","the colums are blank \n Pleace fill the colums");
                }
                else
                {
                    try {
                        boolean isInserted = db.insertRegistrationData(
                                fullname.getText().toString(),
                                dateob.getText().toString(),
                                email.getText().toString(),
                                username.getText().toString(),
                                passwrd.getText().toString(),
                                phonenum.getText().toString());

                        if (isInserted)
                        {
                            Showmsg("Rgistration Success","");
                            fullname.setText("");
                            dateob.setText("");
                            email.setText("");
                            username.setText("");
                            passwrd.setText("");
                            phonenum.setText("");
                        } else
                        {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=db.getAlldata();
                if(res.getCount()==0)
                {
                    //message
                    Showmsg("Error","There is no tables are created");
                    return;
                }
                StringBuffer bfr=new StringBuffer();
                while(res.moveToNext())
                {
                    bfr.append("id :"+res.getString(0)+"\n");
                    bfr.append("full name :"+res.getString(1)+"\n");
                    bfr.append("date of birth :"+res.getString(2)+"\n");
                    bfr.append("email :"+res.getString(3)+"\n");
                    bfr.append("user name :"+res.getString(4)+"\n");
                    bfr.append("password :"+res.getString(5)+"\n");
                    bfr.append("phone number :"+res.getString(6)+"\n\n");
                }
                Showmsg("data",bfr.toString());
            }
        });*/

        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(!isChecked)
                {
                    passwrd.setInputType(129);
                }
                else
                {
                    passwrd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });


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
