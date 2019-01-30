package com.hpe.training.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;
import com.hpe.training.entity.Product;

@Repository
public class JdbcTemplateProductDao implements ProductDao {

	//required = false indicates that spring should do DI only if matching bean is found
	@Autowired(required = false)
	private JdbcTemplate template;
	
	private RowMapper<Product> prm = (rs, index) -> {
		Product p = new Product();
		p.setProductId(rs.getInt("product_id"));
		p.setProductName(rs.getString("product_name"));
		p.setQuantityPerUnit(rs.getString("quantity_per_unit"));
		p.setUnitPrice(rs.getDouble("unit_price"));
		p.setUnitsInStock(rs.getInt("units_in_stock"));
		p.setUnitsOnOrder(rs.getInt("units_on_order"));
		p.setReorderLevel(rs.getInt("reorder_level"));
		p.setDiscontinued(rs.getInt("discontinued"));
		return p;
	};
	
	@Override
	public void add(Product p) throws DaoException {

		String sql = "insert into products(product_name, quantity_per_unit, unit_price, "
				+ "units_in_stock, units_on_order, reorder_level, discontinued, product_id) "
				+ "values(?,?,?,?,?,?,?,?)";
		template.update(sql, 
				p.getProductName(), 
				p.getQuantityPerUnit(), 
				p.getUnitPrice(),
				p.getUnitsInStock(), 
				p.getUnitsOnOrder(), 
				p.getReorderLevel(), 
				p.getDiscontinued(),
				p.getProductId());
	}

	// all of the implemented methods must catch any possible exception,
	//and convert the same into a DaoException, and re-throw it
	//we will use AOP to achieve the same
	@Override
	public Product get(Integer productId) throws DaoException {
		String sql = "select * from products where product_id = ?";
		return template.queryForObject(sql, prm, productId);
	}

	@Override
	public void update(Product p) throws DaoException {

		String sql = "update products set product_name=?, quantity_per_unit=?, unit_price=?, "
				+ "units_in_stock=?, units_on_order=?, reorder_level=?, discontinued=? "
				+ "where product_id=?";
		template.update(sql, 
				p.getProductName(), 
				p.getQuantityPerUnit(), 
				p.getUnitPrice(),
				p.getUnitsInStock(), 
				p.getUnitsOnOrder(), 
				p.getReorderLevel(), 
				p.getDiscontinued(),
				p.getProductId());
	}

	@Override
	public void delete(Integer productId) throws DaoException {

		template.update("delete from products where product_id=?", productId);
	}

	@Override
	public int count() throws DaoException {
		return template.queryForObject("select count(*) from products", Integer.class);
	}

	@Override
	public List<Product> getAll() throws DaoException {
		return template.query("select * from products", prm);
	}

	@Override
	public List<Product> getProductByPrice(Double min, Double max) throws DaoException {
		return template.query("select * from products where unit_price between ? and ?", prm, min, max);
	}

	@Override
	public List<Product> getProductsNotInStock() throws DaoException {
		return template.query("select * from products where units_in_stock=0", prm);
	}

	@Override
	public List<Product> getDiscontinuedProducts() throws DaoException {
		return template.query("select * from products where discontinued=1", prm);
	}

}
