package com.kye.firebase_ex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Date;
import java.util.Iterator;

public class RegisterActivity extends AppCompatActivity {

    EditText edtId;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtId = findViewById(R.id.editText2);
        reference = FirebaseDatabase.getInstance().getReference();
        Button register = findViewById(R.id.button2);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> child = dataSnapshot.getChildren().iterator();
                        while(child.hasNext()){
                            if(edtId.getText().toString().equals(child.next().getKey())){
                                Toast.makeText(getApplicationContext(),"존재하는 아이디입니다.",Toast.LENGTH_LONG).show();
                                reference.removeEventListener(this);
                                return;
                            }
                        }

                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        Date date = new Date(System.currentTimeMillis());
                        String data_time = format.format(date);
                        reference.child(edtId.getText().toString()).setValue(data_time);
                        Toast.makeText(getApplicationContext(),"회원가입이 완료되었습니다.",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
