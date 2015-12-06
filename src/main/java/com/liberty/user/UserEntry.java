package com.liberty.user;

import java.io.Serializable;


public class UserEntry implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7888302454786236243L;

	private String id;

	private Integer age;

	private String name;

	private Integer sex; // 0女，1男

	private AddressEntry addressEntry;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public AddressEntry getAddressEntry() {
		return addressEntry;
	}

	public void setAddressEntry(AddressEntry addressEntry) {
		this.addressEntry = addressEntry;
	}

}
