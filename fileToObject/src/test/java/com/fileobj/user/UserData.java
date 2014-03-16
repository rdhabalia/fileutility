package com.fileobj.user;

import com.fileobj.annotation.Column;

public class UserData {

	@Column(id=0)
	public String header;
	@Column(id=1)
	public String firstName;
	@Column(id=2)
	public String lastName;
	@Column(id=3)
	public String phoneNumber;
	@Column(id=4)
	public String state;
	@Column(id=5)
	public String zip;
	@Column(id=6)
	public String country;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	@Override
	public String toString() {
		return "UserData [header=" + header + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", state=" + state + ", zip=" + zip + ", country=" + country
				+ "]";
	}

}