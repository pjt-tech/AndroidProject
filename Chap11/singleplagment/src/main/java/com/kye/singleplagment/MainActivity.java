package com.kye.singleplagment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MainFragment mainFragment;
    MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = (MainFragment)getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        menuFragment = new MenuFragment();

    }

    public void onFragmentChanged(int index){
        if (index == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();
        }else if(index == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,menuFragment).commit();
        }
    }

    public void onSelect(View v){
        Fragment fr = null;
        switch(v.getId()){
            case R.id.button :
                fr = mainFragment;
                break;
            case R.id.button2 :
                fr = menuFragment;
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fr).commit();
    }
}
