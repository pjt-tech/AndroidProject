package com.kye.timer_thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ImageView startView,pauseView;
    boolean isRun = false;
    boolean isRoll = true;
    Mythread myThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.main_textView);
        startView = findViewById(R.id.main_startBtn);
        pauseView = findViewById(R.id.main_pauseBtn);

        myThread = new Mythread();

    }

    public void startView(View view) {

        isRun = true;


    }
    public void pauseView(View view) {

        isRun=false;


    }

    class Mythread extends Thread{

        public void run(){


                try {
                    int count = 10;

                    while(isRun) {

                        sleep(1000);
                        count--;
                        Message message = handler.obtainMessage();
                        message.what = 1;
                        message.arg1 = count;
                        handler.sendMessage(message);

                        if(count==0){
                            message = new Message();
                            message.what = 2;
                            message.obj = "Finish";
                            handler.sendMessage(message);
                            isRun = false;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==1){
                textView.setText(String.valueOf(msg.arg1));
            }else if(msg.what==2){
                textView.setText((String)msg.obj);
            }
        }
    };
}
