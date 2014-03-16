package com.fileobj.csv;

import com.fileobj.annotation.Column;

public class TrackingRecord {

	@Column(id=0)
	private String company;
	@Column(id=1)
	private String Country;
	@Column(id=2)
	private String accountNumber;
	@Column(id=3)
	private String destination;
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	@Override
	public String toString() {
		return "TrackingRecord [company=" + company + ", Country=" + Country
				+ ", accountNumber=" + accountNumber + ", destination="
				+ destination + "]";
	}
	
}
