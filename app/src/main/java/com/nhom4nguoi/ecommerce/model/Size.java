package com.nhom4nguoi.ecommerce.model;

import java.io.Serializable;

public class Size implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Size(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Size() {
    }
}
