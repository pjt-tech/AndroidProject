package com.kye.mission_pjt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Button mainbtn1 = findViewById(R.id.main_btn1);
        Button login2 = findViewById(R.id.login_btn2);
        Button rebtn = findViewById(R.id.re_btn2);

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


        //태스크의 플래그 속성중 singletop을 설정하면 top을 재사용한다는 의미이다 속성은 manifests와 add플래그를 통하여 사용할 수 있다.
        rebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProductActivity.class);
                intent.putExtra("name","aa");
                startActivity(intent);
            }
        });
    }

    //재사용을 하기때문에 새로운 값을 줄 수 없는데 onNewIntent 오버라이딩을 통하여 새로운 값을 전달할 수 있다.
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("LogCat","onNewIntent 호출");
        String name = intent.getStringExtra("name");
        Toast.makeText(getApplicationContext(),"보낸정보 : " + name , Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LogCat","onStart 호출");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LogCat","onStop 호출");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LogCat","onDestroy 호출");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LogCat","onPause 호출");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LogCat","onResume 호출");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LogCat","onRestart 호출");
    }
}
