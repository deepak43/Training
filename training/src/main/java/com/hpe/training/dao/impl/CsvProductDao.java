package com.hpe.training.dao.impl;

import java.util.List;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;
import com.hpe.training.entity.Product;

public class CsvProductDao implements ProductDao {

	public CsvProductDao() {
		//System.out.println("inside CsvProductDao constructor...!!!");
	}
	
	@Override
	public int count() throws DaoException {
		return 99;
	}

	@Override
	public void add(Product product) throws DaoException {
		throw new DaoException("Method not implemented..!!");
		
	}

	@Override
	public Product get(Integer productId) throws DaoException {
		throw new DaoException("Method not implemented..!!");
	}

	@Override
	public void update(Product product) throws DaoException {
		throw new DaoException("Method not implemented..!!");
		
	}

	@Override
	public void delete(Integer productId) throws DaoException {
		throw new DaoException("Method not implemented..!!");
		
	}

	@Override
	public List<Product> getAll() throws DaoException {
		throw new DaoException("Method not implemented..!!");
	}

	@Override
	public List<Product> getProductByPrice(Double min, Double max) throws DaoException {
		throw new DaoException("Method not implemented..!!");
	}

	@Override
	public List<Product> getProductsNotInStock() throws DaoException {
		throw new DaoException("Method not implemented..!!");
	}

	@Override
	public List<Product> getDiscontinuedProducts() throws DaoException {
		throw new DaoException("Method not implemented..!!");
	}
}
