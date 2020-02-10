package com.kye.self10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class ResultActivity2 extends AppCompatActivity {

    ViewFlipper viewFlipper;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);

        Button button = findViewById(R.id.button3);
        Button button1 = findViewById(R.id.button4);
        viewFlipper = findViewById(R.id.viewFlipper);
        imageView = findViewById(R.id.imageView);


       int[] imagefiled = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
                R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9};

        Intent intent = getIntent();
        int voteResult[] = intent.getIntArrayExtra("VoteCount");

        int max=0;
        for(int i=1; i<voteResult.length; i++) {
            if (voteResult[max] < voteResult[i]) {
                max = i;
            }
        }
        for(int i = 0; i<imagefiled.length; i++) {
            imageView.setImageResource(imagefiled[i]);
        }

        viewFlipper.setFlipInterval(1000);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.startFlipping();

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.stopFlipping();
            }
        });
    }
}
