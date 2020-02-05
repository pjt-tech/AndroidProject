package com.kye.another;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// import
import com.kye.chap08.Person;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);

        intent = getIntent(); //get을 통해 받음
        //String name = intent.getStringExtra("name"); //get을 통해 받음
        //Toast.makeText(getApplicationContext(),"보낸 Activity : " + name,Toast.LENGTH_LONG).show();

        //getSerializableExtra 객체를 받는다
        Person person = (Person)intent.getSerializableExtra("Person");
        Person person1 = (Person)intent.getSerializableExtra("Person1");

        if(person==null && person1==null){
            Toast.makeText(getApplicationContext(),"Person 객체를 전달받지 못했습니다.",Toast.LENGTH_LONG).show();
            return;
        }

        //마치 동일 패키지에 있는 것처럼 객체를 사용
        textView.setText("이름 : " + person.getName()+", 나이 : "+person.getAge());
        textView.append("\n"+"이름 : " + person1.getName()+", 나이 : "+person1.getAge());
    }
}
