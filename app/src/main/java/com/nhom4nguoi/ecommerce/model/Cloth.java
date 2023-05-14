package com.nhom4nguoi.ecommerce.model;

import java.io.Serializable;

public class Cloth implements Serializable {
    private int id;
    private String title;
    private int price;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String path;

    public Cloth(int id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Cloth(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int resourceId) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return String.valueOf(price);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Cloth() {
    }
}
