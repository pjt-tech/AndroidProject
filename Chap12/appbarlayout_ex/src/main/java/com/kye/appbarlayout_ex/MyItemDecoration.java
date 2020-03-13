package com.kye.appbarlayout_ex;


import android.content.Context;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import android.view.View;

import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MyItemDecoration extends RecyclerView.ItemDecoration {
    Context context;

    public MyItemDecoration(Context context) {
        this.context = context;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int width = parent.getWidth();
        int height = parent.getHeight();
        Drawable drawable = ResourcesCompat.getDrawable(context.getResources(),R.drawable.android,null);
        int drWidth = drawable.getIntrinsicWidth();
        int drHeight = drawable.getIntrinsicHeight();

        int left = width/2 - drWidth/2;
        int top = height/2 - drHeight/2;

        c.drawBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.android),left,top,null);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int index = parent.getChildAdapterPosition(view)+1;
        if(index % 3 ==0){
            outRect.set(20,20,20,60);
        }else{
            outRect.set(20,20,20,20);
        }
        view.setBackgroundColor(0xFFECE9E9);
        ViewCompat.setElevation(view, 20.0f);
    }
}