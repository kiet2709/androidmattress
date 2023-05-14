package com.nhom4nguoi.ecommerce.model;

public class Category {
    private String name;
    private int id;
    private String path;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Category() {
    }
}
