package com.hpe.training.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hpe.training.cfg.AppConfig1;
import com.hpe.training.cfg.AppConfig2;
import com.hpe.training.cfg.AppConfig3;
import com.hpe.training.cfg.AppConfig4;
import com.hpe.training.cfg.AppConfig5;
import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;

public class P16_TestingAnnonationConfiguration {

	public static void main(String[] args) throws DaoException {
		// reference of a spring container
		AnnotationConfigApplicationContext ctx;
		
		// object of a spring container
		ctx = new AnnotationConfigApplicationContext(AppConfig5.class);
		
		ProductDao dao = ctx.getBean("jdbc", ProductDao.class);
		
		int pc = dao.count();
		
		System.out.println("There are " + pc + " products.");
		
		ctx.close();

	}

}
