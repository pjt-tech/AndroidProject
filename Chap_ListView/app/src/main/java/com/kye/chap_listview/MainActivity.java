package com.kye.chap_listview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] titles = {"처음처럼","시작","On","그떄 그 아인","사랑,하자"};
    String[] singers ={"엠씨더맥스","가호","방탄소년단","김필","수호"};
    ListView listView;
    SongAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        adapter = new SongAdapter(getApplicationContext());


        for(int i=0; i<titles.length; i++){
            SongItem item = new SongItem(titles[i],singers[i]);
            adapter.addItem(item);
        }

        listView.setAdapter(adapter);

        Button btn_add = findViewById(R.id.btn_add);
        Button btn_close = findViewById(R.id.btn_close);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),SongInfoActivity.class);
                startActivityForResult(intent,1000);
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (data != null && requestCode==1000){
            if(resultCode==RESULT_OK){

                String title = data.getStringExtra("title");
                String singer = data.getStringExtra("singer");

                SongItem item = new SongItem(title,singer);
                adapter.addItem(item);
                adapter.notifyDataSetChanged();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

    }


}
