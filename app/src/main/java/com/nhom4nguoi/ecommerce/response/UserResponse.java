package com.nhom4nguoi.ecommerce.response;

public class UserResponse{
	private String birthday;
	private String firstName;
	private String lastName;
	private Image image;
	private String phoneNumber;
	private String address;
	private int id;
	private String email;
	private String username;

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

	public void setImage(Image image){
		this.image = image;
	}

	public Image getImage(){
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

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
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
			"UserResponse{" + 
			"birthday = '" + birthday + '\'' + 
			",firstName = '" + firstName + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",image = '" + image + '\'' + 
			",phoneNumber = '" + phoneNumber + '\'' + 
			",address = '" + address + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
