package com.kye.orientation_ex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int sum = 0;
    TextView textView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(),"onCreate 호출됨",Toast.LENGTH_SHORT).show();
        Button button = findViewById(R.id.button);
        textView = findViewById(R.id.textView2);
        editText= findViewById(R.id.editText);

        if(savedInstanceState!=null){
            int score = savedInstanceState.getInt("num");
            textView.setText("출력결과 : " + score);
            String content =savedInstanceState.getString("content");
            editText.setText(content);

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i<=100; i++){
                    sum+=i;


                }
                textView.setText("출력결과 : " + sum);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),"onStart 호출됨",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(),"onStop 호출됨",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"onDestroy 호출됨",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("num",sum);
        String result = editText.getText().toString();
        outState.putString("content",result);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            Toast.makeText(getApplicationContext(),"방향 : ORIENTATION_LANDSCAPE",Toast.LENGTH_LONG).show();
        }else if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(getApplicationContext(),"방향 : ORIENTATION_PORTRAIT",Toast.LENGTH_LONG).show();
        }
    }
}

