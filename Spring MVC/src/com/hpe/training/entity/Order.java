package com.hpe.training.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@Column(name = "order_id")
	private Integer orderId;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@Column(name = "order_date")
	private Date orderDate;
	@Column(name = "required_date")
	private Date requiredDate;
	@Column(name = "shipped_date")
	private Date shippedDate;
	
	@ManyToOne
	@JoinColumn(name = "ship_via")
	private Shipper shippedBy;
	
	private Double freight;
	
	@Column(name = "ship_name")
	private String shipName;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="address", column=@Column(name="ship_address")),
		@AttributeOverride(name="city", column=@Column(name="ship_city")),
		@AttributeOverride(name="region", column=@Column(name="ship_region")),
		@AttributeOverride(name="postalCode", column=@Column(name="ship_postal_code")),
		@AttributeOverride(name="country", column=@Column(name="ship_country")),
	})
	private ContactDetails shipDetails;
	
	public Order() {
		
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public Shipper getShippedBy() {
		return shippedBy;
	}

	public void setShippedBy(Shipper shippedBy) {
		this.shippedBy = shippedBy;
	}

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public ContactDetails getShipAddress() {
		return shipDetails;
	}

	public void setShipAddress(ContactDetails shipDetails) {
		this.shipDetails = shipDetails;
	}
	
}
