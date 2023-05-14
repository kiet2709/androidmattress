package com.nhom4nguoi.ecommerce.response;

public class CartUpdateRequest {
    private int quantity;
    private int choice_sizeId;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getChoice_sizeId() {
        return choice_sizeId;
    }

    public void setChoice_sizeId(int choice_sizeId) {
        this.choice_sizeId = choice_sizeId;
    }

    public CartUpdateRequest(int quantity, int choice_sizeId) {
        this.quantity = quantity;
        this.choice_sizeId = choice_sizeId;
    }

    public CartUpdateRequest() {
    }
}
