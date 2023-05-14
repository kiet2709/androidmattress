package com.nhom4nguoi.ecommerce.response.APIOrder;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Mattress{

	@SerializedName("image")
	private Image image;

	@SerializedName("description")
	private String description;

	@SerializedName("createAt")
	private String createAt;

	@SerializedName("soldQuantity")
	private int soldQuantity;

	@SerializedName("createBy")
	private Object createBy;

	@SerializedName("updateBy")
	private Object updateBy;

	@SerializedName("inventories")
	private List<InventoriesItem> inventories;

	@SerializedName("price")
	private Object price;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("category")
	private Category category;

	@SerializedName("brand")
	private Brand brand;

	@SerializedName("updatedAt")
	private String updatedAt;

	public void setImage(Image image){
		this.image = image;
	}

	public Image getImage(){
		return image;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCreateAt(String createAt){
		this.createAt = createAt;
	}

	public String getCreateAt(){
		return createAt;
	}

	public void setSoldQuantity(int soldQuantity){
		this.soldQuantity = soldQuantity;
	}

	public int getSoldQuantity(){
		return soldQuantity;
	}

	public void setCreateBy(Object createBy){
		this.createBy = createBy;
	}

	public Object getCreateBy(){
		return createBy;
	}

	public void setUpdateBy(Object updateBy){
		this.updateBy = updateBy;
	}

	public Object getUpdateBy(){
		return updateBy;
	}

	public void setInventories(List<InventoriesItem> inventories){
		this.inventories = inventories;
	}

	public List<InventoriesItem> getInventories(){
		return inventories;
	}

	public void setPrice(Object price){
		this.price = price;
	}

	public Object getPrice(){
		return price;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	@Override
 	public String toString(){
		return 
			"Mattress{" + 
			"image = '" + image + '\'' + 
			",description = '" + description + '\'' + 
			",createAt = '" + createAt + '\'' + 
			",soldQuantity = '" + soldQuantity + '\'' + 
			",createBy = '" + createBy + '\'' + 
			",updateBy = '" + updateBy + '\'' + 
			",inventories = '" + inventories + '\'' + 
			",price = '" + price + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",category = '" + category + '\'' + 
			",brand = '" + brand + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			"}";
		}
}