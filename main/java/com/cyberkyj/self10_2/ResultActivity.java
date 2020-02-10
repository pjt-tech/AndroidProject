package com.cyberkyj.self10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tvTop = findViewById(R.id.tvTop);
        ImageView ivTop = findViewById(R.id.ivTop);
        Button button = findViewById(R.id.button2);

        int imageFiled[] = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
                        R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9};

        Intent intent = getIntent();
        int voteResult[] = intent.getIntArrayExtra("VoteCount");
        String[] imageName = intent.getStringArrayExtra("ImageName");

        int max=0;
        for(int i=1; i<voteResult.length;i++){
            if(voteResult[max]<voteResult[i]){
                max = i;
            }
        }
        tvTop.setText(imageName[max]);
        ivTop.setImageResource(imageFiled[max]);

        TextView tv[] = new TextView[imageName.length];
        RatingBar[] rBar = new RatingBar[imageName.length];

        int[] tvId = {R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9};
        int[] rBarId = {R.id.rb1, R.id.rb2, R.id.rb3, R.id.rb4, R.id.rb5, R.id.rb6, R.id.rb7, R.id.rb8, R.id.rb9};

        for(int i=0; i<voteResult.length; i++){
            tv[i] = findViewById(tvId[i]);
            rBar[i] = findViewById(rBarId[i]);
        }

        for(int i=0; i<voteResult.length; i++){
            tv[i].setText(imageName[i]);
            rBar[i].setRating((float)voteResult[i]);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
