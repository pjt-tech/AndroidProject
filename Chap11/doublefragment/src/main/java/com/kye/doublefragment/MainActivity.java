package com.kye.doublefragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    imageFragment imageFragment;
    int[] id = {R.drawable.jeju1,R.drawable.jeju02,R.drawable.jeju3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageFragment = (imageFragment)getSupportFragmentManager().findFragmentById(R.id.image_Fragment);

    }

    public void onImageSelected(int postion){
        imageFragment.setImage(id[postion]);

    }
}
