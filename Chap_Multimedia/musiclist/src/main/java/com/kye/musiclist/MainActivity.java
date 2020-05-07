package com.kye.musiclist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends Activity {

    ListView listViewMP3;
    Button btnPlay, btnPause, btnStop;
    TextView tvMP3;
    ProgressBar pbMP3;

    ArrayList<String> mp3List;
    String selectedMP3;

    String mp3Path;
    MediaPlayer mPlayer;

    boolean PAUSED = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 MP3 플레이어");
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},0);



        mp3Path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/";
        mp3List = new ArrayList<String>();

        File[] listFiles = new File(mp3Path).listFiles();
        String fileName, extName;
        for (File file : listFiles) {
            fileName = file.getName();
            extName = fileName.substring(fileName.length() - 3);
            if (extName.equals((String) "mp3"))
                mp3List.add(fileName);
        }

        ListView listViewMP3 = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, mp3List);
        listViewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewMP3.setAdapter(adapter);
        listViewMP3.setItemChecked(0, true);

        listViewMP3
                .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int arg2, long arg3) {
                        selectedMP3 = mp3List.get(arg2);
                    }
                });
        selectedMP3 = mp3List.get(0);

        btnPlay = (Button) findViewById(R.id.button);
        btnPause = (Button) findViewById(R.id.button3);
        btnStop = (Button) findViewById(R.id.button2);
        tvMP3 = (TextView) findViewById(R.id.textView);
        pbMP3 = (ProgressBar) findViewById(R.id.progressBar);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    mPlayer = new MediaPlayer();
                    mPlayer.setDataSource(mp3Path + selectedMP3);
                    mPlayer.prepare();
                    mPlayer.start();
                    btnPlay.setClickable(false);
                    btnPause.setClickable(true);
                    btnStop.setClickable(true);
                    tvMP3.setText("실행중인 음악 : " + selectedMP3);
                    pbMP3.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (PAUSED == false) {
                    mPlayer.pause();
                    btnPause.setText("이어듣기");
                    PAUSED = true;
                    pbMP3.setVisibility(View.INVISIBLE);
                } else {
                    mPlayer.start();
                    PAUSED = false;
                    btnPause.setText("일시정지");
                    pbMP3.setVisibility(View.VISIBLE);
                }
            }
        });
        btnPause.setClickable(false);


        btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPlayer.stop();
                mPlayer.reset();
                btnPlay.setClickable(true);
                btnPause.setClickable(false);
                btnPause.setText("일시정지");
                btnStop.setClickable(false);
                tvMP3.setText("실행중인 음악 : ");
                pbMP3.setVisibility(View.INVISIBLE);
            }
        });
        btnStop.setClickable(false);

    }
}
