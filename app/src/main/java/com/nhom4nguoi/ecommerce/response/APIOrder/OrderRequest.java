package com.nhom4nguoi.ecommerce.response.APIOrder;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OrderRequest{

	@SerializedName("deliverMethod")
	private String deliverMethod;

	@SerializedName("orderDate")
	private String orderDate;

	@SerializedName("cartItemId")
	private List<Integer> cartItemId;

	public void setDeliverMethod(String deliverMethod){
		this.deliverMethod = deliverMethod;
	}

	public String getDeliverMethod(){
		return deliverMethod;
	}

	public void setOrderDate(String orderDate){
		this.orderDate = orderDate;
	}

	public String getOrderDate(){
		return orderDate;
	}

	public void setCartItemId(List<Integer> cartItemId){
		this.cartItemId = cartItemId;
	}

	public List<Integer> getCartItemId(){
		return cartItemId;
	}

	@Override
 	public String toString(){
		return 
			"OrderRequest{" + 
			"deliverMethod = '" + deliverMethod + '\'' + 
			",orderDate = '" + orderDate + '\'' + 
			",cartItemId = '" + cartItemId + '\'' + 
			"}";
		}
}