package com.kye.self10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class Result2Activity extends AppCompatActivity {

    ViewFlipper vFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);

        Button btnStart = findViewById(R.id.btnStart);
        Button btnStop = findViewById(R.id.btnStop);

        vFlipper = findViewById(R.id.viewFlipper1);

        Intent intent = getIntent();
        int[] voteResult = intent.getIntArrayExtra("VoteCount");
        String[] imageName = intent.getStringArrayExtra("ImageName");

        Integer imageFileId[] = { R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
                R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9 };

        int tmpResult;
        String tmpName;
        int tmpId;
        for (int i = 0; i < voteResult.length - 1; i++)
            for (int j = i; j < voteResult.length - 1; j++) {
                if (voteResult[j] < voteResult[j + 1]) {
                    tmpResult = voteResult[j];
                    tmpName = imageName[j];
                    tmpId = imageFileId[j];
                    voteResult[j] = voteResult[j + 1];
                    imageName[j] = imageName[j + 1];
                    imageFileId[j] = imageFileId[j + 1];
                    voteResult[j + 1] = tmpResult;
                    imageName[j + 1] = tmpName;
                    imageFileId[j + 1] = tmpId;
                }
            }

        int ivID[] = { R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5, R.id.iv6, R.id.iv7, R.id.iv8, R.id.iv9 };

        for (int i = 0; i < voteResult.length; i++) {
            ImageView iv = findViewById(ivID[i]);
            iv.setImageResource(imageFileId[i]);
        }

        vFlipper.setFlipInterval(1000);

        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vFlipper.startFlipping();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vFlipper.stopFlipping();
            }
        });

    }

}
