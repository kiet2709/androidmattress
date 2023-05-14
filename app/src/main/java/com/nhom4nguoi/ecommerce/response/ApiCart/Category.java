package com.nhom4nguoi.ecommerce.response.ApiCart;

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

	public void setImage(Image image){
		this.image = image;
	}

	public Image getImage(){
		return image;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(Object description){
		this.description = description;
	}

	public Object getDescription(){
		return description;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Category{" + 
			"image = '" + image + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}