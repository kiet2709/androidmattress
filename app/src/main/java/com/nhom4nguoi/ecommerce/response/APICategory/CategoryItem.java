package com.nhom4nguoi.ecommerce.response.APICategory;

import com.google.gson.annotations.SerializedName;

public class CategoryItem {

	@SerializedName("imagePath")
	private String imagePath;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public String getImagePath(){
		return imagePath;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}