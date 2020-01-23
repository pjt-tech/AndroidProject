package com.kye.j_chap15;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    ImageView imageView;
    RadioButton radioButton;

    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText = findViewById(R.id.editText);
        imageView = findViewById(R.id.imageView);

        Button button1 = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        radioButton = findViewById(R.id.radioButton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean personChecked = radioButton.isChecked();
                if(personChecked){
                    createPerson(editText.getText().toString());
                }else{
                    createBaby(editText.getText().toString());
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person!=null){
                    person.walk(10);
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person!=null){
                    person.run(20);
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person!=null){
                    person.cry();
                }
            }
        });

    }

    public void createPerson(String name){
        person = new Person(name,MainActivity.this);
        imageView.setImageResource(R.drawable.person);
    }
    public void createBaby(String name){
        person = new Baby(name,MainActivity.this);
        imageView.setImageResource(R.drawable.baby);
    }

}
