package com.kye.navigationdrawer_ex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.main_drawer);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close){};
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.main_drawerView);
        View header_View = navigationView.getHeaderView(0);
        ImageView imageView = header_View.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"이미지 클릭",Toast.LENGTH_LONG).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int id = item.getItemId();
                        if(id==R.id.nav_camera){
                            Toast.makeText(getApplicationContext(),"카메라 클릭",Toast.LENGTH_LONG).show();
                        }else if(id==R.id.nav_gallery){
                            Toast.makeText(getApplicationContext(),"갤러리 클릭",Toast.LENGTH_LONG).show();
                        }else if(id==R.id.nav_slideshow) {
                            Toast.makeText(getApplicationContext(), "슬라이드쇼 클릭", Toast.LENGTH_LONG).show();
                        }else if(id==R.id.nav_manage) {
                            Toast.makeText(getApplicationContext(), "Tools 클릭", Toast.LENGTH_LONG).show();
                        }else if(id==R.id.nav_share) {
                            Toast.makeText(getApplicationContext(), "공유 클릭", Toast.LENGTH_LONG).show();
                        }else if(id==R.id.nav_send) {
                            Toast.makeText(getApplicationContext(), "send 클릭", Toast.LENGTH_LONG).show();
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}
