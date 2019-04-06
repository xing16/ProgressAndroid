package com.xing.progressandroid.bean;

public class GridCategoryBean {

    private String title;
    private int resId;

    public GridCategoryBean() {
    }

    public GridCategoryBean(String title, int resId) {
        this.title = title;
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @Override
    public String toString() {
        return "GridCategoryBean{" +
                "title='" + title + '\'' +
                '}';
    }
}
