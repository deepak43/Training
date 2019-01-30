package com.hpe.training.cfg;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.hpe.training.dao.ProductDao;
import com.hpe.training.dao.impl.JdbcProductDao;

@ComponentScan(basePackages = {"com.hpe.training.dao"})
@Configuration
public class AppConfig7 {

	@Bean(name= {"dataSource"})
	public BasicDataSource h2Dbcp()
	{
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("org.h2.Driver");
		bds.setUrl("jdbc:h2:tcp://localhost/~/training");
		bds.setUsername("sa");
		bds.setPassword("");
		
		return bds;
	}
	
	@Bean
	public HibernateTemplate ht(SessionFactory sf) {
		return new HibernateTemplate(sf);
	}
	
	//this bean is NOT instanceof session factory, but has a method called
	//getObject() that returns an instanceof SessionFactory.
	//whenever spring has to inject an object of SessionFactory, it will call
	//this bean's getObjcet() function.
	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean lsfb(DataSource ds) {
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(ds);
		sf.setConfigLocation(new ClassPathResource("spring-hibernate.cfg.xml"));
		
		Properties pros = new Properties();
		pros.setProperty("hibernate.show_sql", "false");
		pros.setProperty("hibernate.format_sql", "true");
		sf.setHibernateProperties(pros);
		return sf;
	}
}
