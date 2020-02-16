package com.kye.b_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Log.d("Logcat","onCreate실행");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Logcat","onStart실행");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Logcat","onStop실행");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Logcat","onRestart실행");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Logcat","onDestroy실행");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Logcat","onPause실행");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Logcat","onResume실행");

    }

    public void onCall(View view) {
    }
}
