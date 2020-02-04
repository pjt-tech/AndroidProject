package com.kye.self_check01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText editText,editText1;
    Singer singer;
    TextView textView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText1);
        editText1 = findViewById(R.id.editText2);
        textView1 = findViewById(R.id.textView1);

    }
    public void onButton1Clicked (View v){
       int age1 = Integer.parseInt(editText1.getText().toString());
       creatSinger(editText.getText().toString(),age1);
       Toast.makeText(getApplicationContext(),"가수 추가됨 :" + singer.name +"가수의 나이 :" + singer.age,Toast.LENGTH_LONG).show();
       String result = String.valueOf(singer.Total);
       textView1.setText("추가된 총수:"+result+"명");
    }

    public void onButton2Clicked (View v){
        int age1 = Integer.parseInt(editText1.getText().toString());
        creatGirlGruop(editText.getText().toString(),age1);
        Toast.makeText(getApplicationContext(),"걸그룹 추가됨 :" + singer.name +"걸그룹 나이 :" + singer.age,Toast.LENGTH_LONG).show();
        String result = String.valueOf(singer.Total);
        textView1.setText("추가된 총수:"+result+"명");
    }

    public void creatSinger(String name , int age){

        singer = new Singer(name,age,MainActivity.this);
    }

    public void creatGirlGruop(String name , int age){
        singer = new GirlGruoupSinger(name,age,MainActivity.this);
    }

}
