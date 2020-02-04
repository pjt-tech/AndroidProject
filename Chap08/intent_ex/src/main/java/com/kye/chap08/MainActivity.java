package com.kye.chap08;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            //Intent는 우편배달부
            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent(getApplicationContext(), NewActivity.class); //클래스 생성자 뒤에는 context와 class를 쓸수있다
                intent.putExtra("activity", "MainActivity"); //키와 값을 입력
                startActivityForResult(intent, 1000); //누가보낸건지 가리기 위해 요청코드를 입력할수있음. 결과를 기대하며 보냄
                */
                Intent intent = new Intent();
                ComponentName name = new ComponentName("com.kye.another","com.kye.another.MainActivity");
                intent.setComponent(name);
                intent.putExtra("name","Chap08_MainActivity");
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            int result = data.getIntExtra("sum", 0);
            Toast.makeText(getApplicationContext(), "응답받은 Activity 결과 값 :" + name + "," + result, Toast.LENGTH_LONG).show();
        }
    }
}