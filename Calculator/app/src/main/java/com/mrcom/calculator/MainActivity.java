package com.mrcom.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button one,two,three,four,five,six,seven,eight,nine,zero,dot,add,sub,mul,div,clear,equal,btnpi;
    EditText result;
    Float a,b,c;
    int temp=0,opr=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one=(Button)findViewById(R.id.edt1);
        two=(Button)findViewById(R.id.edt2);
        three=(Button)findViewById(R.id.edt3);
        four=(Button)findViewById(R.id.edt4);
        five=(Button)findViewById(R.id.edt5);
        six=(Button)findViewById(R.id.edt6);
        seven=(Button)findViewById(R.id.edt7);
        eight=(Button)findViewById(R.id.edt8);
        nine=(Button)findViewById(R.id.edt9);
        zero=(Button)findViewById(R.id.edt0);
        add=(Button)findViewById(R.id.edtadd);
        sub=(Button)findViewById(R.id.edtsub);
        mul=(Button)findViewById(R.id.edtmul);
        div=(Button)findViewById(R.id.edtdiv);
        equal=(Button)findViewById(R.id.edteql);
        dot=(Button)findViewById(R.id.edtpoint);
        clear=(Button)findViewById(R.id.clear);
        btnpi=(Button)findViewById(R.id.btnpi);

        result=(EditText)findViewById(R.id.result);
        result.setText("0");

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opr==1)
                {
                    result.setText("0");
                    opr=0;

                }
                else
                {
                    if(result.getText().toString().equals("0"))
                        result.setText("0");
                    else
                        result.setText(result.getText().toString()+"0");
                }
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opr==1)
                {
                    result.setText("1");
                    opr=0;

                }
                else
                {
                    if(result.getText().toString().equals("0"))
                        result.setText("1");
                    else
                        result.setText(result.getText().toString()+"1");
                }
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(opr==1)
                {
                    result.setText("2");
                    opr=0;

                }
                else
                {
                    if(result.getText().toString().equals("0"))
                        result.setText("2");
                    else
                        result.setText(result.getText().toString()+"2");
                }
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(opr==1)
                {
                    result.setText("3");
                    opr=0;

                }
                else
                {
                    if(result.getText().toString().equals("0"))
                        result.setText("3");
                    else
                        result.setText(result.getText().toString()+"3");
                }
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(opr==1)
                {
                    result.setText("4");
                    opr=0;

                }
                else
                {
                    if(result.getText().toString().equals("0"))
                        result.setText("4");
                    else
                        result.setText(result.getText().toString()+"4");
                }
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opr==1)
                {
                    result.setText("5");
                    opr=0;

                }
                else
                {
                    if(result.getText().toString().equals("0"))
                        result.setText("5");
                    else
                        result.setText(result.getText().toString()+"5");
                }
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opr==1)
                {
                    result.setText("6");
                    opr=0;

                }
                else
                {
                    if(result.getText().toString().equals("0"))
                        result.setText("6");
                    else
                        result.setText(result.getText().toString()+"6");
                }

            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(opr==1)
                {
                    result.setText("7");
                    opr=0;

                }
                else
                {
                    if(result.getText().toString().equals("0"))
                        result.setText("7");
                    else
                        result.setText(result.getText().toString()+"7");
                }
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opr==1)
                {
                    result.setText("8");
                    opr=0;

                }
                else
                {
                    if(result.getText().toString().equals("0"))
                        result.setText("8");
                    else
                        result.setText(result.getText().toString()+"8");
                }
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opr==1)
                {
                    result.setText("9");
                    opr=0;

                }
                else
                {
                    if(result.getText().toString().equals("0"))
                        result.setText("9");
                    else
                        result.setText(result.getText().toString()+"9");
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str=result.getText().toString();
                if (str.length()>=1) {
                    str=str.substring(0,str.length()-1);
                    result.setText(str);
                }
                if (str.length()<1) {
                    result.setText("0");
                }
                temp=0;
            }
        });

        clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                result.setText("0");
                temp=0;
                return true;
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result.getText().toString().contains("."))
                {}
                else
                result.setText(result.getText().toString()+".");
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result.getText().toString().equals("")|result.getText().toString().equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "Enter the digit", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    a=Float.valueOf(result.getText().toString());
                    result.setText("");
                    temp=1;
                }
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result.getText().toString().equals("")|result.getText().toString().equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "Enter the digit", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    a=Float.valueOf(result.getText().toString());
                    result.setText("");
                    temp=2;
                }
            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result.getText().toString().equals("")|result.getText().toString().equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "Enter the digit", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    a=Float.valueOf(result.getText().toString());
                    result.setText("");
                    temp=3;
                }
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result.getText().toString().equals("")|result.getText().toString().equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "Enter the digit", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    a=Float.valueOf(result.getText().toString());
                    result.setText("");
                    temp=4;
                }
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opr=1;

                if (temp==1) {

                    if (result.getText().toString().equals(""))
                    {
                        return;
                    }
                    b=Float.valueOf(result.getText().toString());
                    c=a+b;
                    result.setText(c.toString());
                }
                else if (temp==2) {
                    if (result.getText().toString().equals(""))
                    {
                        return;
                    }
                    b=Float.valueOf(result.getText().toString());
                    c=a-b;
                    result.setText(c.toString());
                }
                else if (temp==3) {
                    if (result.getText().toString().equals(""))
                    {
                        return;
                    }
                    b=Float.valueOf(result.getText().toString());
                    c=a*b;
                    result.setText(c.toString());
                }
                else if (temp==4) {
                    if (result.getText().toString().equals(""))
                    {
                        return;
                    }
                    b=Float.valueOf(result.getText().toString());
                    c=a/b;
                    result.setText(c.toString());
                }
                else
                {

                }

                if (result.getText().toString().contains("Π"))
                {
                    String pi=result.getText().toString();
                    Integer a=pi.indexOf("Π");
                    Integer l=pi.length();
                    if (result.getText().toString().equals("Π"))
                    {
                        result.setText("3.14159265");
                    }
                    else if (a==0) {
                        pi = pi.substring(1, pi.length());
                        Double mpi = Double.parseDouble(pi.toString());
                        Double pim = 3.14159265 * mpi;
                        result.setText(pim.toString());
                        //Toast.makeText(getApplicationContext(), a.toString(), Toast.LENGTH_SHORT).show();
                    }
                    else if (a==l-1)
                    {
                        pi = pi.substring(0, pi.length()-1);
                        Double mpi = Double.parseDouble(pi.toString());
                        Double pim = 3.14159265 * mpi;
                        result.setText(pim.toString());
                    }
                    else
                    {
                        String p1= pi.substring(0, a);
                        String p2=pi.substring(a+1,l);
                        Double mp1 = Double.parseDouble(p1.toString());
                        Double mp2 = Double.parseDouble(p2.toString());
                        Double pim = mp1*3.14159265 * mp2;
                        result.setText(pim.toString());
                        //Toast.makeText(getApplicationContext(), p1+"pi"+p2, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnpi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    /*if(result.getText().toString().equals("0"))
                    {
                        if (result.getText().toString().contains("Π"))
                        {}
                        else
                            result.setText("Π");
                    }
                else
                    {
                        if (result.getText().toString().contains("Π"))
                        {}
                        else
                        Toast.makeText(getApplicationContext(),"Take pi at  first",Toast.LENGTH_SHORT).show();
                    }*/

                if(opr==1)
                {
                    result.setText("Π");
                    opr=0;

                }
                else
                {
                    if(result.getText().toString().equals("0"))
                        result.setText("Π");
                    else
                        result.setText(result.getText().toString()+"Π");
                }
            }
        });
    }
}
