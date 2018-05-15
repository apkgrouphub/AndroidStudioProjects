package com.me.keepme.keepme.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.me.keepme.keepme.adapter.Adapter;
import com.me.keepme.keepme.R;
import com.me.keepme.keepme.adapter.Adapter_Dialouge;
import com.me.keepme.keepme.model.GroupModel;
import com.me.keepme.keepme.utils.Data_Base;
import com.me.keepme.keepme.utils.IconList;
import com.me.keepme.keepme.utils.ItemClickSupport;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    RecyclerView rv;
    RecyclerView dlg_rv;
    EditText dlg_text;
    Button dlg_btn_ok;
    ImageView img_bigh;
    Data_Base db;
    int image;
    Adapter adptr;
    String j;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db=new Data_Base(this);
        img_bigh=findViewById(R.id.dlg_big_imge);

        //Toast.makeText(this, db.getALl_of_TABLE_FOLDER().get(1).groupName, Toast.LENGTH_SHORT).show();



        //setting adapter
        rv=findViewById(R.id.rv);
        RecyclerView.LayoutManager l = new GridLayoutManager(this,3);
        rv.setLayoutManager(l);
        ArrayList<GroupModel> list=db.getALl_of_TABLE_FOLDER();

        adptr=new Adapter(this,list);
        rv.setAdapter(adptr);

        ItemClickSupport.addTo(rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                //Toast.makeText(HomeActivity.this, "This is home", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void flot_add_click (View view){
        final Dialog d = new Dialog(HomeActivity.this);
        d.setContentView(R.layout.dialog_set_fields);

        dlg_rv=d.findViewById(R.id.dgrclview);
        dlg_text=d.findViewById(R.id.dgedt_set_folder);
        dlg_btn_ok=d.findViewById(R.id.dlg_edt_btn);
        img_bigh=d.findViewById(R.id.dlg_big_imge);

        //RecyclerView.LayoutManager mgr=new GridLayoutManager(this,3);
        RecyclerView.LayoutManager mgr = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        dlg_rv.setLayoutManager(mgr);
        IconList il=new IconList();
        final Adapter_Dialouge adptrdlg=new Adapter_Dialouge(this,il.icons());
        dlg_rv.setAdapter(adptrdlg);
        d.show();


        ItemClickSupport.addTo(dlg_rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int p, View v) {
                image = adptrdlg.getImage(p);
                img_bigh.setImageDrawable(getResources().getDrawable(image));
                //dlg_rv.setVisibility(View.GONE);
                //img_bigh.setVisibility(View.VISIBLE);

            }
        });

        dlg_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(dlg_text.getText().length()!=0)
                {
                    dlg_btn_ok.setVisibility(View.VISIBLE);
                }
                else if (dlg_text.getText().length()==0)
                {
                    dlg_btn_ok.setVisibility(View.GONE);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dlg_btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dlg_text.getText().length()!=0) {

                    //Toast.makeText(HomeActivity.this, dlg_text.getText().toString()+" / "+ image, Toast.LENGTH_SHORT).show();

                    boolean isInserteddb=db.insertData_of_TABLE_FOLDER(dlg_text.getText().toString(),image);
                    if (isInserteddb==true)
                    {
                        ArrayList<GroupModel>list = db.getALl_of_TABLE_FOLDER();
                        //Toast.makeText(HomeActivity.this, "inserted", Toast.LENGTH_SHORT).show();
                        adptr =  new Adapter(getApplicationContext(),list);
                        rv.setAdapter(adptr);
                        d.dismiss();
                    }
                    else
                    {
                        Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        d.dismiss();
                    }
                }
            }
        });

    }



}
