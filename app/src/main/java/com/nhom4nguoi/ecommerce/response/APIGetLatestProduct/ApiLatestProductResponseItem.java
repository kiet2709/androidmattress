package com.nhom4nguoi.ecommerce.response.APIGetLatestProduct;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ApiLatestProductResponseItem{

	@SerializedName("image")
	private Image image;

	@SerializedName("sold")
	private int sold;

	@SerializedName("inventories")
	private List<InventoriesItem> inventories;

	@SerializedName("price")
	private int price;

	public void setImage(Image image) {
		this.image = image;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	public void setInventories(List<InventoriesItem> inventories) {
		this.inventories = inventories;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

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

	public Image getImage(){
		return image;
	}

	public int getSold(){
		return sold;
	}

	public List<InventoriesItem> getInventories(){
		return inventories;
	}

	public int getPrice(){
		return price;
	}

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public int getId(){
		return id;
	}

	public Category getCategory(){
		return category;
	}

	public Brand getBrand(){
		return brand;
	}
}