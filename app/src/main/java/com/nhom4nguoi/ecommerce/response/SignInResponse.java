package com.nhom4nguoi.ecommerce.response;

import com.google.gson.annotations.SerializedName;

public class SignInResponse{

	@SerializedName("type")
	public String type;

	@SerializedName("token")
	public String token;

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"SignInResponse{" + 
			"type = '" + type + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}