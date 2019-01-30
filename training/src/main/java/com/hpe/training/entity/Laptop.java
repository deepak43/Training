package com.hpe.training.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "laptops")
public class Laptop {

	@Id
	@Column(name = "sl_no")
	private String serialNumber;
	private String make;
	private String model;
	
	//@ManyToOne is preferred instead of @OneToOne here to make sure that
	// "emp_id" is a foreign key column in the current table "laptops"
	//If you use @OneToOne, then the join column is referred from the other
	//table, "employees". "unique=true" ensures that it is one-to-one
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "emp_id", unique = true, nullable = true)
	private Employee ownedBy;
	
	public Laptop() {
		
	}
	
	public Laptop(String serialNum, String make, String model) {
		this.serialNumber = serialNum;
		this.make = make;
		this.model = model;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Employee getOwnedBy() {
		return ownedBy;
	}

	public void setOwnedBy(Employee ownedBy) {
		this.ownedBy = ownedBy;
	}
	
	//helper fuction to do bidirectional association with Employee
	public void allocateTo(Employee emp)
	{
		this.setOwnedBy(emp);
		emp.setLaptop(this);
	}
	
}
