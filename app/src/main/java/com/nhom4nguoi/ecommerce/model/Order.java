package com.nhom4nguoi.ecommerce.model;

import java.util.Date;

public class Order {
    int id;
    int orderTrackId;
    private Date orderDate;
    private String sum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderTrackId() {
        return orderTrackId;
    }

    public void setOrderTrackId(int orderTrackId) {
        this.orderTrackId = orderTrackId;
    }

    public Order(Date orderDate, String sum) {
        this.orderDate = orderDate;
        this.sum = sum;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderTrackId=" + orderTrackId +
                ", orderDate=" + orderDate +
                ", sum='" + sum + '\'' +
                '}';
    }

    public Order() {
    }
}
