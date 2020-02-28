package com.kye.dialog_ex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edt_Name,edt_Email,dig_Name,dig_Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_Name = findViewById(R.id.editText);
        edt_Email = findViewById(R.id.editText2);

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View layout = View.inflate(MainActivity.this,R.layout.dialog,null);
                builder.setView(layout);
                builder.setTitle("사용자 정보 입력");
                builder.setIcon(R.drawable.ic_menu_allfriends);
                dig_Name = layout.findViewById(R.id.editText3);
                dig_Email = layout.findViewById(R.id.editText4);
                dig_Name.setText(edt_Name.getText().toString());
                dig_Email.setText(edt_Email.getText().toString());
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edt_Name.setText(dig_Name.getText().toString());
                        edt_Email.setText(dig_Email.getText().toString());

                    }
                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"취소했습니다.",Toast.LENGTH_LONG).show();
                    }
                });

                builder.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] singer = {"AOA","TWICE","APINK","여자친구"};
                final ArrayList<String> item = new ArrayList<>();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("좋아하는 걸그룹은 ? ");
                builder.setMultiChoiceItems(singer, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                     String value = singer[which];
                     if(isChecked){
                         item.add(value);
                     }else if(item.contains(value)){
                         item.remove(value);
                     }
                    }
                });

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String msg = "";
                        for(int i = 0; i < item.size(); i++){
                            msg += "\n"+item.get(i);
                        }
                        Toast.makeText(getApplicationContext(),"Selected Singer : " + msg,Toast.LENGTH_LONG).show();
                    }
                });

                builder.show();
            }
        });
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("종료 확인");
        builder.setMessage("현재 프로그램을 종료하시겠습니까?");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getApplicationContext(),"취소했습니다.",Toast.LENGTH_LONG).show();
            }
        });

        builder.show();
    }
}
