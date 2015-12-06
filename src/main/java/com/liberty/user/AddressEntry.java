package com.liberty.user;

import java.io.Serializable;

public class AddressEntry implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -139411465363029251L;

	private String email;
	
	private String location;
	
	private String country;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
