package com.kye.b_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d("Logcat","onCreate실행");

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Toast.makeText(this, "보낸 액티비티 " + name, Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(getApplicationContext(),Main3Activity.class);
        intent.putExtra("name","MainActivity");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
