package com.hpe.training.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;
import com.hpe.training.entity.Product;

@SuppressWarnings("unchecked")
@Repository("htDao")
public class HibernateTemplateProductDao implements ProductDao {

	@Autowired(required = false)
	private HibernateTemplate template;
	//all methods of HibernateTemplate throws DataAccessException
	//which has to be converted into a DaoException, and must be 
	//re-thrown, which we will do using AOP
	
	@Override
	public void add(Product product) throws DaoException {
		template.persist(product);
	}

	@Override
	public Product get(Integer productId) throws DaoException {
		return template.get(Product.class, productId);
	}

	@Override
	public void update(Product product) throws DaoException {
		template.merge(product);
	}

	@Override
	public void delete(Integer productId) throws DaoException {
		Product p = get(productId);
		if(p==null) throw new DaoException("Invalid product id for deletion");
		
		template.delete(p);
	}

	@Override
	public int count() throws DaoException {
		List<?> list = template.find("select count(p) from Product p"); 
		Object count = list.get(0);
		return new Integer(count.toString());
	}

	@Override
	public List<Product> getAll() throws DaoException {
		return (List<Product>) template.find("from Product");
	}

	@Override
	public List<Product> getProductByPrice(Double min, Double max) throws DaoException {
		DetachedCriteria cr = DetachedCriteria.forClass(Product.class)
				.add(Restrictions.between("unitPrice", min, max));
		
		return (List<Product>) template.findByCriteria(cr);
	}

	@Override
	public List<Product> getProductsNotInStock() throws DaoException {
		//example product 
		Product p = new Product();
		p.setUnitsInStock(0);
		return template.findByExample(p);
	}

	@Override
	public List<Product> getDiscontinuedProducts() throws DaoException {
		//example product 
		Product p = new Product();
		p.setDiscontinued(1);
		return template.findByExample(p);
	}

}
