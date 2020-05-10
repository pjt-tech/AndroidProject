package com.kye.musiclist2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tvName,tvTime;
    ProgressBar pb;
    String mp3Path,selectMp3;
    ArrayList<String> mp3List;
    Button btnPlay,btnPause,btnStop;
    MediaPlayer player;
    SeekBar seekBar;
    boolean pause = false;
    boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},0);

        btnPlay = findViewById(R.id.button);
        btnPause = findViewById(R.id.button2);
        btnStop = findViewById(R.id.button3);
        pb = findViewById(R.id.progressBar);
        tvName = findViewById(R.id.textView);
        ListView listView = findViewById(R.id.listView);
        tvTime = findViewById(R.id.textView3);
        seekBar = findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (seekBar.getMax()==progress) {
                    btnPlay.setClickable(true);
                    btnPause.setClickable(false);
                    btnStop.setClickable(false);
                    isPlaying = false;
                    player.stop();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isPlaying = false;
                player.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isPlaying = true;
                int ttt = seekBar.getProgress(); // 사용자가 움직여놓은 위치
                player.seekTo(ttt);
                player.start();
                new MusicThread().start();

            }
        });


        mp3Path = Environment.getExternalStorageDirectory().getPath()+"/Download/";
        //mp3Path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/";
        mp3List = new ArrayList<>();
        File[] files = new File(mp3Path).listFiles();
        String fileName,edtName;
        for(File file : files){
            fileName = file.getName();
            edtName = fileName.substring(fileName.length()-3);
            if(edtName.equals("mp3")) {
                mp3List.add(fileName);
            }
        }
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

        btnPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(player!=null){
                        player.release();
                        player=null;

                    }
                    player = new MediaPlayer();
                    player.setDataSource(mp3Path + selectMp3);
                    player.prepare();
                    player.start();
                    btnPlay.setVisibility(View.INVISIBLE);
                    btnPause.setVisibility(View.VISIBLE);
                    btnStop.setVisibility(View.VISIBLE);
                    btnPause.setClickable(true);
                    btnStop.setClickable(true);
                    tvName.setText("실행중인 음악 : " + selectMp3);
                    pb.setVisibility(View.VISIBLE);
                    int a = player.getDuration(); // 노래의 재생시간(miliSecond)
                    seekBar.setMax(a);// 씨크바의 최대 범위를 노래의 재생시간으로 설정
                    isPlaying = true; // 씨크바 쓰레드 반복 하도록
                    new MusicThread().start(); // 씨크바 그려줄 쓰레드 시작

                } catch (IOException e) {
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (pause == false) {
                    player.pause();
                    btnPause.setText("이어듣기");
                    pause = true;
                    pb.setVisibility(View.INVISIBLE);
                    isPlaying = false; // 쓰레드 정지
                } else {
                    player.start();
                    pause = false;
                    btnPause.setText("일시정지");
                    pb.setVisibility(View.VISIBLE);
                    isPlaying = true; // 재생하도록 flag 변경
                    new MusicThread().start(); // 쓰레드 시작
                }
            }
        });
        btnPause.setClickable(false);


        btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                player.stop();
                player.reset();
                btnPlay.setClickable(true);
                btnPlay.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.INVISIBLE);
                btnStop.setVisibility(View.INVISIBLE);
                tvName.setText("실행중인 음악 : ");
                pb.setVisibility(View.INVISIBLE);
                isPlaying = false;
                seekBar.setProgress(0); // 씨크바 초기화
            }
        });
        btnStop.setClickable(false);

    }
    class MusicThread extends Thread {
        @Override
        public void run() { // 쓰레드가 시작되면 콜백되는 메서드
            // 씨크바 막대기 조금씩 움직이기 (노래 끝날 때까지 반복)
            final SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
            while(isPlaying) {
                        seekBar.setProgress(player.getCurrentPosition());
                        tvTime.setText(timeFormat.format(player.getCurrentPosition()));
            }
        }
    }
}

