package com.me.keepme.keepme.dialouge;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.me.keepme.keepme.R;
import com.me.keepme.keepme.adapter.Adapter_Dialouge;
import com.me.keepme.keepme.utils.IconList;

public class Dialouge extends Dialog{
    Context c;
    public Dialouge(@NonNull Context c) {
        super(c);
        this.c = c;
    }

    public void setContentLayout(int layout){
        setContentView(layout);
    }
    public void initUI(String title){
        Button edit_buttonFolder= this.findViewById(R.id.dlg_edt_btn);
        ImageView edit_img_folder= this.findViewById(R.id.dlg_big_imge);

        //setting recycleview.
        RecyclerView edit_rv_folder= this.findViewById(R.id.dgrclview);
        RecyclerView.LayoutManager mgr = new LinearLayoutManager(c,LinearLayoutManager.HORIZONTAL,false);
        edit_rv_folder.setLayoutManager(mgr);
        IconList il=new IconList();
        final Adapter_Dialouge adptrdlg=new Adapter_Dialouge(c,il.icons());
        edit_rv_folder.setAdapter(adptrdlg);


        EditText edit_edt_Folder = this.findViewById(R.id.dgedt_set_folder);
        edit_edt_Folder.setText(title);


        edit_buttonFolder.setVisibility(View.VISIBLE);


        show();
    }

}
