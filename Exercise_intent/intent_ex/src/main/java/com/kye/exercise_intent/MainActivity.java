package com.kye.exercise_intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        Button button1 = findViewById(R.id.button3);
        Button button2 = findViewById(R.id.button4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent = new Intent(getApplicationContext(),Subactivity.class);
              intent.putExtra("activity","MainActivity");
              startActivityForResult(intent,10);
              //결과값을 받거나 보낼일이 없을경우 한줄로 화면이동이가능하다.
                //startActivity(new Intent(getApplicationContext(),Subactivity.class));
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                ComponentName componentName = new ComponentName("com.kye.intent_ex1","com.kye.intent_ex1.MainActivity");
                intent.putExtra("app","intent_ex");
                intent.setComponent(componentName);
                startActivityForResult(intent,20);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setAction("android.intent.action.ACTION_STUDY");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 10 && resultCode == RESULT_OK){
               String name = data.getStringExtra("sub");
               int result  = data.getIntExtra("sub1",0);
               Toast.makeText(getApplicationContext(),"보낸액티비티 : " + name +", 전달받은 값 : " + result ,Toast.LENGTH_LONG).show();
        }else if(requestCode == 20 && resultCode == RESULT_OK){
            String name = data.getStringExtra("app");
            int result  = data.getIntExtra("app1",0);
            Toast.makeText(getApplicationContext(),"보낸액티비티 : " + name +", 전달받은 값 : " + result ,Toast.LENGTH_LONG).show();
        }
    }
}
