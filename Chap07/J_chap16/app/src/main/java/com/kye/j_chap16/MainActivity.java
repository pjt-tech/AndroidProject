package com.kye.j_chap16;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        layout = findViewById(R.id.layout);

    }

    public void onBenz(View v){

        int price = Integer.parseInt(editText.getText().toString());
        Benz benz = new Benz(this);
        benz.setPrice(price);
        CarPrototype.cars.add(benz);
        Toast.makeText(getApplicationContext(),"Benz 구매함",Toast.LENGTH_LONG).show();
        addToLayout();
    }

    public void onBMW(View v){

        int price = Integer.parseInt(editText.getText().toString());
        BMW bmw = new BMW(this);
        bmw.setPrice(price);
        CarPrototype.cars.add(bmw);
        Toast.makeText(getApplicationContext(),"bmw 구매함",Toast.LENGTH_LONG).show();
        addToLayout();
    }

    public void addToLayout(){
        Button button = new Button(this);
        button.setTag(CarPrototype.cars.size()-1);
        button.setText("CAR "+CarPrototype.cars.size());
        button.setTextSize(50);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button curButton = (Button)v;
                    int index = ((Integer)curButton.getTag()).intValue();
                    Car curCar = CarPrototype.cars.get(index);
                    int price = curCar.getPrice();
                    if(curCar instanceof Benz){
                    Toast.makeText(getApplicationContext(),"지금 선택하신 차종은 Benz 차종이고 , 가격은 " +price+"만원입니다.",Toast.LENGTH_LONG).show();
                }else if(curCar instanceof BMW){
                    Toast.makeText(getApplicationContext(),"지금 선택하신 차종은 BMW 차종이고 , 가격은" +price+"만원입니다.",Toast.LENGTH_LONG).show();
                }



            }
        });

        layout.addView(button);


    }
}
