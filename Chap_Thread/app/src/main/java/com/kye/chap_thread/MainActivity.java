package com.kye.chap_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
   // r_Handler handler = new r_Handler();
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
    }

    public void onThread(View view) {
        r_Thread thread = new r_Thread();
        thread.start();
    }

    class r_Thread extends Thread{

        public void run(){
            for(int i=0; i<100; i++){
                final String str = "#"+i+"호출됨";

                /*
                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putString("data", str);
                message.setData(bundle);
                handler.sendMessage(message);
                */


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(str);
                    }
                });


                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }


    }

    class r_Handler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String str = bundle.getString("data");
            textView.setText(str);
        }
    }
}
