package com.kye.a_intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText,editText1;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        Button button1 = findViewById(R.id.button2);
        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),Main2Activity.class);
                intent.putExtra("name",editText.getText().toString());
                intent.putExtra("age",Integer.parseInt(editText1.getText().toString()));
                startActivityForResult(intent,100);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                ComponentName componentName = new ComponentName("com.kye.b_intent","com.kye.b_intent.MainActivity");
                intent.setComponent(componentName);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 || resultCode == RESULT_OK){
            String name = data.getStringExtra("activity");
            String result = data.getStringExtra("result");
            Toast.makeText(getApplicationContext(),"보낸액티비티 : "+name+" , " +result,Toast.LENGTH_LONG).show();
        }

    }
}
