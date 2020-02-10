package com.kye.self10_3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Main Activity");

        Button btnCalc;

        //계산하기 버튼에 ClickListener 정의
        btnCalc = (Button)findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //입력 값
                EditText edtNum1 = (EditText)findViewById(R.id.edtNum1);
                EditText edtNum2 = (EditText)findViewById(R.id.edtNum2);
                //라디오 그룹
                RadioGroup rdoGroup = (RadioGroup)findViewById(R.id.rdoGroup);

                //intent 선언
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                //넘겨줄 데이터 정의
                intent.putExtra("num1", Integer.parseInt(edtNum1.getText().toString()) );
                intent.putExtra("num2", Integer.parseInt(edtNum2.getText().toString()) );
                intent.putExtra("rdoGroup", rdoGroup.getCheckedRadioButtonId() );

                //SecondActivity 화면에 출력
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //리턴되는 값이 존재
        if(resultCode == RESULT_OK) {
            //리턴값
            double result = data.getDoubleExtra("result", 0);
            Toast.makeText(getApplicationContext(), "합계 : " + result, Toast.LENGTH_SHORT).show();
        }
    }
}
