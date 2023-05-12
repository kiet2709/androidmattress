package com.nhom4nguoi.ecommerce.model;

import java.util.Date;

public class Order {
    private Date orderDate;
    private String sum;

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

    public Order() {
    }
}
