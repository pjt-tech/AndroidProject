package com.kye.filegallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyPictureView extends View {

    String path;


    public MyPictureView(Context context) {
        super(context);
    }

    public MyPictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(path!=null){

            Bitmap bitmap = BitmapFactory.decodeFile(path);
            int cenX = this.getWidth()/2;
            int cenY = this.getHeight()/2;
            int picX = (this.getWidth()-bitmap.getWidth())/2;
            int picY = (this.getHeight()-bitmap.getHeight())/2;
            canvas.scale(2,2,cenX,cenY);
            canvas.drawBitmap(bitmap,picX,picY,null);
            bitmap.recycle();

        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
