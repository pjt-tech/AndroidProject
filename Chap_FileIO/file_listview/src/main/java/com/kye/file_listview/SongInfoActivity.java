package com.kye.file_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SongInfoActivity extends AppCompatActivity {

    EditText edt_title,edt_singer;
    int[] images = {R.drawable.song,R.drawable.song2,R.drawable.song3};
    ImageView imageView;
    Animation animation;
    int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_info);

        edt_title = findViewById(R.id.editText);
        edt_singer = findViewById(R.id.editText2);

        Button btn_save = findViewById(R.id.button3);
        Button btn_close = findViewById(R.id.button4);
        imageView = findViewById(R.id.imageView2);

        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeout);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageIndex++;
                if(imageIndex>2){
                    imageIndex=0;
                }
                imageView.setImageResource(images[imageIndex]);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(animation);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edt_title.getText().toString();
                String singer = edt_singer.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("title",title);
                intent.putExtra("singer",singer);
                intent.putExtra("imageResource",images[imageIndex]);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
