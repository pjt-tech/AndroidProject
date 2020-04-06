package com.kye.chap_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SongInfoActivity extends AppCompatActivity {

    EditText edt_title,edt_singer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_info);

        edt_title = findViewById(R.id.editText);
        edt_singer = findViewById(R.id.editText2);

        Button btn_save = findViewById(R.id.button3);
        Button btn_close = findViewById(R.id.button4);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edt_title.getText().toString();
                String singer = edt_singer.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("title",title);
                intent.putExtra("singer",singer);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
