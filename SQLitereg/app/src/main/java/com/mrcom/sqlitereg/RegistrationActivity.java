package com.mrcom.sqlitereg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity
{
    EditText r_name,r_pass,r_age;
    Button done;
    DB_HELPER mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        r_name=(EditText)findViewById(R.id.edt_rname);
        r_pass=(EditText)findViewById(R.id.edt_rpass);
        r_age=(EditText)findViewById(R.id.edt_age);
        done=(Button)findViewById(R.id.btn_done);
        mydb=new DB_HELPER(this);

        done.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean isInserted= mydb.insertData(r_name.getText().toString(),r_pass.getText().toString(),r_age.getText().toString());

                if(isInserted==true)
                {
                    Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
