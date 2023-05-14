package com.nhom4nguoi.ecommerce.response;

import com.google.gson.annotations.SerializedName;

public class OrderStatusRequest{

	@SerializedName("orderId")
	private int orderId;

	@SerializedName("status")
	private String status;

	public OrderStatusRequest(int orderId, String status) {
		this.orderId = orderId;
		this.status = status;
	}

	public void setOrderId(int orderId){
		this.orderId = orderId;
	}

	public int getOrderId(){
		return orderId;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"OrderStatusRequest{" + 
			"orderId = '" + orderId + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}