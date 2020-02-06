package com.kye.chap08;

import android.os.Parcel;
import android.os.Parcelable;

public class Person2 implements Parcelable {

    String name;
    int age;

    public Person2() {
    }

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


    //송신측
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeInt(age);

    }


    //수신측
    public static final Creator<Person2> CREATOR = new Creator<Person2>() {
        @Override
        public Person2 createFromParcel(Parcel source) {
            Person2 person2 = new Person2();
            person2.setName(source.readString());
            person2.setAge(source.readInt());
            return person2;
        }

        @Override
        public Person2[] newArray(int size) {
            return new Person2[0];
        }
    };
}


