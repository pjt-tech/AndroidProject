package com.kye.mission_pjt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RevenueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue);

        Button mainbtn3 = findViewById(R.id.main_btn3);
        Button login4 = findViewById(R.id.login_btn4);

        mainbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("rev","Revenue result message is OK!");
                intent.putExtra("name2","매출");
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        login4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });
    }
}
