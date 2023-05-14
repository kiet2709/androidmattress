package com.nhom4nguoi.ecommerce.response.APIGetLatestProduct;

import com.google.gson.annotations.SerializedName;

public class Category{

	@SerializedName("image")
	private Image image;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private Object description;

	@SerializedName("id")
	private int id;

	public Image getImage(){
		return image;
	}

	public String getName(){
		return name;
	}

	public Object getDescription(){
		return description;
	}

	public int getId(){
		return id;
	}
}