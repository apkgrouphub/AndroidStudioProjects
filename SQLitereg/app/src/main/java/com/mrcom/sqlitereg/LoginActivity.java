package com.mrcom.sqlitereg;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity
{
    Button Login,signup;
    EditText name,password;
    DB_HELPER mydb;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name=(EditText)findViewById(R.id.edtname);
        password=(EditText)findViewById(R.id.edtpass);
        Login=(Button)findViewById(R.id.btn_login);
        signup=(Button)findViewById(R.id.btn_signup);
        mydb=new DB_HELPER(this);

        Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                    Cursor cr=mydb.log(name.getText().toString(),password.getText().toString());
                    if (cr.getCount()==1)
                    {
                        while(cr.moveToNext())
                        {
                            user_id=cr.getString(0);
                        }
                        Toast.makeText(getApplicationContext(), user_id, Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(),db_display_Activity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "name or password is wrong", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i1=new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(i1);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menus,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.it1:
                Toast.makeText(getApplicationContext(), "manikandan", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
