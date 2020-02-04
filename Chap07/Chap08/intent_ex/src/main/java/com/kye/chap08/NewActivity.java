package com.kye.chap08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {

    Intent intent;
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);


        Button button = findViewById(R.id.button);

        intent = getIntent(); //get을 통해 받음
        String name = intent.getStringExtra("activity"); //get을 통해 받음
        Toast.makeText(getApplicationContext(),"보낸 Activity : " + name,Toast.LENGTH_LONG).show();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent();
                for(int i =1;  i<=100; i++){
                    sum+=i;
                }
                intent.putExtra("name","NewActivity");
                intent.putExtra("sum",sum); //값을 다시보내줌
                setResult(RESULT_OK,intent); //요청했던 액티비티에게 답을 보낼때 , RESULT_OK = 데이터가 제대로왔다는 신호
                finish();
            }
        });
    }
}
