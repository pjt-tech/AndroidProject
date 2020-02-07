package com.kye.implicit_intent_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
    }

    public void onOk(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        //intent.putExtra("sms_body","2교시 쉬는 시간입니다.");
        intent.putExtra(Intent.EXTRA_TEXT,editText.getText().toString());
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }
}
