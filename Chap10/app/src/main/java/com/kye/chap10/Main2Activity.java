package com.kye.chap10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button = findViewById(R.id.button2);
        textView = findViewById(R.id.textView2);
        setTitle("Activity2");

        Intent intent = getIntent();
        String name = intent.getStringExtra("a2");
        textView.setText(name);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Main3Activity.class);
                intent.putExtra("a3","A2에서 보낸값");
                startActivity(intent);
            }
        });
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("LifeCycle", "onNewIntent() 실행");
        String name = intent.getStringExtra("a2");
        textView.setText(name);
        //onNewIntent를 통한 값 재전달 재사용 일때 사용
    }
}
