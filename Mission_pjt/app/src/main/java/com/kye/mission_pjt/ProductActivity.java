package com.kye.mission_pjt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Button mainbtn1 = findViewById(R.id.main_btn1);
        Button login2 = findViewById(R.id.login_btn2);

        mainbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("pro","Product result message is OK!");
                intent.putExtra("name1","상품");
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });
    }
}
