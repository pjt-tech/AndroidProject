package com.kye.mission_pjt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button cusbtn = findViewById(R.id.cus_btn);
        Button revbtn = findViewById(R.id.rev_btn);
        Button probtn = findViewById(R.id.pro_btn);

        intent = getIntent();
        String name = intent.getStringExtra("name");
        Toast.makeText(getApplicationContext(), "사용자 이름 : " + name + " 님이 로그인 하셨습니다.", Toast.LENGTH_LONG).show();

        cusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), CustomerActivity.class);
                startActivityForResult(intent, 10);

            }
        });
        probtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), ProductActivity.class);
                startActivityForResult(intent, 20);

            }
        });

        revbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), RevenueActivity.class);
                startActivityForResult(intent, 30);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode ==RESULT_OK) {
            String cus = data.getStringExtra("cus");
            String name = data.getStringExtra("name");
            Toast.makeText(getApplicationContext(), name + "관리 응답 : " + cus, Toast.LENGTH_LONG).show();
        } else if (resultCode == RESULT_CANCELED)
            finish();

        if (requestCode == 20 && resultCode ==RESULT_OK) {
            String pro = data.getStringExtra("pro");
            String name = data.getStringExtra("name1");
            Toast.makeText(getApplicationContext(), name + "관리 응답 : " + pro, Toast.LENGTH_LONG).show();
        } else if (resultCode == RESULT_CANCELED)
            finish();

        if (requestCode == 30 && resultCode == RESULT_OK) {
            String rev = data.getStringExtra("rev");
            String name = data.getStringExtra("name2");
            Toast.makeText(getApplicationContext(), name + "관리 응답 : " + rev, Toast.LENGTH_LONG).show();
        }else if(resultCode == RESULT_CANCELED)
                finish();
        }
    }

