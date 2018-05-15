package com.me.keepme.keepme.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.me.keepme.keepme.R;
import com.me.keepme.keepme.activity.HomeActivity;
import com.me.keepme.keepme.model.GroupModel;
import com.me.keepme.keepme.utils.Data_Base;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder>
{
    private EditText editFolder;
    private Button buttonFolder;
    private ImageView img_folder;
    private Context c;
    private ArrayList<GroupModel> data;
    public Adapter(Context context, ArrayList<GroupModel> data)
    {
        this.c=context;
        this.data=data;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(c).inflate(R.layout.home_list_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder h,final int p) {
        h.img.setImageDrawable(c.getResources().getDrawable(data.get(p).getImage()));
        h.title.setText(data.get(p).getGroupName());

        h.img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(c, h.img_menu);
                popup.inflate(R.menu.home_item_pop_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                //handle menu1 click
                               // h.img_Folder.setImageDrawable(c.getResources().getDrawable());
                                Dialog d = new Dialog(c);
                                d.setContentView(R.layout.edit_folder);
                                editFolder=d.findViewById(R.id.edt_edit_folder);
                                buttonFolder=d.findViewById(R.id.btn_edit_folder);
                                img_folder=d.findViewById(R.id.img_edit_folder);
                                img_folder.setImageDrawable(c.getResources().getDrawable(data.get(p).getImage()));
                                d.show();
                                editFolder.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                        if(editFolder.getText().length()!=0)
                                        {
                                            buttonFolder.setVisibility(View.VISIBLE);
                                        }
                                        else if (editFolder.getText().length()==0)
                                        {
                                            buttonFolder.setVisibility(View.GONE);

                                        }
                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {

                                    }
                                });
                                break;
                            case R.id.delete:
                                //handle menu2 click
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;
        ImageView img_menu;
        public viewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imageView);
            title=itemView.findViewById(R.id.textView);
            img_menu=itemView.findViewById(R.id.img_menu_listitem);

            //img_Folder=itemView.findViewById(R.id.img_edit_folder);
        }
    }

    public void editDialouge()
    {

    }

}
