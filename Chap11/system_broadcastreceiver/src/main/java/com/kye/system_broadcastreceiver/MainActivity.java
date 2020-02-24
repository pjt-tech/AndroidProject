package com.kye.system_broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> datas;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        datas = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
        listView.setAdapter(adapter);

        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent battetyStatus = registerReceiver(null,filter);
        int status = battetyStatus.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
        boolean isCarging = status== BatteryManager.BATTERY_STATUS_CHARGING;
        if(isCarging){
            int chargingPlug = battetyStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED,-1);
            boolean usbCharge = chargingPlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargingPlug == BatteryManager.BATTERY_PLUGGED_AC;
            if(usbCharge){
                addListItem("Battery is USB Charging");
            }else if(acCharge){
                addListItem("Battery is AC Charging");
            }
        }else{
            addListItem("Battery State is not Charging");
        }

        int level = battetyStatus.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        int scale = battetyStatus.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
        float pct = (level/(float)scale)*100;
        addListItem("Current Battery : " + pct+"%");


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.PROCESS_OUTGOING_CALLS) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CALL_LOG}, 100);

        }

        registerReceiver(brOn,new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(brOff,new IntentFilter(Intent.ACTION_SCREEN_OFF));
        registerReceiver(batteryReceiver,new IntentFilter(Intent.ACTION_POWER_CONNECTED));
        registerReceiver(batteryReceiver,new IntentFilter(Intent.ACTION_POWER_DISCONNECTED));


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                Toast toast = Toast.makeText(this, "no permission", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(brOn);
        unregisterReceiver(brOff);
        unregisterReceiver(batteryReceiver);
    }

    private void addListItem(String msg){
        datas.add(msg);
        adapter.notifyDataSetChanged();

    }
    BroadcastReceiver brOn = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            addListItem("Screen On");
        }
    };
    BroadcastReceiver brOff = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            addListItem("Screen Off");
        }
    };

    BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(Intent.ACTION_POWER_CONNECTED)){
                addListItem("On Connected");
            }else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)){
                addListItem("on disconnected");
            }
        }
    };
}
