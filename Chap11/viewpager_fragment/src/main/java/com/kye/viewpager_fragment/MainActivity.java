package com.kye.viewpager_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.pager);
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myAdapter);

    }
}
