package com.kye.firebase_ex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    EditText checkId;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkId = findViewById(R.id.editText);
        reference = FirebaseDatabase.getInstance().getReference("users");
        Button login = findViewById(R.id.login);
        final Button register = findViewById(R.id.register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Iterator<DataSnapshot> child = dataSnapshot.getChildren().iterator();
                        while(child.hasNext()){
                            if(child.next().getKey().equals(checkId.getText().toString())){
                                Toast.makeText(getApplicationContext(),checkId.getText().toString()+"으로 로그인 되었습니다.",Toast.LENGTH_LONG).show();
                            }
                        }
                        Toast.makeText(getApplicationContext(),"존재하지 않는 아이디입니다.",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });


    }
}
