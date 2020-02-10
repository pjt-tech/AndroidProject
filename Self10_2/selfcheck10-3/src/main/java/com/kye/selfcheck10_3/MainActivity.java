package com.kye.selfcheck10_3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioButton radioButton,radioButton1,radioButton2,radioButton3;
    EditText editText,editText1;
    int num1,num2;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText2);
        Button button = findViewById(R.id.button);
        radioButton = findViewById(R.id.radioButton);
        radioButton1 = findViewById(R.id.radioButton2);
        radioButton2 = findViewById(R.id.radioButton3);
        radioButton3 = findViewById(R.id.radioButton4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 =Integer.parseInt(editText.getText().toString());
                num2 = Integer.parseInt(editText1.getText().toString());
                if(radioButton.isChecked()){
                    intent = new Intent(getApplicationContext(),SecondActivity.class);
                    intent.putExtra("num1",num1);
                    intent.putExtra("num2",num2);
                    startActivityForResult(intent,10);
                }else if (radioButton1.isChecked()){
                    intent = new Intent(getApplicationContext(),SecondActivity.class);
                    intent.putExtra("num1",num1);
                    intent.putExtra("num2",num2);
                    startActivityForResult(intent,20);
                }else if (radioButton2.isChecked()){
                    intent = new Intent(getApplicationContext(),SecondActivity.class);
                    intent.putExtra("num1",num1);
                    intent.putExtra("num2",num2);
                    startActivityForResult(intent,30);
                }else if (radioButton3.isChecked()){
                    intent = new Intent(getApplicationContext(),SecondActivity.class);
                    intent.putExtra("num1",num1);
                    intent.putExtra("num2",num2);
                    startActivityForResult(intent,40);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10&&resultCode==RESULT_OK){
            intent = getIntent();
            int result = intent.getIntExtra("add",0);
            Toast.makeText(getApplicationContext(),"결과 :" + result ,Toast.LENGTH_LONG).show();
        } if(requestCode==20&&resultCode==RESULT_OK){
            intent = getIntent();
            int result = intent.getIntExtra("sub",0);
            Toast.makeText(getApplicationContext(),"결과 :" + result ,Toast.LENGTH_LONG).show();
        } if(requestCode==30&&resultCode==RESULT_OK){
            intent = getIntent();
            int result = intent.getIntExtra("mul",0);
            Toast.makeText(getApplicationContext(),"결과 :" + result ,Toast.LENGTH_LONG).show();
        } if(requestCode==40&&resultCode==RESULT_OK){
            intent = getIntent();
            int result = intent.getIntExtra("div",0);
            Toast.makeText(getApplicationContext(),"결과 :" + result ,Toast.LENGTH_LONG).show();
        }
    }
}
