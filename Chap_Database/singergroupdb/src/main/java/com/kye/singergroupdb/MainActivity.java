package com.kye.singergroupdb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtName,edtNumber,edtNameResult,edtNumberResult;
    String db_name = "girlgroup.db";
    int version = 1;
    MyDbhelper dbhelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.editText);
        edtNumber = findViewById(R.id.editText2);
        edtNameResult = findViewById(R.id.editText3);
        edtNumberResult = findViewById(R.id.editText4);

        Button btnInit = findViewById(R.id.button);
        Button btnInsert = findViewById(R.id.button2);
        Button btnUpdate = findViewById(R.id.button3);
        Button btnDelete = findViewById(R.id.button4);
        final Button btnSelect = findViewById(R.id.button5);

        dbhelper = new MyDbhelper(this);
        db = dbhelper.getWritableDatabase();

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhelper.onUpgrade(db,version,2);
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtName.getText().toString();

                if(!(name.isEmpty())) {
                    int num = Integer.parseInt(edtNumber.getText().toString());
                        db.execSQL("insert into groupTBL values('" + name + "', " + num + ");");
                        Toast.makeText(getApplicationContext(), "데이터가 입력되었습니다.", Toast.LENGTH_LONG).show();
                        edtName.setText("");
                        edtNumber.setText("");
                        //btnSelect 콜백시켜줌
                        btnSelect.callOnClick();
                }
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = db.rawQuery("select * from groupTBL;",null);
                String strNames = "그룹이름"+"\n"+"________"+"\n";
                String strNumbers = "인원"+"\n"+"____"+"\n";
                while (cursor.moveToNext()){
                    strNames+=cursor.getString(0)+"\n";
                    strNumbers+=cursor.getInt(1)+"\n";
                }

                edtNameResult.setText(strNames);
                edtNumberResult.setText(strNumbers);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = edtName.getText().toString();

                if(!(name.isEmpty())) {
                    int num = Integer.parseInt(edtNumber.getText().toString());
                    db.execSQL("update groupTBL set gNumber= " + num + " where gName='" + name + "';");
                    Toast.makeText(getApplicationContext(), "데이터가 수정되었습니다.", Toast.LENGTH_LONG).show();
                    edtName.setText("");
                    edtNumber.setText("");
                    //btnSelect 콜백시켜줌
                    btnSelect.callOnClick();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                if(!(name.isEmpty())){
                    db.execSQL("delete from groupTBL Where gName='"+name+"';");
                    edtName.setText("");
                    btnSelect.callOnClick();
                    Toast.makeText(getApplicationContext(), "데이터가 삭제되었습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public class MyDbhelper extends SQLiteOpenHelper{

        public MyDbhelper(@Nullable Context context) {
            super(context, db_name, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table groupTBL(gName varchar(20) PRIMARY KEY,gNumber integer);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists groupTBL");
            onCreate(db);
            Toast.makeText(getApplicationContext(),"초기화 완료~!",Toast.LENGTH_LONG).show();
        }
    }
}
