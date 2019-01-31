package com.hpe.training.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//this class by itself is not mapped to any table
// but it can be part of (embeddable) another entity class
@Embeddable
public class ContactDetails {

	private String address;
	private String city;
	private String region;
	@Column(name="postal_code")
	private String postalCode;
	private String country;
	
	public ContactDetails()
	{
		
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
