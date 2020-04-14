package com.kye.filegallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    File[] imageFiles;
    String fileName;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},0);

        textView = findViewById(R.id.textView);
        Button btn_Pre = findViewById(R.id.button);
        Button btn_next = findViewById(R.id.button2);
        final MyPictureView myView = findViewById(R.id.myView);


        imageFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures").listFiles();
        fileName = imageFiles[0].toString();
        myView.setPath(fileName);
        textView.setText("1/"+imageFiles.length);
        myView.invalidate();

        btn_Pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index<=0){
                    index=imageFiles.length-1;
                }else{
                    index--;
                }

                fileName = imageFiles[index].toString();
                myView.setPath(fileName);
                myView.invalidate();
                textView.setText((index+1)+"/"+imageFiles.length);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index>=imageFiles.length-1){
                    index=0;
                }else{
                    index++;
                }

                fileName = imageFiles[index].toString();
                myView.setPath(fileName);
                myView.invalidate();
                textView.setText((index+1)+"/"+imageFiles.length);
            }
        });

    }
}
