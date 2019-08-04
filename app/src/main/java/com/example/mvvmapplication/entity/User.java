package com.example.mvvmapplication.entity;

public class User {

    public User(String name, int age, String emailAddress) {
        this.name = name;
        this.age = age;
        this.emailAddress = emailAddress;
    }

    //省略其它
    public String name;
    public int age;
    public String emailAddress;
}