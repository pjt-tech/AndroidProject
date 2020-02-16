package com.kye.a_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity {

    int num1,num2;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button button = findViewById(R.id.button5);
        intent = getIntent();
        num1 = intent.getIntExtra("num1",0);
        num2 = intent.getIntExtra("num2",0);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                int result = num1 + num2;
                intent.putExtra("activity","Main3Activity");
                intent.putExtra("result",result);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
