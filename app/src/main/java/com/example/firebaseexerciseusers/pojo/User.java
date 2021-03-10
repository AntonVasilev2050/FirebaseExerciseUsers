package com.example.firebaseexerciseusers.pojo;

public class User {
    private String name;
    private String lastName;
    private int age;
    private String sex;

    public User() {
    }

    public User(String name, String lastName, int age, String sex) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }
}
