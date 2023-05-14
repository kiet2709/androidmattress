package com.nhom4nguoi.ecommerce.response.Size;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SizeResponse{

	@SerializedName("SizeResponse")
	private List<SizeResponseItem> sizeResponse;

	public void setSizeResponse(List<SizeResponseItem> sizeResponse){
		this.sizeResponse = sizeResponse;
	}

	public List<SizeResponseItem> getSizeResponse(){
		return sizeResponse;
	}

	@Override
 	public String toString(){
		return 
			"SizeResponse{" + 
			"sizeResponse = '" + sizeResponse + '\'' + 
			"}";
		}
}