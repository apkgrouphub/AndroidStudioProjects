package com.example.diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by PACHU on 30-04-2017.
 */

public class VideoListAdapter extends BaseAdapter {

    private ArrayList<AudProdect> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public VideoListAdapter(Context aContext, ArrayList<AudProdect> listData) {
        this.listData=listData;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.video_list_item, null);

            holder.tvimg = (ImageView) convertView.findViewById(R.id.vid_list_img);
            holder.tvDate = (TextView) convertView.findViewById(R.id.vid_list_text);

            convertView.setTag(R.id.vid_list_img,holder.tvimg);
            convertView.setTag(R.id.vid_list_text,holder.tvDate);

        }
        else
        {
            holder.tvimg = (ImageView) convertView.findViewById(R.id.vid_list_img);
            holder.tvDate=(TextView)convertView.getTag(R.id.vid_list_text);
        }


        holder.tvimg.setImageResource(listData.get(position).getImgid());
        holder.tvDate.setText(listData.get(position).getDate());

        return convertView;
    }
    static class ViewHolder {

        ImageView tvimg;
        TextView tvDate;

    }
}
