package com.kye.myaudioplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String selectMp3,mp3path;
    ArrayList<String> mp3List;
    ListView listView;
    TextView tvMp3, tvTime;
    MediaPlayer player;
    Button btnPlay,btnPause,btnStop;
    boolean PLAYER = false;
    SeekBar pbMP3;
    MusicThread thread;
    ProgressBar progressBar;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        mp3List = new ArrayList<>();

        //에뮬연결시
        mp3path = Environment.getExternalStorageDirectory().getPath()+"/";

        //단말기 연결시
        //mp3path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/mp3/";
        File[] files = new File(mp3path).listFiles();
        String fileName,mpName;
        for(File file : files) {
            fileName = file.getName();
            mpName = fileName.substring(fileName.length()-3);
            if (mpName.equals("mp3")) {
                mp3List.add(fileName);
            }
        }
            listView = findViewById(R.id.listViewMP3);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice,mp3List);
            listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
            listView.setAdapter(adapter);
            listView.setItemChecked(0,true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectMp3 = mp3List.get(position);
                }
            });
            selectMp3 = mp3List.get(0);


        pbMP3 = findViewById(R.id.pbMP3);
        tvMp3 = findViewById(R.id.tvMP3);
        tvTime = findViewById(R.id.tvTime);
        progressBar =findViewById(R.id.progressBar);

        btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thread==null) {
                    thread = new MusicThread();
                    thread.start();
                }

                if(player!=null){
                    player.release();
                    player=null;
                }else{
                    try {
                        player = new MediaPlayer();
                        player.setDataSource(mp3path+selectMp3);
                        player.prepare();
                        player.start();
                        btnPlay.setClickable(false);
                        btnPause.setClickable(true);
                        btnStop.setClickable(true);
                        tvMp3.setText("실행중인 음악 :" + selectMp3);
                        progressBar.setVisibility(View.VISIBLE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        btnPause = findViewById(R.id.btnPause);
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PLAYER==false) {
                    player.pause();
                    PLAYER = true;
                    btnPause.setText("이어듣기");
                    progressBar.setVisibility(View.INVISIBLE);
                }else{
                    player.start();
                    PLAYER = false;
                    btnPause.setText("일시정지");
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        btnPause.setClickable(false);


        btnStop = findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
                player.reset();
                player=null;
                btnPlay.setClickable(true);
                btnPause.setClickable(false);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        btnStop.setClickable(false);
    }


    class MusicThread extends Thread{
        @Override
        public void run() {
            pbMP3.setProgress(0);
            try {
                for(int i = 0; i<100; i++){
                    thread.sleep(2000);
                    pbMP3.setProgress(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
