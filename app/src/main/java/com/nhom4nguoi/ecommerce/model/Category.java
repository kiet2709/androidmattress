package com.nhom4nguoi.ecommerce.model;

public class Category {
    private String name;
    private int resourceId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public Category(String name, int resourceId) {
        this.name = name;
        this.resourceId = resourceId;
    }

    public Category() {
    }
}
