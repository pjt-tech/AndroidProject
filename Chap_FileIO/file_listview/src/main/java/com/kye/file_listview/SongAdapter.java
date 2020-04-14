package com.kye.file_listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class SongAdapter extends BaseAdapter {

    ArrayList<SongItem> items = new ArrayList<>();
    Context context;

    public SongAdapter(Context context) {
        this.context = context;
    }

    public void addItem(SongItem item){
        items.add(item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SongItemView view = null;
        if(convertView==null){
            view = new SongItemView(context);
        }else {
            view = (SongItemView)convertView;
        }

        SongItem item = items.get(position);
        view.setTitle(item.getTitle());
        view.setSinger(item.getSinger());
        view.setImageView(R.drawable.song);
        return view;
    }

    public void removeItem(int position){
        items.remove(position);
    }
}
