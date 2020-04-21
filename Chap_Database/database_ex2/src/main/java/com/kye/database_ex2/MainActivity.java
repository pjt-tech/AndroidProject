package com.kye.database_ex2;

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
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText db_Name;
    TextView status;
    ListView listView;
    String databaseName;
    int version=1;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    String table_Name = "customer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db_Name = findViewById(R.id.editText);
        status = findViewById(R.id.textView);
        Button query_Btn = findViewById(R.id.button);
        listView = findViewById(R.id.listView);

        query_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseName = db_Name.getText().toString();
                boolean isOpen = createDatabase();
                if(isOpen){
                    Cursor cursor = executeQuery();
                }
            }
        });
    }

    public boolean createDatabase(){

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        return true;
    }

    public Cursor executeQuery(){
        //String query = "select _id, name, age, phone from "+table_Name+";";
        String query2 = "select _id ,name ,age, phone from "+table_Name+" where age>?;";
        String[] args = {"30"};
        //Cursor cursor = db.rawQuery(query,null);
        Cursor cursor = db.rawQuery(query2,args);
        int recordCount = cursor.getCount();
        for(int i = 0; i<recordCount; i++){
            cursor.moveToNext();
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String phone = cursor.getString(3);

            println("Record #"+i+" : "+name+","+age+","+phone);
        }
        return cursor;
    }


    class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(@Nullable Context context) {
            super(context, databaseName, null, version);
            println("[" + databaseName + "] 데이터베이스가 생성되었습니다.");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("drop table if exists " + table_Name + ";");
            db.execSQL("create table if not exists " + table_Name + "(_id integer PRIMARY KEY autoincrement,"
                    + "name text,age integer,phone text);");
            println("[" + table_Name + "] 테이블이 생성되었습니다.");

            db.execSQL("insert into " + table_Name + "(name,age,phone) values('jone',20,'010-1000-1000');");
            db.execSQL("insert into " + table_Name + "(name,age,phone) values('Mike',26,'010-2000-2000');");
            db.execSQL("insert into " + table_Name + "(name,age,phone) values('Sean',35,'010-3000-3000');");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            println("[" + databaseName + "]"+"데이터베이스와[" + table_Name + "] 테이블을 열었습니다.");


        }

    }

    public void println(String msg){
        status.append("\n"+msg);
    }

}
