package com.kye.myscretmemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMemo,btnPassword,btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMemo = findViewById(R.id.button);
        btnPassword = findViewById(R.id.button2);
        btnFinish = findViewById(R.id.button3);

        btnMemo.setOnClickListener(this);
        btnPassword.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

        String passwordUseYn = loadPasswordUseYN(this);
        if(passwordUseYn!=null && passwordUseYn.equals("Y")){
            Intent intent = new Intent(getApplicationContext(),PasswordSettingActivity.class);
            intent.putExtra("mode","unlock");
            startActivityForResult(intent,1002);
        }

    }

    public void onClick(View v){

        if(v==btnMemo){

        }else if(v==btnPassword){
            Intent intent = new Intent(getApplicationContext(),PasswordSettingActivity.class);
            intent.putExtra("mode","lock");
            startActivityForResult(intent,1002);

        }else if(v==btnFinish){
            finish();
        }
    }

    private String loadPasswordUseYN(Context context){
        SharedPreferences preferences = context.getSharedPreferences("environ",0);

        return preferences.getString("passwordUseYN","N");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null && requestCode==1002){
            String mode = data.getStringExtra("mode");
            if(mode!=null && mode.equals("exit")){
                finish();
            }
        }
    }
}
