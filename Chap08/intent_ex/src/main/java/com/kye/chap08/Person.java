package com.kye.chap08;

import java.io.Serializable;

//Serializable 은 빈껍데기
//직렬화를 위한 인터페이스

public class Person implements Serializable {

    //원본정보를 변경하였으면 version과 내용을 알려 맞춰줘야함.

    private static final long serialVersionUID = 100001L;

    String name;
    int age;
    String phone;

    public Person(String name, int age) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
