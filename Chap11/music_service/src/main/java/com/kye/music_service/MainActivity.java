package com.kye.music_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_Start = findViewById(R.id.button);
        Button btn_Stop = findViewById(R.id.button2);

        btn_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startService(new Intent(getApplicationContext(),MusicService.class));
            }
        });

        btn_Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplicationContext(),MusicService.class));
            }
        });
    }
}
