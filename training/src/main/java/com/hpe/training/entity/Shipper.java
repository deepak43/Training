package com.hpe.training.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "shippers")
public class Shipper {

	@Id
	@Column(name = "shipper_id")
	private Integer shipperId;
	@Column(name = "company_name")
	private String companyName;
	private String phone;
	
	@ManyToMany
	@JoinTable(name = "orders",
				joinColumns = {@JoinColumn(name = "ship_via")},
				inverseJoinColumns= @JoinColumn(name = "customer_id"))
	private Set<Customer> customers;
	
	public Shipper() {
		
	}

	public Integer getShipperId() {
		return shipperId;
	}

	public void setShipperId(Integer shipperId) {
		this.shipperId = shipperId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
	
}
