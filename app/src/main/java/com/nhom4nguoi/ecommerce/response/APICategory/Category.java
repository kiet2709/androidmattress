package com.nhom4nguoi.ecommerce.response.APICategory;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Category {

	@SerializedName("Response")
	private List<CategoryItem> response;

	public List<CategoryItem> getResponse(){
		return response;
	}
}