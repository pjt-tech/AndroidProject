package com.kye.myapplink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);
        Intent intent = getIntent();
        Uri uri = intent.getData();
        if(uri!=null){
            String data = uri.getQueryParameter("photoNumber");
            int number = Integer.parseInt(data);
            if(number==1){
                imageView.setImageResource(R.drawable.jeju1);
            }else if(number==2){
                imageView.setImageResource(R.drawable.jeju2);
            }else if(number==3){
                imageView.setImageResource(R.drawable.jeju3);
            }
        }

    }
}
