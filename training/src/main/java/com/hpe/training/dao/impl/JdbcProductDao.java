package com.hpe.training.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;
import com.hpe.training.entity.Product;

//@Component("jdbc")
@Repository("jdbc")
//@Service("jdbc")
//@Controller("jdbc")
//@Configuration("jdbc")
//@RestController("jdbc")
public class JdbcProductDao implements ProductDao {

	private String driverClassName;
	private String url;
	private String username;
	private String password;
	@Autowired(required=false)
	private DataSource dataSource;

	public JdbcProductDao() {
		// System.out.println("JdbcProductDao instantiated!");
	}

	public JdbcProductDao(String driverClassName, String url, String username, String password) {
		this.driverClassName = driverClassName;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	//a setter is a.k.a. a writable property
	//setXyz -> prperty "xyz"
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private Connection openConnection() throws ClassNotFoundException, SQLException {
		//if connection pool exists, use a connection from the same
		if(dataSource != null)
		{
			return dataSource.getConnection();
		}
		
		//if pool dosen't exist, then try to create a new connection
		Class.forName(driverClassName);
		return DriverManager.getConnection(url, username, password);
	}

	@Override
	public int count() throws DaoException {
		
		String sql = "select count(*) from products";
		try(
			Connection conn = openConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
		) {
			rs.next();
			return rs.getInt(1);
		}
		catch(Exception ex) {
			throw new DaoException(ex);
		}
		
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