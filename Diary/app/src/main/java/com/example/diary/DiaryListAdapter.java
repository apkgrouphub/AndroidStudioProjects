package com.example.diary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by Adhoc on 15-Jan-17.
 */

public class DiaryListAdapter extends BaseAdapter {
    private ArrayList<Prodect> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public DiaryListAdapter(Context aContext, ArrayList<Prodect> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
        context = aContext;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.diary_list_item, null);

            holder.tvimg = (ImageView) convertView.findViewById(R.id.imageView);
            holder.tvDate = (TextView) convertView.findViewById(R.id.text_item_date);
            holder.tvRemark = (TextView) convertView.findViewById(R.id.text_item_remark);

            convertView.setTag(R.id.imageView,holder.tvimg);
            convertView.setTag(R.id.text_item_date,holder.tvDate);
            convertView.setTag(R.id.text_item_remark,holder.tvRemark);

        }
        else
        {
            holder.tvimg = (ImageView) convertView.findViewById(R.id.imageView);
            holder.tvDate=(TextView)convertView.getTag(R.id.text_item_date);
            holder.tvRemark=(TextView)convertView.getTag(R.id.text_item_remark);
        }


        holder.tvimg.setImageResource(listData.get(position).getImgid());
        holder.tvDate.setText(listData.get(position).getDate());
        holder.tvRemark.setText(listData.get(position).getRemark());

        return convertView;
    }

    static class ViewHolder {

        ImageView tvimg;
        TextView tvDate;
        TextView tvRemark;

    }
}


