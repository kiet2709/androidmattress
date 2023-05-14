package com.nhom4nguoi.ecommerce.response.Register;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest{

	@SerializedName("birthday")
	private String birthday;

	@SerializedName("firstName")
	private String firstName;

	@SerializedName("lastName")
	private String lastName;

	@SerializedName("password")
	private String password;

	@SerializedName("phoneNumber")
	private String phoneNumber;

	@SerializedName("address")
	private String address;

	@SerializedName("email")
	private String email;

	@SerializedName("enabled")
	private boolean enabled;

	@SerializedName("username")
	private String username;

	public RegisterRequest(String birthday, String firstName, String lastName, String password, String phoneNumber, String address, String email, boolean enabled, String username) {
		this.birthday = birthday;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
		this.enabled = enabled;
		this.username = username;
	}

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

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
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

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}

	public boolean isEnabled(){
		return enabled;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"RegisterRequest{" + 
			"birthday = '" + birthday + '\'' + 
			",firstName = '" + firstName + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",password = '" + password + '\'' + 
			",phoneNumber = '" + phoneNumber + '\'' + 
			",address = '" + address + '\'' + 
			",email = '" + email + '\'' + 
			",enabled = '" + enabled + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}