package com.libedi.demo;

import java.io.Serializable;

/**
 * 
 * @author Sangjun, Park
 *
 */
public class Address implements Serializable {
	
	private static final long serialVersionUID = -3137843664066631745L;
	
	private String city;
	private String zipcode;
	
	public Address(String city, String zipcode) {
		super();
		this.city = city;
		this.zipcode = zipcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
}
