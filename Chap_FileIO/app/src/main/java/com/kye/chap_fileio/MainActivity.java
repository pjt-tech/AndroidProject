package com.kye.chap_fileio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    String fileName;
    Button button;
    String contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //위험권한

        ActivityCompat.requestPermissions(
                this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},0);

        DatePicker datePicker = findViewById(R.id.datePicker);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //SD카드 root디렉토리
        final String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/myDiary/";
        //디렉토리가 없으면 생성
        File dir = new File(path);
        if(!dir.isDirectory()){
            dir.mkdir();
        }

        fileName = year+"-"+(month+1)+"-"+day+".txt";
        contents = readDiary(path+fileName);
        editText.setText(contents);

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName = year+"-"+(monthOfYear+1)+"-"+ dayOfMonth+".txt";
                contents = readDiary(path+fileName);
                editText.setText(contents);
            }
        });

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               try {
                   FileOutputStream outputStream = new FileOutputStream(path+fileName);
                   String str = editText.getText().toString();
                   outputStream.write(str.getBytes());
                   outputStream.flush();
                   outputStream.close();
                   Toast.makeText(getApplicationContext(),path+fileName+"이 저장됨",Toast.LENGTH_LONG).show();
               } catch (Exception e) {
                   e.printStackTrace();
               }

           }
       });


    }

    private String readDiary(String name){
        String str = "";
        try {
            FileInputStream inputStream = new FileInputStream(name);
            byte[] txt = new byte[500];
            inputStream.read(txt);
            str = new String(txt).trim();
            button.setText("수정하기");
            inputStream.close();

        } catch (Exception e) {
            editText.setHint("일기 없음");
            button.setText("새로저장");
        }

        return str;
    }
}
