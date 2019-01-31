package com.hpe.training.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "products")
public class ProductList {

	private List<Product> product;
	
	public ProductList()
	{
		
	}
	
	public ProductList(List<Product> product)
	{
		this.product = product;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}
}
