package com.kye.chap_multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStart,btnPause,btnRestart;
    static final  String AUDIO_URL = "https://sites.google.com/site/ubiaccessmobile/sample_audio.mp3";
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.button);
        btnPause = findViewById(R.id.button2);
        btnRestart = findViewById(R.id.button3);
        btnStart.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnRestart.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if(v==btnStart){
            try {
                playAudio(AUDIO_URL);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void playAudio(String url) throws IOException {
        killPlayer();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(url);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    private void killPlayer(){
        if(mediaPlayer!=null){
            mediaPlayer.release();
        }
    }
}
