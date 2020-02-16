package com.kye.a_intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText editText, editText1;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button = findViewById(R.id.button3);
        Button button1 = findViewById(R.id.button4);
        TextView textView = findViewById(R.id.textView4);
        editText = findViewById(R.id.editText2);
        editText1 = findViewById(R.id.editText3);

        intent = getIntent();
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age", 0);

        textView.setText("이름 : " + name + ", 나이 : " + age);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.putExtra("activity", "Main2Activity");
                intent.putExtra("result", "잘받았습니다.");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), Main3Activity.class);
                intent.putExtra("num1", Integer.parseInt(editText.getText().toString()));
                intent.putExtra("num2", Integer.parseInt(editText1.getText().toString()));
                startActivityForResult(intent, 10);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10 || resultCode == RESULT_OK) {
            String name = data.getStringExtra("activity");
            double result = (double)data.getIntExtra("result",0);
            Toast.makeText(getApplicationContext(), "보낸액티비티 : " + name + " , " + result, Toast.LENGTH_LONG).show();
        }
    }
}
