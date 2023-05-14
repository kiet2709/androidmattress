package com.nhom4nguoi.ecommerce.response.ApiCart;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CartResponse{

	@SerializedName("id")
	private int id;

	@SerializedName("cartItems")
	private List<CartItemsItem> cartItems;

	@SerializedName("userId")
	private int userId;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCartItems(List<CartItemsItem> cartItems){
		this.cartItems = cartItems;
	}

	public List<CartItemsItem> getCartItems(){
		return cartItems;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"CartResponse{" + 
			"id = '" + id + '\'' + 
			",cartItems = '" + cartItems + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}