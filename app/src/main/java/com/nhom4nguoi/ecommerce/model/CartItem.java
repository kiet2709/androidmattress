package com.nhom4nguoi.ecommerce.model;

public class CartItem {
    private String name;
    private String price;
    private int resourceId;
    private String quantity;

    public CartItem() {
    }

    public CartItem(String name, String price, int resourceId, String quantity) {
        this.name = name;
        this.price = price;
        this.resourceId = resourceId;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
