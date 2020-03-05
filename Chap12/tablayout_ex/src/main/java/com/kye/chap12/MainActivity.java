package com.kye.chap12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    boolean isfabOpen;
    FloatingActionButton fab,fabsub1,fabsub2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.pager);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==1){
                    DialogFragment dialogFragment = new DialogFragment();
                    dialogFragment.show(getSupportFragmentManager(),"");
                }

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fab = findViewById(R.id.fab);
        fabsub1 = findViewById(R.id.fabSub1);
        fabsub2 = findViewById(R.id.fabSub2);
        fab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                toggleFab();
                /*
                Snackbar.make(v,"나는 스낵바입니다.",Snackbar.LENGTH_LONG).setAction("MoreAction", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"스낵바의 액션입니다.",Toast.LENGTH_LONG).show();
                    }
                }).show();

                 */
            }

        });
        fabsub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"알람 Fab입니다.",Toast.LENGTH_LONG).show();
            }
        });

        fabsub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"쇼핑 Fab입니다.",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void toggleFab(){
        if(isfabOpen) {
            ObjectAnimator.ofFloat(fabsub1,"translationY",0f).start();
            ObjectAnimator.ofFloat(fabsub2,"translationY",0f).start();
            fab.setImageResource(R.drawable.ic_add);
        }else{
            ObjectAnimator.ofFloat(fabsub1,"translationY",-200f).start();
            ObjectAnimator.ofFloat(fabsub2,"translationY",-400f).start();
            fab.setImageResource(R.drawable.ic_sub);
        }
        isfabOpen=!isfabOpen;
    }


}
