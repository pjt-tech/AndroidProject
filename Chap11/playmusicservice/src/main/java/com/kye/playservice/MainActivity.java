package com.kye.playservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView playBtn;
    ImageView stopBtn;
    ProgressBar progressBar;
    TextView textView;
    String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = findViewById(R.id.lab1_play);
        stopBtn = findViewById(R.id.lab1_stop);
        progressBar = findViewById(R.id.lab1_progress);
        textView = findViewById(R.id.lab1_title);

        filePath = "/data/data/com.kye.playservice/sample.mp3";

        playBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        stopBtn.setEnabled(false); //최초에 안눌리게 설정

        registerReceiver(receiver,new IntentFilter(PlayService.Tag));
    }

    @Override
    public void onClick(View v) {
        if(v==playBtn){
            Intent intent = new Intent(PlayService.Tag);
            intent.putExtra("mode" , "start");
            sendBroadcast(intent);
        }else if (v==stopBtn){
            Intent intent = new Intent(PlayService.Tag);
            intent.putExtra("mode" , "stop");
            sendBroadcast(intent);
        }
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };
}
