package com.nhom4nguoi.ecommerce.response.APIGetLatestProduct;

import com.google.gson.annotations.SerializedName;

public class InventoriesItem{

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("size")
	private Size size;

	@SerializedName("id")
	private int id;

	public int getQuantity(){
		return quantity;
	}

	public Size getSize(){
		return size;
	}

	public int getId(){
		return id;
	}
}