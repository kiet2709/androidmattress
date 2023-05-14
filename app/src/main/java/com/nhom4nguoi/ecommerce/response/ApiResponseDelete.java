package com.nhom4nguoi.ecommerce.response;

import com.google.gson.annotations.SerializedName;

public class ApiResponseDelete{

	@SerializedName("success")
	private boolean success;

	@SerializedName("http")
	private String http;

	@SerializedName("message")
	private String message;

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setHttp(String http){
		this.http = http;
	}

	public String getHttp(){
		return http;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"ApiResponseDelete{" + 
			"success = '" + success + '\'' + 
			",http = '" + http + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}