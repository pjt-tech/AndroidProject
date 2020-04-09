package com.kye.imagmove;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {


    public static final int HORIZONTAL = 1;
    public static final int VERTICAL=2;
    ImageView dog1,dog2;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dog1 = findViewById(R.id.imageView);
        dog2 = findViewById(R.id.imageView2);

        dog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DogThread dogThread1 = new DogThread(HORIZONTAL,v);
                dogThread1.start();
            }
        });

        dog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DogThread dogThread2 = new DogThread(VERTICAL,v);
                dogThread2.start();
            }
        });

    }

    class DogThread extends Thread{

        int direction;
        View v;
        int margin = 0;

        boolean running = false;
        boolean forward = true;



        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        public DogThread(int direction, View v){
            this.direction = direction;
            this.v = v;
        }

        public void run(){
           running = true;
           while (running) {
               if (direction == HORIZONTAL) {
                   if (margin > 700) {
                       forward = false;
                   }
               } else if (direction == VERTICAL) {
                   if (margin > 1200) {
                       forward = false;
                   }
               }

               if (forward) {
                   margin += 10;
               } else {
                   margin -= 10;
                   if (direction == VERTICAL) {
                       if (margin < 250) {
                           break;
                       }
                   }
               }
                if(margin<=0){
                 break;
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(direction==HORIZONTAL){
                            params.leftMargin=margin;
                        }else if(direction==VERTICAL){
                            params.topMargin=margin;
                        }
                        v.setLayoutParams(params);
                        v.invalidate();
                    }
                });
               try {
                   Thread.sleep(20);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }
    }
}
