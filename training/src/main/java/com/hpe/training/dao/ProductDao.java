package com.hpe.training.dao;

import java.util.List;

import com.hpe.training.entity.Product;

public interface ProductDao {
	
	//CRUD operations
	public void add(Product product) throws DaoException;
	public Product get(Integer productId) throws DaoException;;
	public void update(Product product) throws DaoException;
	public void delete(Integer productId) throws DaoException;
	
	//Queries
	public int count() throws DaoException;
	public List<Product> getAll() throws DaoException;
	public List<Product> getProductByPrice(Double min, Double max) throws DaoException;
	public List<Product> getProductsNotInStock() throws DaoException;
	public List<Product> getDiscontinuedProducts() throws DaoException;

}
