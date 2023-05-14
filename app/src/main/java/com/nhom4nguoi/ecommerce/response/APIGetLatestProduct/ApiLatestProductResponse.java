package com.nhom4nguoi.ecommerce.response.APIGetLatestProduct;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ApiLatestProductResponse {


	private List<ApiLatestProductResponseItem> apiLatestProductResponse;

	public List<ApiLatestProductResponseItem> getApiLatestProductResponse(){
		return apiLatestProductResponse;
	}

	public void setApiLatestProductResponse(List<ApiLatestProductResponseItem> apiLatestProductResponse) {
		this.apiLatestProductResponse = apiLatestProductResponse;
	}
}