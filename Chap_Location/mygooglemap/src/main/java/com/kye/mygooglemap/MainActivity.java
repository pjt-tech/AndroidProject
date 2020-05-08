package com.kye.mygooglemap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    MapFragment mapFragment;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
        }, 0);

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            }
        });
        MapsInitializer.initialize(this);
        startLocation();

    }

    private void startLocation() {
        long time = 10000;
        float distane = 0;
        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        try {
            GPSListener gpsListener = new GPSListener();
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, time, distane, gpsListener);
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, time, distane, gpsListener);
        }catch(SecurityException e){

        }
    }

    class GPSListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            showCurrentLocation(latitude,longitude);
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

    public void showCurrentLocation(double latitude, double longitude){
        LatLng curPoint = new LatLng(latitude,longitude);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint,15));
        map.setMyLocationEnabled(true);
        showAllItems(35.833493, 127.137918,R.drawable.school,"백제","직업전문학교");
    }

    private void showAllItems(double latitude,double longitude,int id,String title,String snippet){
        MarkerOptions marker = new MarkerOptions();
        marker.position(new LatLng(latitude,longitude));
        marker.title(title);
        marker.snippet(snippet);
        marker.draggable(true);
        marker.icon(BitmapDescriptorFactory.fromResource(id));
        map.addMarker(marker);
    }
}