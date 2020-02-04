package com.kye.j_chap15;

import android.widget.Toast;

public class Person {

    String name;
    MainActivity activity;

    public Person(String name,MainActivity activity){
        this.name = name;
        this.activity = activity;
    }

    public void walk(int speed){

        Toast.makeText(activity.getApplicationContext(),name+"이(가)"+speed+"km 속도로 걸어갑니다.",Toast.LENGTH_LONG).show();
        activity.imageView.setImageResource(R.drawable.person_walk);

    }
    public void run(int speed){

        Toast.makeText(activity.getApplicationContext(),name+"이(가)"+speed+"km 속도로 뛰어갑니다.",Toast.LENGTH_LONG).show();
        activity.imageView.setImageResource(R.drawable.person_run);

    }

    public void cry(){
        Toast.makeText(activity.getApplicationContext(),"우는 방법을 모릅니다.",Toast.LENGTH_LONG).show();
        activity.imageView.setImageResource(0);
    }

}
