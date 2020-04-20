package com.kye.chap_database;

        import androidx.appcompat.app.AppCompatActivity;

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
        Button table_btn = findViewById(R.id.createTb);

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

            }
        });

    }

    public void insert_Click(View view) {
    }

    public void update_Click(View view) {
    }

    public void delete_Click(View view) {
    }
    public void createDatabase(String name) {
        if (database_create == false) {
            db = openOrCreateDatabase(name, MODE_PRIVATE, null);
            database_create = true;
            println("[" + name + "] 데이터베이스 생성이 완료되었습니다.");
        }
    }
    public void println(String msg){
        status.append("\n" + msg);
    }
}
