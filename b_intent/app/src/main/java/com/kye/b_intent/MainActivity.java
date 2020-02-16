package com.kye.b_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName("com.kye.a_intent","com.kye.a_intent.MainActivity");
                intent.setComponent(componentName);
                startActivity(intent);
            }
        });

    }
    public void onCall(View view) {
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        intent.putExtra("name","MainActivity");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
