package com.kye.pagesliding_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    boolean isPageOpen = false;
    LinearLayout layout;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        layout = findViewById(R.id.layout);
        final Animation translate_Left = AnimationUtils.loadAnimation(this,R.anim.translate_left);
        final Animation translate_Right = AnimationUtils.loadAnimation(this,R.anim.translate_right);
        PageListener pageListener = new PageListener();
        translate_Left.setAnimationListener(pageListener);
        translate_Right.setAnimationListener(pageListener);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPageOpen){
                    layout.startAnimation(translate_Right);
                }else{
                    layout.startAnimation(translate_Left);
                    layout.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    class PageListener implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isPageOpen){
                layout.setVisibility(View.INVISIBLE);
                button.setText("OPEN");
                isPageOpen = false;
            }else{
                button.setText("CLOSE");
                isPageOpen = true;
            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
