package com.kye.chap08;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

//Serializable 은 빈껍데기
//직렬화를 위한 인터페이스

public class Person2 implements Parcelable {

    private static final long serialVersionUID = 100001L;

    String name;
    int age;


    public Person2(String name, int age) {
        this.name = name;
        this.age = age;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }



}
