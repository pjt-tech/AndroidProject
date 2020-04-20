package com.kye.file_listview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] titles = {"처음처럼","시작","On","그때 그 아인","사랑,하자"};
    String[] singers ={"엠씨더맥스","가호","방탄소년단","김필","수호"};
    ListView listView;
    SongAdapter adapter;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},0);

        listView = findViewById(R.id.listView);
        Button btn_add = findViewById(R.id.button2);
        Button btn_close = findViewById(R.id.button);
        Button btn_fileWrite = findViewById(R.id.button5);
        Button btn_fileRead = findViewById(R.id.button6);

        adapter = new SongAdapter(getApplicationContext());


        for(int i=0; i<titles.length; i++){
            SongItem item = new SongItem(titles[i],singers[i],R.drawable.song);
            adapter.addItem(item);
        }
        listView.setAdapter(adapter);



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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SongItem item = (SongItem)adapter.getItem(position);
                Toast.makeText(getApplicationContext(),"선택한 아이템 : " + item.getTitle() + item.getSinger(),Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.removeItem(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        //안드로이드는 반드시 소켓을 사용할때 스레드사용

        btn_fileWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WriteTread tread = new WriteTread();
                tread.start();

            }
        });

        btn_fileRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ReadTread tread = new ReadTread();
                tread.start();

            }
        });

        /* 파일쓰기와 읽기의 이벤트
        btn_fileWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = getFile();
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                    ArrayList<SongItem> items = adapter.items;
                    objectOutputStream.writeObject(items.size());
                    for(int i = 0; i < items.size(); i++){
                        SongItem item = items.get(i);
                        objectOutputStream.writeObject(item);
                    }
                    Toast.makeText(getApplicationContext(),"파일 쓰기가 완료되었습니다.",Toast.LENGTH_LONG).show();
                    objectOutputStream.flush();
                    objectOutputStream.close();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"파일 쓰기가 실패하였습니다..",Toast.LENGTH_LONG).show();
                }


            }
        });

        btn_fileRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = getFile();
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));

                    int count = (Integer) objectInputStream.readObject();
                    adapter.clear();
                    for(int i = 0; i<count; i++){
                        SongItem item = (SongItem)objectInputStream.readObject();
                        adapter.addItem(item);
                    }
                    objectInputStream.close();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(),"파일 읽기가 완료되었습니다.",Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"파일 읽기가 실패하였습니다.",Toast.LENGTH_LONG).show();
                }
            }
        });

         */


    }

    public File getFile(){
        File file =new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator
        +"list.txt");

        return file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (data != null && requestCode==1000){
            if(resultCode==RESULT_OK){

                String title = data.getStringExtra("title");
                String singer = data.getStringExtra("singer");
                int imageResource = data.getIntExtra("imageResource",0);

                SongItem item = new SongItem(title,singer,imageResource);
                adapter.addItem(item);
                adapter.notifyDataSetChanged();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    class WriteTread extends Thread{
        @Override
        public void run() {
            try {
                Socket socket = new Socket("210.96.43.5",11000);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject("WRITE");
                ArrayList<SongItem> items = adapter.items;
                outputStream.writeObject(new Integer(items.size()));
                for(int i=0; i< items.size(); i++){
                    SongItem item = items.get(i);
                    outputStream.writeObject(item);
                    outputStream.flush();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"서버로 리스트 데이터 쓰기 완료",Toast.LENGTH_LONG).show();
                    }
                });

                outputStream.close();
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class ReadTread extends Thread{
        @Override
        public void run() {
            try {
                Socket socket = new Socket("210.96.43.5",11000);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject("READ");
                outputStream.flush();

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                int count = (int)inputStream.readObject();

                //Thread에서는 adapter에 직접 접근불가 핸들러 이용
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                    }
                });

                for(int i=0; i<count; i++){
                    final SongItem item = (SongItem)inputStream.readObject();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.addItem(item);

                        }
                    });
                }



                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"서버에서 리스트 데이터 읽기 완료",Toast.LENGTH_LONG).show();
                        adapter.notifyDataSetChanged();
                    }
                });

                inputStream.close();
                socket.close();
                outputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
