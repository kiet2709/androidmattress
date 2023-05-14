package com.nhom4nguoi.ecommerce.response.APIOrder;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OrderResponse{

	@SerializedName("deliverMethod")
	private String deliverMethod;

	@SerializedName("orderTrack")
	private OrderTrack orderTrack;

	@SerializedName("totalPrice")
	private Object totalPrice;

	@SerializedName("totalProductPrice")
	private Object totalProductPrice;

	@SerializedName("id")
	private int id;

	@SerializedName("deliverCost")
	private Object deliverCost;

	@SerializedName("orderDate")
	private String orderDate;

	@SerializedName("orderItems")
	private List<OrderItemsItem> orderItems;

	@SerializedName("userId")
	private int userId;

	public void setDeliverMethod(String deliverMethod){
		this.deliverMethod = deliverMethod;
	}

	public String getDeliverMethod(){
		return deliverMethod;
	}

	public void setOrderTrack(OrderTrack orderTrack){
		this.orderTrack = orderTrack;
	}

	public OrderTrack getOrderTrack(){
		return orderTrack;
	}

	public void setTotalPrice(Object totalPrice){
		this.totalPrice = totalPrice;
	}

	public Object getTotalPrice(){
		return totalPrice;
	}

	public void setTotalProductPrice(Object totalProductPrice){
		this.totalProductPrice = totalProductPrice;
	}

	public Object getTotalProductPrice(){
		return totalProductPrice;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDeliverCost(Object deliverCost){
		this.deliverCost = deliverCost;
	}

	public Object getDeliverCost(){
		return deliverCost;
	}

	public void setOrderDate(String orderDate){
		this.orderDate = orderDate;
	}

	public String getOrderDate(){
		return orderDate;
	}

	public void setOrderItems(List<OrderItemsItem> orderItems){
		this.orderItems = orderItems;
	}

	public List<OrderItemsItem> getOrderItems(){
		return orderItems;
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
			"OrderResponse{" + 
			"deliverMethod = '" + deliverMethod + '\'' + 
			",orderTrack = '" + orderTrack + '\'' + 
			",totalPrice = '" + totalPrice + '\'' + 
			",totalProductPrice = '" + totalProductPrice + '\'' + 
			",id = '" + id + '\'' + 
			",deliverCost = '" + deliverCost + '\'' + 
			",orderDate = '" + orderDate + '\'' + 
			",orderItems = '" + orderItems + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}