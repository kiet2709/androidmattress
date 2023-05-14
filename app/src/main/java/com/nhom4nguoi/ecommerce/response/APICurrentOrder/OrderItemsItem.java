package com.nhom4nguoi.ecommerce.response.APICurrentOrder;

import com.google.gson.annotations.SerializedName;

public class OrderItemsItem{

	@SerializedName("mattress")
	private Mattress mattress;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("totalPrice")
	private Object totalPrice;

	@SerializedName("id")
	private int id;

	@SerializedName("choice_size")
	private ChoiceSize choiceSize;

	public void setMattress(Mattress mattress){
		this.mattress = mattress;
	}

	public Mattress getMattress(){
		return mattress;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setTotalPrice(Object totalPrice){
		this.totalPrice = totalPrice;
	}

	public Object getTotalPrice(){
		return totalPrice;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setChoiceSize(ChoiceSize choiceSize){
		this.choiceSize = choiceSize;
	}

	public ChoiceSize getChoiceSize(){
		return choiceSize;
	}

	@Override
 	public String toString(){
		return 
			"OrderItemsItem{" + 
			"mattress = '" + mattress + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",totalPrice = '" + totalPrice + '\'' + 
			",id = '" + id + '\'' + 
			",choice_size = '" + choiceSize + '\'' + 
			"}";
		}
}