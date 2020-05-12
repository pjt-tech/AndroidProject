package com.kye.mygooglemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeoCodingActivity extends AppCompatActivity {

    Geocoder gc;
    EditText editText1, editText2, editText3;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_coding);
        //한국어를 지원하는 Geocoder
        gc = new Geocoder(this, Locale.KOREAN);

        editText1 = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);

        Button button1 = (Button)findViewById(R.id.button2);
        Button button2 = (Button)findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText1.getText().toString();
                searchLocation(str);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latStr = editText2.getText().toString();
                String lngStr = editText3.getText().toString();
                latitude = Double.parseDouble(latStr);
                longitude = Double.parseDouble(lngStr);
                searchLocation(latitude, longitude);
            }
        });
    }

    public void searchLocation(String str){
        List<Address> addressList = null;
        try {
            //최대 하나만 검색하여 가져옴
            addressList = gc.getFromLocationName(str, 1);
            if(addressList!=null){
                for (int i=0; i<addressList.size(); i++ ){
                    Address addr = addressList.get(i);
                    latitude = addr.getLatitude();
                    longitude = addr.getLongitude();

                }

                Intent intent = getIntent();
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                setResult(RESULT_OK, intent);
                finish();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchLocation(double latitude, double longitude){
        Intent intent = getIntent();
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        setResult(RESULT_OK, intent);
        finish();
    }
}
