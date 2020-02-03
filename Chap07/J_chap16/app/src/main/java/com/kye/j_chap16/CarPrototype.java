package com.kye.j_chap16;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public abstract class CarPrototype implements Car {

    public static ArrayList<Car> cars = new ArrayList<Car>();

    Context context;
    int price;

    public CarPrototype(Context context) {
        this.context = context;
    }

    @Override
    public void doStart() {

        Toast.makeText(context,"CarPrototype의 doStart 메소드가 호출되었습니다.",Toast.LENGTH_LONG).show();

    }

    @Override
    public void doTurn() {

        Toast.makeText(context,"CarPrototype의 doTurn 메소드가 호출되었습니다.",Toast.LENGTH_LONG).show();
    }

    @Override
    public void doStop() {

        Toast.makeText(context,"CarPrototype의 doStop 메소드가 호출되었습니다.",Toast.LENGTH_LONG).show();
    }

    @Override
    public abstract void doRun();

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
