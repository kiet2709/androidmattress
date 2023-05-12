package com.nhom4nguoi.ecommerce.model;

public class Cloth {
    private int resourceId;
    private String title;
    private int price;

    public Cloth(int resourceId, String title, int price) {
        this.resourceId = resourceId;
        this.title = title;
        this.price = price;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
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
