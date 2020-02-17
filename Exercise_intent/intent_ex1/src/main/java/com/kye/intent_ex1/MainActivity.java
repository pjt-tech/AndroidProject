package com.kye.intent_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    int sum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        String name = getIntent().getStringExtra("app");
        Toast.makeText(getApplicationContext(),"보낸 액티비티 : " + name , Toast.LENGTH_LONG).show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                for(int i = 0; i<100; i++) {
                    sum += i;
                }
                    intent.putExtra("app","intent_ex1");
                    intent.putExtra("app1",sum);
                    setResult(RESULT_OK,intent);
                    finish();

            }
        });

    }
}
