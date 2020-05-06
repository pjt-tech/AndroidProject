package com.kye.videoplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
        import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
        import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    String video_URL ="https://sites.google.com/site/ubiaccessmobile/sample_video.mp4";
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},0);

        Button btnStart = findViewById(R.id.button);
        Button btnVol = findViewById(R.id.button2);
        videoView = findViewById(R.id.videoView);


        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/mv.mp4";

        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);
        videoView.setVideoPath(filePath);
        //videoView.setVideoURI(Uri.parse(video_URL));
        videoView.requestFocus();



        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(),"동영상이 준비되었습니다. \n 재생버튼을 누르세요",Toast.LENGTH_LONG).show();

            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(),"동영상 재생이 완료되었습니다.",Toast.LENGTH_LONG).show();
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.seekTo(0);
                videoView.start();
            }
        });
        btnVol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManager manager = (AudioManager)getSystemService(AUDIO_SERVICE);
                int max = manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                manager.setStreamVolume(AudioManager.STREAM_MUSIC,max,AudioManager.FLAG_SHOW_UI);
            }
        });
    }
}
