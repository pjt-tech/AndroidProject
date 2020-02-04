package com.kye.another;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = getIntent(); //get을 통해 받음
        String name = intent.getStringExtra("name"); //get을 통해 받음
        Toast.makeText(getApplicationContext(),"보낸 Activity : " + name,Toast.LENGTH_LONG).show();
    }
}
