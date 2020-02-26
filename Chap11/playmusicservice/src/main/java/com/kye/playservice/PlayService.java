package com.kye.playservice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

public class PlayService extends Service implements MediaPlayer.OnCompletionListener {

    MediaPlayer mediaPlayer;
    String  filePath = "/data/data/com.kye.playservice/sample.mp3";

    public PlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(receiver,new IntentFilter("com.kye.PLAY_TO_SERVICE"));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        filePath = intent.getStringExtra("filePath");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Intent intent = new Intent("com.kye.PLAY_TO_ACTIVITY");
        intent.putExtra("mode","stop");
        sendBroadcast(intent);

        stopSelf();
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String mode = intent.getStringExtra("mode");
            if(mode!=null){
                if(mode.equals("start")){
                    try {
                        if(mediaPlayer!=null && mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                            mediaPlayer = new MediaPlayer();
                            mediaPlayer.setDataSource(filePath);
                            mediaPlayer.prepare();
                            mediaPlayer.start();

                            Intent intent1 = new Intent("com.kye.PLAY_TO_SERVICE");
                            intent.putExtra("mode","start");
                            intent.putExtra("duration",mediaPlayer.getDuration());
                            sendBroadcast(intent1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else if(mode.equals("stop")){
                    if(mediaPlayer!=null && mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                }
            }
        }
    };
}
