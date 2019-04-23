package com.xing.progressandroid.rxjava.bean;

import java.util.List;

public class Plan {
    private String title;
    private long time;
    private List<String> actionList;

    public Plan() {
    }

    public Plan(String title, long time, List<String> actionList) {
        this.title = title;
        this.time = time;
        this.actionList = actionList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<String> getActionList() {
        return actionList;
    }

    public void setActionList(List<String> actionList) {
        this.actionList = actionList;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", actionList=" + actionList +
                '}';
    }
}
