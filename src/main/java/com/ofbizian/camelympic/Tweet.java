package com.ofbizian.camelympic;

public class Tweet {
    private String name;
    private String text;
    private String url;
    private int width;
    private int height;
    private long tweetCount;
    private long imageCount;
    private long startDate;

    public Tweet withName(String name) {
        this.name = name;
        return this;
    }

    public Tweet withText(String text) {
        this.text = text;
        return this;
    }

    public Tweet withImageUrl(String url) {
        this.url = url;
        return this;
    }

    public Tweet withWidth(int width) {
        this.width = width;
        return this;
    }

    public Tweet withHeight(int height) {
        this.height = height;
        return this;
    }

    public Tweet withCount(long tweetCount) {
        this.tweetCount = tweetCount;
        return this;
    }

    public Tweet withImageCount(long imageCount) {
        this.imageCount = imageCount;
        return this;
    }

    public Tweet withStartDate(long startDate) {
        this.startDate = startDate;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public long getImageCount() {
        return imageCount;
    }

    public String getName() {
        return name;
    }

    public long getStartDate() {
        return startDate;
    }

    public String getText() {
        return text;
    }

    public long getTweetCount() {
        return tweetCount;
    }

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }
}
