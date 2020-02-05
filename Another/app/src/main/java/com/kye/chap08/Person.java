package com.kye.chap08;

import java.io.Serializable;

public class Person implements Serializable {

    // 복사한쪽 Serializable
    private static final long serialVersionUID = 100001L;

    String name;
    int age;
    String phone;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
