package com.hpe.training.cfg;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.hpe.training.dao.ProductDao;
import com.hpe.training.dao.impl.JdbcProductDao;

@Configuration
public class AppConfig2 {

	//phase 1 - spring registers all the bean names with proxies
	//phase 2 - spring calls all @bean methods to replace the proxies with the actual beans
	//phase 3 - wiring of beans done
	
	@Bean
	@Lazy(true)
	public ProductDao jdbc() {
		JdbcProductDao jpd = new JdbcProductDao();
		jpd.setDataSource(dataSource());
		return jpd;
	}

	@Scope("singleton")
	@Bean
	public BasicDataSource dataSource()
	{
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("org.h2.Driver");
		bds.setUrl("jdbc:h2:tcp://localhost/~/training");
		bds.setUsername("sa");
		bds.setPassword("");
		bds.setInitialSize(5);
		bds.setMaxTotal(50);
		
		return bds;
	}
}
