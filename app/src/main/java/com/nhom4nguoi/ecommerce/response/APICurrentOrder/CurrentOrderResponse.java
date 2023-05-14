package com.nhom4nguoi.ecommerce.response.APICurrentOrder;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CurrentOrderResponse{

	@SerializedName("CurrentOrderResponse")
	private List<CurrentOrderResponseItem> currentOrderResponse;

	public void setCurrentOrderResponse(List<CurrentOrderResponseItem> currentOrderResponse){
		this.currentOrderResponse = currentOrderResponse;
	}

	public List<CurrentOrderResponseItem> getCurrentOrderResponse(){
		return currentOrderResponse;
	}

	@Override
 	public String toString(){
		return 
			"CurrentOrderResponse{" + 
			"currentOrderResponse = '" + currentOrderResponse + '\'' + 
			"}";
		}
}