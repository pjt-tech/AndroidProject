package com.kye.mypaintboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.io.File;

public class PaintBoard extends View {

    Paint paint;
    Canvas mCanvas;
    Bitmap mbitmap;
    int lastX, lastY, x, y;
    Path path;

    public PaintBoard(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10.0f);
        lastX=-1;
        lastY=-1;
        path = new Path();
    }

    public PaintBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mbitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas();
        mCanvas.setBitmap(mbitmap);
        mCanvas.drawColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mbitmap!=null){
            canvas.drawBitmap(mbitmap,0,0, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        x = (int)event.getX();
        y = (int)event.getY();

        switch (action){
            case MotionEvent.ACTION_DOWN :
                if(lastX != -1){
                    if(x != lastX || y != lastY){
                        mCanvas.drawLine(lastX, lastY, x, y, paint);
                    }
                }

                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if(lastX != -1){
                    mCanvas.drawLine(lastX, lastY, x, y, paint);
                }
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_UP:
                lastX = -1;
                lastY = -1;
                break;
        }

        invalidate();

        return true;
    }
}
