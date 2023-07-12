package com.Hanium.Farm.Farm.Domain;

public class Review {
    private String fruit_name;
    private String review_time;
    private String user_id;
    private String content;
    private String flavor;
    private int good;
    private String[] images;

    public Review(String fruit_name, String review_time, String user_id, String content, String flavor, int good, String[] images) {
        this.fruit_name = fruit_name;
        this.review_time = review_time;
        this.user_id = user_id;
        this.content = content;
        this.flavor = flavor;
        this.good = good;
        this.images = images;
    }
}
