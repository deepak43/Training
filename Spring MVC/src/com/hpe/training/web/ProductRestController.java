package com.hpe.training.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;
import com.hpe.training.entity.Product;
import com.hpe.training.entity.ProductList;

@RequestMapping("/api")
@RestController
public class ProductRestController {

	@Autowired
	@Qualifier("htDao")
	private ProductDao dao;
	
	//@ResponseBody - use this if @Controller is used instead of @RestController
	@RequestMapping(path = "/products/{id}", method = RequestMethod.GET, 
			produces = {"application/json", "application/xml"})
	public Product getProductById(@PathVariable("id") int id) throws DaoException
	{
		return dao.get(id);
	}
	
	@RequestMapping(path = "/products", method = RequestMethod.GET, 
			produces = {"application/json"})
	public List<Product> getAllProducts() throws DaoException
	{
		return dao.getAll();
	}
	
	@RequestMapping(path = "/products", method = RequestMethod.GET, 
			produces = {"application/xml"})
	public ProductList getProductList() throws DaoException {
		return new ProductList(dao.getAll());
	}
}
