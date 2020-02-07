package com.cyberkyj.chap08;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE},MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                Intent intent = new Intent(getApplicationContext(),NewActivity.class);
                intent.putExtra("activity","MainActivity");
                startActivityForResult(intent,1000);
                */

                /*
                Intent intent = new Intent();
                ComponentName name = new ComponentName("com.cyberkyj.another","com.cyberkyj.another.MainActivity");
                intent.setComponent(name);
                //intent.putExtra("name","Chap08_MainActivity");

                //Person person = new Person("Twice", 25);
                //Person person2 = new Person("APINK", 30);

                Person2 person = new Person2("Twice", 25);
                Person2 person2 = new Person2("APINK", 30);

                intent.putExtra("Person",person);
                intent.putExtra("Person2",person2);
                */

                Intent intent = new Intent();
                intent.setAction("action.ACTION_ANDROID_STUDY");
                startActivity(intent);

            }
        });


    }

   

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1000 && resultCode==RESULT_OK){
            String name = data.getStringExtra("name");
            int result = data.getIntExtra("sum",0);
            Toast.makeText(getApplicationContext(),"응답받은 액티비티 이름과 결과값 : "+name+", "+result,Toast.LENGTH_LONG).show();
        }
    }
}
