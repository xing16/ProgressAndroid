package com.xing.progressandroid.layoutmanager.card;

public class CardLayoutManagerSettings {

    /**
     * 最大可见的数量
     */
    private int maxVisibleCount;
    private int translationY;
    private float scale;

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getMaxVisibleCount() {
        return maxVisibleCount;
    }

    public void setMaxVisibleCount(int maxVisibleCount) {
        this.maxVisibleCount = maxVisibleCount;
    }

    public int getTranslationY() {
        return translationY;
    }

    public void setTranslationY(int translationY) {
        this.translationY = translationY;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public static class Builder {
        int maxVisibleCount = 4;
        int translationY;
        float scale;

        public Builder setMaxVisibleCount(int maxVisibleCount) {
            this.maxVisibleCount = maxVisibleCount;
            return this;
        }

        public Builder setTranslationY(int translationY) {
            this.translationY = translationY;
            return this;
        }

        public Builder setScale(float scale) {
            this.scale = scale;
            return this;
        }

        public CardLayoutManagerSettings build() {
            CardLayoutManagerSettings settings = new CardLayoutManagerSettings();
            settings.maxVisibleCount = maxVisibleCount;
            settings.translationY = translationY;
            settings.scale = scale;
            return settings;
        }
    }
}
