package com.hpe.training.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_details")
public class LineItem implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="order_id")
	private Integer orderId;
	
	@Id
	@Column(name="product_id")
	private Integer productId;
	
	@Column(name="unit_price")
	private Double unitPrice;
	private Double discount;
	private Integer quantity;
	
	public LineItem()
	{
		
	}

	public Integer getOrder() {
		return orderId;
	}

	public void setOrder(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProduct() {
		return productId;
	}

	public void setProduct(Integer product) {
		this.productId = product;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
