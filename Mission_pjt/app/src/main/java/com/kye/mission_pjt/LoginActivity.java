package com.kye.mission_pjt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editText,editText1;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText2);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals("") || editText1.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "아이디와 패스워드를 입력하세요.", Toast.LENGTH_LONG).show();
                }else if(editText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "아이디를 입력하세요.", Toast.LENGTH_LONG).show();
                }else if(editText1.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "패스워드를 입력하세요.", Toast.LENGTH_LONG).show();
                } else {
                    intent = new Intent(getApplicationContext(), MenuActivity.class);
                    intent.putExtra("name",editText.getText().toString());
                    startActivityForResult(intent,10);
                }
            }
        });

    }

}
