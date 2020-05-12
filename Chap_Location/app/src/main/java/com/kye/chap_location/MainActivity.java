package com.kye.chap_location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},0);
        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocationService();
            }
        });

    }
    public void startLocationService() {

        long time = 10000;
        float distance = 0;
        //위치관리자
        LocationManager manager = (LocationManager)getSystemService(LOCATION_SERVICE);

        try {
            //마지막 위치 값
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (manager != null) {
                //위도와 경도 표시
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                textView.setText("최근 내 위치 : " + latitude + ", " + longitude);
             }

            //실시간 위치반영
            GPSListener gpsListener = new GPSListener();
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,time,distance,gpsListener);
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,time,distance,gpsListener);

            }catch (SecurityException e){

        }

    }

    class GPSListener implements LocationListener{

        @Override
        //위치가 바뀌엇을때 콜백
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            textView.setText("내 위치 : " + latitude + ", " + longitude);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
