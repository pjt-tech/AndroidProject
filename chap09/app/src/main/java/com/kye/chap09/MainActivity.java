package com.kye.chap09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("LifeCycle","onCreate() 실행");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LifeCycle","onStart() 실행");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LifeCycle","onStop() 실행");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycle","onDestroy() 실행");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LifeCycle","onPause() 실행");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LifeCycle","onResume() 실행");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LifeCycle","onRestart() 실행");
    }

    public void onNew(View view){
        Intent intent = new Intent(getApplicationContext(),NewActivity.class);
        startActivity(intent);
    }
}
