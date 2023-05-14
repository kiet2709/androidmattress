package com.nhom4nguoi.ecommerce.response;

import com.google.gson.annotations.SerializedName;

public class ProfileRequest{
	private String firstName;

	private String lastName;
	private String phoneNumber;

	private String image;

	private String birthday;

	private String address;

	public void setBirthday(String birthday){
		this.birthday = birthday;
	}

	public String getBirthday(){
		return birthday;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setImage(String image){
		this.image = image;
	}

	public Object getImage(){
		return image;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	@Override
 	public String toString(){
		return 
			"ProfileRequest{" + 
			"birthday = '" + birthday + '\'' + 
			",firstName = '" + firstName + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",image = '" + image + '\'' + 
			",phoneNumber = '" + phoneNumber + '\'' + 
			",address = '" + address + '\'' + 
			"}";
		}
}