package com.kye.self10_3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setTitle("Second Activity");

        //넘어온 데이터 정의
        Intent intent = getIntent();
        final int num1 = intent.getIntExtra("num1", 0);
        final int num2 = intent.getIntExtra("num2", 0);
        final Integer rdoGroup = intent.getIntExtra("rdoGroup", 0);

        //돌아가기 버튼에 ClickListener정의
        Button btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //계산 유형
                double result = 0;
                switch( rdoGroup ) {
                    case R.id.add:
                        result = (double)(num1 + num2);
                        break;
                    case R.id.minus:
                        result = (double)(num1 - num2);
                        break;
                    case R.id.multi:
                        result = (double)(num1 * num2);
                        break;
                    case R.id.division:
                        result = (double)num1 / (double)num2;
                        break;
                }

                //intent 선언
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("result", result);
                //setResult()로 mainActivity로 데이터 전달; main의 onActivityResult()가 실행됨
                setResult(RESULT_OK, intent);
                finish();	//현 activity 닫음
            }
        });
    }
}
