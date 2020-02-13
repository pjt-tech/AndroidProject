package com.kye.chap10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        Button button1 = findViewById(R.id.button4);

        setTitle("Activity");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                intent.putExtra("a2","A1에서 보낸값");
                startActivity(intent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //ComponentName componentName = new ComponentName("com.kye.chap09","com.kye.chap09.MainActivity");
               // intent.setComponent(componentName);
                intent.setAction("android.intent_ACTION_PLUS");
                startActivity(intent);
            }
        });

    }
}

