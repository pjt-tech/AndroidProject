package com.kye.chap_database;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.ContentValues;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText db_Name,tb_Name;
    TextView status;

    String database_name,table_name;
    Boolean database_create = false;
    Boolean table_create = false;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db_Name = findViewById(R.id.edtDbName);
        tb_Name = findViewById(R.id.edtTbName);
        status = findViewById(R.id.status);

        Button database_btn = findViewById(R.id.createDb);
        final Button table_btn = findViewById(R.id.createTb);

        database_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database_name = db_Name.getText().toString();
                createDatabase(database_name);
            }
        });

        table_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_name = tb_Name.getText().toString();
                createTable(table_name);
                int count = insertRecord(table_name);
                println(count+"개의 레코드를 입력하였습니다.");
            }
        });
    }


    public void createDatabase(String name) {
        if (database_create == false) {
            db = openOrCreateDatabase(name, MODE_PRIVATE, null);
            database_create = true;
            println("[" + name + "] 데이터베이스 생성이 완료되었습니다.");
        }
    }
    public void createTable(String name){
        if(database_create==true){
            db.execSQL("create table if not exists "+name+"(_id integer PRIMARY KEY autoincrement,"
                    +"name text,age integer,phone text);");
            table_create=true;
            println("[" + name + "] 테이블 생성이 완료되었습니다.");
        }
    }
    public void println(String msg){
        status.append("\n" + msg);
    }

    public int insertRecord(String name){
        if(table_create==true){
            db.execSQL("insert into "+name+"(name,age,phone) values('jone',20,'010-1000-1000');");
            db.execSQL("insert into "+name+"(name,age,phone) values('Mike',26,'010-2000-2000');");
            db.execSQL("insert into "+name+"(name,age,phone) values('Sean',35,'010-3000-3000');");
        }
        int count=3;
        return count;
    }

    public void insert_Click(View view) {
        ContentValues record_Value = new ContentValues();
        record_Value.put("name","Rice");
        record_Value.put("age",40);
        record_Value.put("phone","010-4000-4000");
        db.insert(table_name,null,record_Value);
        println("[" + table_name + "] 테이블에 1개의 데이터를 입력하였습니다.");
    }

    public void update_Click(View view) {
    }

    public void delete_Click(View view) {
    }
}
