package com.example.SpringCrud.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    private @Id @GeneratedValue Long userId;
    private String userName;
    private int age;

    public User() {

    }

    public User(String userName, int age){
        this.userName = userName;
        this.age = age;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
