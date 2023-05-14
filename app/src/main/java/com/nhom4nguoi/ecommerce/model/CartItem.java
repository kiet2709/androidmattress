package com.nhom4nguoi.ecommerce.model;

public class CartItem {
    private String name;
    private String price;
    private int id;
    private String quantity;
    private String size;
    private String path;
    private int sizeNumber;

    public int getSizeNumber() {
        return sizeNumber;
    }

    public void setSizeNumber(int sizeNumber) {
        this.sizeNumber = sizeNumber;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public CartItem() {
    }

    public CartItem(String name, String price, int id, String quantity, String size) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.quantity = quantity;
        this.size = size;
    }
}
