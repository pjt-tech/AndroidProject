package com.kye.myscretmemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.File;
import java.util.ArrayList;

public class MemoAdapter extends BaseAdapter {
    Context context;
    ArrayList<MemoItem> items = new ArrayList<>();
    String imagePath;

    public MemoAdapter(Context context) {
        this.context = context;
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
        MemoItemView view = null;
        if(convertView==null){
            view = new MemoItemView(context);
        }else{
            view = (MemoItemView)convertView;
        }
        MemoItem item = items.get(position);
        view.setTxtContents(item.getContents());
        view.setTxtName(item.getFriendName());
        view.setTxtMobile(item.getFriendphone());
        view.setTxtTimeStamp(item.getTimeStemp());
        imagePath = item.getImagePath();
        if(imagePath.equals("")){
            view.setImageView(R.mipmap.ic_launcher);
        }else {
            File file = new File(imagePath);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize=8;
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(),options);
            Matrix matrix = new Matrix();
            matrix.postRotate(90);

            Bitmap rotateBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            bitmap = rotateBitmap;
            view.setImageBitmap(bitmap);
        }

        return view;
    }

    public void addItem(MemoItem item){
        items.add(item);
    }
}
