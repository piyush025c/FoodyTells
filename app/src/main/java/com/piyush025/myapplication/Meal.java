package com.piyush025.myapplication;

public class Meal {

    private int id;
    private String title;
    private String description;
    private  String price;
    private int image;

    public Meal(int id, String title, String description, String price, int image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
}
