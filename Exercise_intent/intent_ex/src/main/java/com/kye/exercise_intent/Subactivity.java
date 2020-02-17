package com.kye.exercise_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Subactivity extends AppCompatActivity {

    int sum = 0;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subactivity);

        Button button = findViewById(R.id.button2);

        String name = getIntent().getStringExtra("activity");
        Toast.makeText(getApplicationContext(),"보낸액티비티 : " + name, Toast.LENGTH_LONG).show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                for(int i = 0; i<10; i++){
                    sum += i;
                }
                intent.putExtra("sub","Subactivity");
                intent.putExtra("sub1",sum);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
