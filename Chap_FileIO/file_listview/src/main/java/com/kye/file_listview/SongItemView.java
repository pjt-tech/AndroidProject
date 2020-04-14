package com.kye.file_listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SongItemView extends LinearLayout {

    Context context;
    TextView title,singer;
    ImageView imageView;

    public SongItemView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public void init(){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.songitem,this,true);
        title = findViewById(R.id.textView);
        singer = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setSinger(String singer) {
        this.singer.setText(singer);
    }

    public void setImageView(int id) {
        this.imageView.setImageResource(id);
    }
}
