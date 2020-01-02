package com.kye.chap07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout1 = findViewById(R.id.layout1);
        LinearLayout layout2 = findViewById(R.id.layout2);

        layout1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                float x = event.getX();
                float y = event.getY();

                if(action==MotionEvent.ACTION_DOWN) {
                    textView.append("손가락 눌림 : " + x + "," + y + "\n");
                }else if(action==MotionEvent.ACTION_HOVER_MOVE) {
                    textView.append("손가락 움직임 : " + x + "," + y + "\n");
                }else if(action==MotionEvent.ACTION_UP) {
                    textView.append("손가락 땜 : " + x + "," + y + "\n");
                }
                return false;
            }
        });
    }
}
