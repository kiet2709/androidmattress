package com.nhom4nguoi.ecommerce.response.APIProductDetail;

import com.google.gson.annotations.SerializedName;

public class InventoriesItem{

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("size")
	private Size size;

	@SerializedName("id")
	private int id;

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setSize(Size size){
		this.size = size;
	}

	public Size getSize(){
		return size;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"InventoriesItem{" + 
			"quantity = '" + quantity + '\'' + 
			",size = '" + size + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}