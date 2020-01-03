package com.kye.chap07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    GestureDetector gestureDetector;
    ScrollView scrollView;
    final int SWIPE_MIN_DISTANCE = 120;  // SWIPE를 인식하는 가장 작은 거리
    final int SWIPE_MAX_OFF_PATH = 250;  // 인식하는 최대 거리
    final int SWIPE_THRSHOLD_VELOCITY = 2000; //임의의 속도
    long initTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout1 = findViewById(R.id.layout1);
        LinearLayout layout2 = findViewById(R.id.layout2);
        textView = findViewById(R.id.textView);
        scrollView = findViewById(R.id.scrollView);

        layout1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                float x = event.getX();
                float y = event.getY();

                if(action==MotionEvent.ACTION_DOWN) {
                    textView.append("손가락 눌림 : " + x + "," + y + "\n");
                }else if(action==MotionEvent.ACTION_MOVE) {
                    textView.append("손가락 움직임 : " + x + "," + y + "\n");
                }else if(action==MotionEvent.ACTION_UP) {
                    textView.append("손가락 땜 : " + x + "," + y + "\n");
                }
                scrollView.fullScroll(scrollView.FOCUS_DOWN);
                return false;
            }
        });

        layout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                scrollView.fullScroll(scrollView.FOCUS_DOWN);
                return false;
            }
        });

        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                textView.append("onDown 호출됨"+"\n");
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                textView.append("onShowPress 호출됨"+"\n");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                textView.append("onSingleTapUp 호출됨"+"\n");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                textView.append("onScroll 호출됨"+"\n");
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                textView.append("onLongPress 호출됨"+"\n");

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                //textView.append("onFling 호출됨"+"\n");

                textView.append("속도출력 : " +velocityX+","+velocityY);
                if(e1.getX()-e2.getX()>SWIPE_MIN_DISTANCE && Math.abs(velocityX)>SWIPE_THRSHOLD_VELOCITY){
                    Toast.makeText(getApplicationContext(),"Left SWIPE",Toast.LENGTH_LONG).show();
                }else if(e2.getX()-e1.getX()>SWIPE_MIN_DISTANCE && Math.abs(velocityX)>SWIPE_THRSHOLD_VELOCITY) {
                    Toast.makeText(getApplicationContext(), "Right SWIPE", Toast.LENGTH_LONG).show();
                }else if(e1.getY()-e2.getY()>SWIPE_MIN_DISTANCE && Math.abs(velocityY)>SWIPE_THRSHOLD_VELOCITY) {
                    Toast.makeText(getApplicationContext(), "Up SWIPE", Toast.LENGTH_LONG).show();
                }else if(e2.getY()-e1.getY()>SWIPE_MIN_DISTANCE && Math.abs(velocityY)>SWIPE_THRSHOLD_VELOCITY) {
                    Toast.makeText(getApplicationContext(), "Down SWIPE", Toast.LENGTH_LONG).show();
                }
                    return false;
            }
        });
    }
/*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis()-initTime>3000){
                Toast.makeText(getApplicationContext(),"종료하려면 한번 더 누르세요.",Toast.LENGTH_LONG).show();
                initTime = System.currentTimeMillis();
            }else{
                finish();
            }
        }

        return true;

       // return super.onKeyDown(keyCode, event);
    }
*/
    @Override
    public void onBackPressed() {

            if(System.currentTimeMillis()-initTime>3000){
                Toast.makeText(getApplicationContext(),"종료하려면 한번 더 누르세요.",Toast.LENGTH_LONG).show();
                initTime = System.currentTimeMillis();
            }else{
                finish();
            }
       //super.onBackPressed();
    }
}
