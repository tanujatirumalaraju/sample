package com.model;

public class AddressModel {
	private int addressId;
	private int userId;
	private String userAddress;
	private String userState;
	private int userPincode;
	private String userDate;
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public int getUserPincode() {
		return userPincode;
	}
	public void setUserPincode(int userPincode) {
		this.userPincode = userPincode;
	}
	public String getUserDate() {
		return userDate;
	}
	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}

	
}
