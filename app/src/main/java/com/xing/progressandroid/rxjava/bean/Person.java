package com.xing.progressandroid.rxjava.bean;

import java.util.List;

public class Person {
    private String name;
    private List<Plan> planList;

    public Person() {
    }

    public Person(String name, List<Plan> planList) {
        this.name = name;
        this.planList = planList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", planList=" + planList +
                '}';
    }
}
