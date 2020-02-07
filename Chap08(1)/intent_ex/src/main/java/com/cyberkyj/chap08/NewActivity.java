package com.cyberkyj.chap08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {

    Intent intent;
    int sum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Button button = findViewById(R.id.button);

        intent = getIntent();
        String name = intent.getStringExtra("activity");
        Toast.makeText(getApplicationContext(), "보낸 액티비티명 : " + name, Toast.LENGTH_LONG).show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();

                for(int i=1; i<=100; i++){
                    sum+=i;
                }
                intent.putExtra("name","NewActivity");
                intent.putExtra("sum",sum);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
