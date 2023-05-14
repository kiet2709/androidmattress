package com.nhom4nguoi.ecommerce.response.ApiCart;

import com.google.gson.annotations.SerializedName;

public class Image{

	@SerializedName("path")
	private String path;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	public void setPath(String path){
		this.path = path;
	}

	public String getPath(){
		return path;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"Image{" + 
			"path = '" + path + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}