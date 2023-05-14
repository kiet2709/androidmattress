package com.nhom4nguoi.ecommerce.response.APICurrentOrder;

import com.google.gson.annotations.SerializedName;

public class OrderTrack{

	@SerializedName("id")
	private int id;

	@SerializedName("status")
	private String status;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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
			"OrderTrack{" + 
			"id = '" + id + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}