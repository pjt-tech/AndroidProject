package com.kye.self_check01;

public class Singer {

    String name;
    int age;
    MainActivity activity;
    int Total;

    public Singer(String name, int age, MainActivity activity) {
        this.name = name;
        this.age = age;
        this.activity = activity;
        Total++;

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

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }
}
