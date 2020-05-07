package com.kye.audio_recoder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRecord,btnRecStop,btnPlay,btnStop;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO},0);

        btnRecord=findViewById(R.id.button);
        btnRecStop=findViewById(R.id.button2);
        btnPlay=findViewById(R.id.button3);
        btnStop=findViewById(R.id.button4);

        btnRecord.setOnClickListener(this);
        btnRecStop.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/recordData.mp3";

    }

    @Override
    public void onClick(View v) {

        if(v==btnRecord){
            if(mediaRecorder!=null){
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder=null;
            }else{
                mediaRecorder = new MediaRecorder();
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                mediaRecorder.setOutputFile(filePath);
                Toast.makeText(getApplicationContext(),"녹음을 시작합니다.",Toast.LENGTH_LONG).show();
                try {
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if(v==btnRecStop){
            if(mediaRecorder==null){
                return;
            }else{
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder=null;
                Toast.makeText(getApplicationContext(),"녹음을 중지합니다.",Toast.LENGTH_LONG).show();
            }
        }else if(v==btnPlay){
            if(mediaPlayer!=null){
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer=null;
            }else{
                mediaPlayer=new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(filePath);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(),"재생을 시작합니다.",Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if(v==btnStop){
            if(mediaPlayer==null){
                return;
            }else {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer=null;
                Toast.makeText(getApplicationContext(),"재생을 중지합니다.",Toast.LENGTH_LONG).show();
            }
        }
    }
}
