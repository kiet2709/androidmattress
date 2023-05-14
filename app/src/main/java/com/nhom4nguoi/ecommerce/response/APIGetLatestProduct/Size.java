package com.nhom4nguoi.ecommerce.response.APIGetLatestProduct;

import com.google.gson.annotations.SerializedName;

public class Size{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}