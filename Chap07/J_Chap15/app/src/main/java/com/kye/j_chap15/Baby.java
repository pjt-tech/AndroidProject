package com.kye.j_chap15;

import android.widget.Toast;

public class Baby extends Person {


    public Baby(String name, MainActivity activity) {
        super(name, activity);

    }


    @Override
    public void walk(int speed) {
        Toast.makeText(activity.getApplicationContext(),name+"이(가)"+speed+"km 속도로 걸어갑니다.",Toast.LENGTH_LONG).show();
        activity.imageView.setImageResource(R.drawable.baby_walk);
    }

    @Override
    public void run(int speed) {
        Toast.makeText(activity.getApplicationContext(),name+"이(가)"+speed+"km 속도로 뛰어갑니다.",Toast.LENGTH_LONG).show();
        activity.imageView.setImageResource(R.drawable.baby);
    }

    @Override
    public void cry() {
        Toast.makeText(activity.getApplicationContext(),name+"이(가) 웁니다.",Toast.LENGTH_LONG).show();
        activity.imageView.setImageResource(R.drawable.baby_cry);
    }
}

