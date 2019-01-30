package com.hpe.training.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hpe.training.dao.impl.JdbcProductDao;

//This is an alternate context1.xml, which defined one or more beans

@Configuration  //this is equivalent of <beans> element of context1.xml
public class AppConfig1 {
	
	public AppConfig1()
	{
		System.out.println("AppConfig1 constructor called...!!");
	}

	@Bean(name = {"jdbc", "dao"}) //this is equivalent of <bean> element of context1.xml
	public JdbcProductDao dao() {
		System.out.println("AppConfig1.dao() called..!!");
		JdbcProductDao jpd = new JdbcProductDao();
		jpd.setDriverClassName("org.h2.Driver");
		jpd.setUrl("jdbc:h2:tcp://localhost/~/training");
		jpd.setUsername("sa");
		jpd.setPassword("");
		return jpd;
	}
}
