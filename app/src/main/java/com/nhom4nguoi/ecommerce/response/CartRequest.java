package com.nhom4nguoi.ecommerce.response;

public class CartRequest {
    private int quantity;
    private int mattressId;
    private String choice_sizeId;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMattressId() {
        return mattressId;
    }

    public void setMattressId(int mattressId) {
        this.mattressId = mattressId;
    }

    public String getChoice_sizeId() {
        return choice_sizeId;
    }

    public void setChoice_sizeId(String choice_sizeId) {
        this.choice_sizeId = choice_sizeId;
    }

    public CartRequest(int quantity, int mattressId, String choice_sizeId) {
        this.quantity = quantity;
        this.mattressId = mattressId;
        this.choice_sizeId = choice_sizeId;
    }


}
