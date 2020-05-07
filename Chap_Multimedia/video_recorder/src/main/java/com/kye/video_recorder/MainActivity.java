package com.kye.video_recorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String filePath;
    MediaRecorder recorder;
    MediaPlayer player;
    SurfaceHolder holder;
    String fileName;
    int fileIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO,Manifest.permission.CAMERA},0);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        SurfaceView surfaceView = new SurfaceView(this);
        holder = surfaceView.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        FrameLayout layout = findViewById(R.id.layout);
        layout.addView(surfaceView);


    }

    public void onRecStart(View view) throws Exception {
        if(recorder==null){
            recorder = new MediaRecorder();
        }
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        /*
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        recorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);
        recorder.setVideoEncodingBitRate(8*500000);
        recorder.setVideoSize(1280,720);
        recorder.setMaxDuration(60*1000);
        recorder.setMaxFileSize(5000000);
         */
        recorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_720P));
        fileName = filePath+"/RecordingData"+fileIndex+".mp4";
        fileIndex++;
        recorder.setOutputFile(fileName);
        recorder.setPreviewDisplay(holder.getSurface());

        recorder.prepare();
        recorder.start();

    }

    public void onRecStop(View view) {
        if(recorder==null){
            return;
        }
        recorder.stop();
        recorder.release();
        recorder=null;

    }

    public void onPlay(View view) {
        if(player==null){
            player = new MediaPlayer();
            try {
                player.setDataSource(fileName);
                player.setDisplay(holder);
                player.prepare();
                player.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onPlayStop(View view) {
        if(player==null){
            return;
        }else{
            player.stop();
            player.release();
            player=null;
        }
    }
}
