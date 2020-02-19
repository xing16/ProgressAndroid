package com.xing.progressandroid.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class User {

    @Id
    private String userId;
    @Unique
    private String name;
    private int age;
    private boolean isDeveloper;
    private float salary;
    private String address;


    public User(String name, int age, boolean isDeveloper, float salary) {
        this.name = name;
        this.age = age;
        this.isDeveloper = isDeveloper;
        this.salary = salary;
    }


    @Generated(hash = 263285165)
    public User(String userId, String name, int age, boolean isDeveloper,
                float salary, String address) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.isDeveloper = isDeveloper;
        this.salary = salary;
        this.address = address;
    }


    @Generated(hash = 586692638)
    public User() {
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getIsDeveloper() {
        return this.isDeveloper;
    }

    public void setIsDeveloper(boolean isDeveloper) {
        this.isDeveloper = isDeveloper;
    }

    public float getSalary() {
        return this.salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }


    @Override
    public String toString() {
        return "userId=" + userId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isDeveloper=" + isDeveloper +
                ", salary=" + salary +
                ", address = " + address;
    }


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getAddress() {
        return this.address;
    }


    public void setAddress(String address) {
        this.address = address;
    }
}
