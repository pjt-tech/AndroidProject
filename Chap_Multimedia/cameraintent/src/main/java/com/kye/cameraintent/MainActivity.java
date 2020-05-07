package com.kye.cameraintent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class MainActivity extends AppCompatActivity {

    File file;
    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},0);

        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"intentCamera.jpg");
        imageView = findViewById(R.id.imageView);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) {
                    Uri fileURI = FileProvider.getUriForFile(getApplicationContext(), "com.kye.cameraintent.fileprovider", file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileURI);
                }else{
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(file));
                }
                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(intent,1000);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(),options);
            Resources r = Resources.getSystem();
            Configuration configuration = r.getConfiguration();
            if(configuration.orientation==ORIENTATION_LANDSCAPE) {
                //가로방향
                imageView.setImageBitmap(bitmap);
            }else{
                //세로모드
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                Bitmap rotatebitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
                bitmap = rotatebitmap;
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==ORIENTATION_LANDSCAPE){
            imageView.setImageBitmap(bitmap);
        }else {
            imageView.setImageBitmap(bitmap);
        }
    }
}
