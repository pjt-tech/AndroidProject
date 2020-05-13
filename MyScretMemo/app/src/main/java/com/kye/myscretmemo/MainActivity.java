package com.kye.myscretmemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMemo,btnPassword,btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMemo = findViewById(R.id.button);
        btnPassword = findViewById(R.id.button2);
        btnFinish = findViewById(R.id.button3);

        btnMemo.setOnClickListener(this);
        btnPassword.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

    }

    public void onClick(View v){

        if(v==btnMemo){

        }else if(v==btnPassword){


        }else if(v==btnFinish){
            finish();
        }
    }
}
