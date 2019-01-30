package com.hpe.training.cfg;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.hpe.training.dao.ProductDao;
import com.hpe.training.dao.impl.JdbcProductDao;

@Configuration
@ComponentScan(basePackages= {"com.hpe.training.dao.impl"})
public class AppConfig5 {

	@Bean(name= {"dataSource", "dbcp"})
	public BasicDataSource h2Dbcp()
	{
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("org.h2.Driver");
		bds.setUrl("jdbc:h2:tcp://localhost/~/training");
		bds.setUsername("sa");
		bds.setPassword("");
		
		return bds;
	}
}
