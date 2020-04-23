package com.kye.mydiary_db;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    String contents;
    String DB = "myDB";
    int version = 1;
    String table_name = "myDiary";
    MyDbhelper dbHelper;
    SQLiteDatabase db;
    int year,month,day;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        DatePicker datePicker = findViewById(R.id.datePicker);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new MyDbhelper(getApplicationContext());
                db = dbHelper.getWritableDatabase();
                insertDate(db);
            }
        });
    }

    public void insertDate(SQLiteDatabase db){
        contents = editText.getText().toString();
        db.execSQL("insert into "+ table_name +" values('데이터','" + contents + "')");
        Toast.makeText(getApplicationContext(),"데이터 입력이 완료되었습니다.",Toast.LENGTH_LONG).show();
        button.setText("수정하기");
    }



    public class MyDbhelper extends SQLiteOpenHelper {

        public MyDbhelper(@Nullable Context context) {
            super(context, DB, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table "+table_name+"(diaryDate char(10) PRIMARY KEY,content varchar(500));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists myDiary");
            onCreate(db);

        }
    }
}
