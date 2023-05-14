package com.nhom4nguoi.ecommerce.response.APIGetLatestProduct;

import com.google.gson.annotations.SerializedName;

public class Image{

	@SerializedName("path")
	private String path;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	public String getPath(){
		return path;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}
}