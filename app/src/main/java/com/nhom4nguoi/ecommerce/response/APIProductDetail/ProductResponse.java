package com.nhom4nguoi.ecommerce.response.APIProductDetail;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProductResponse implements Serializable {

	@SerializedName("image")
	private Image image;

	@SerializedName("sold")
	private int sold;

	@SerializedName("inventories")
	private List<InventoriesItem> inventories;

	@SerializedName("price")
	private int price;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	@SerializedName("category")
	private Category category;

	@SerializedName("brand")
	private Brand brand;

	public void setImage(Image image){
		this.image = image;
	}

	public Image getImage(){
		return image;
	}

	public void setSold(int sold){
		this.sold = sold;
	}

	public int getSold(){
		return sold;
	}

	public void setInventories(List<InventoriesItem> inventories){
		this.inventories = inventories;
	}

	public List<InventoriesItem> getInventories(){
		return inventories;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCategory(Category category){
		this.category = category;
	}

	public Category getCategory(){
		return category;
	}

	public void setBrand(Brand brand){
		this.brand = brand;
	}

	public Brand getBrand(){
		return brand;
	}

	@Override
 	public String toString(){
		return 
			"ProductResponse{" + 
			"image = '" + image + '\'' + 
			",sold = '" + sold + '\'' + 
			",inventories = '" + inventories + '\'' + 
			",price = '" + price + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			",category = '" + category + '\'' + 
			",brand = '" + brand + '\'' + 
			"}";
		}
}