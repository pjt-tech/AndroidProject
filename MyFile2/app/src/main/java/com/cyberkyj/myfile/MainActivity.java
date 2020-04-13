package com.cyberkyj.myfile;

import android.Manifest;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    String fileName, contents;
    EditText editText1, editText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},200);

        editText1 = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);

        Button button1 = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileName = editText1.getText().toString();
                contents = editText2.getText().toString();

                fileName = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+fileName;
                Toast.makeText(getApplicationContext(),"File Name : "+fileName, Toast.LENGTH_SHORT).show();
                writeToFile(fileName, contents);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileName = editText1.getText().toString();
                fileName = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+fileName;
                readFromFile(fileName);
            }
        });
    }

    public void writeToFile(String fileName, String contents){
        File file = new File(fileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            dataOutputStream.writeUTF(contents);

            dataOutputStream.flush();
            fileOutputStream.close();
            dataOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"파일에 쓰기 실패", Toast.LENGTH_SHORT).show();
        }

    }

    public void readFromFile(String fileName){
        File file = new File(fileName);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            String contents = dataInputStream.readUTF();
            editText2.setText(contents);

            dataInputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"파일에 읽기 실패", Toast.LENGTH_SHORT).show();
        }

    }
}
