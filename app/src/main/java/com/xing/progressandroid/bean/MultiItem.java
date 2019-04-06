package com.xing.progressandroid.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MultiItem implements MultiItemEntity {

    public static final int TEXT = 1;
    public static final int IMG_TEXT = 2;
    public static final int TEXT_IMG = 3;
    private String text;
    private int resId;

    private int itemType;

    public MultiItem(String text, int resId, int itemType) {
        this.text = text;
        this.resId = resId;
        this.itemType = itemType;
    }

    public MultiItem(String text, int itemType) {
        this.text = text;
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
