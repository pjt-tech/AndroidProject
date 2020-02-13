package com.kye.chap10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {


    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button button = findViewById(R.id.button3);
        textView = findViewById(R.id.textView3);
        setTitle("Activity3");


        Intent intent = getIntent();
        String name = intent.getStringExtra("a3");
        textView.setText(name);

        Log.d("LifeCycle", "onCreate() 실행");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Main3Activity.class);
                intent.putExtra("a3","A3다시호출");
                startActivity(intent);
            }

        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("LifeCycle", "onNewIntent() 실행");
        String name = intent.getStringExtra("a3");
        textView.setText(name);
        //onNewIntent를 통한 값 재전달 Singletop 일때 사용
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LifeCycle", "onStart() 실행");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LifeCycle", "onStop() 실행");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycle", "onDestroy() 실행");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LifeCycle", "onPause() 실행");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LifeCycle", "onResume() 실행");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LifeCycle", "onRestart() 실행");
    }
}
