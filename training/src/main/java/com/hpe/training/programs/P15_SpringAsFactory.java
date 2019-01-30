package com.hpe.training.programs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;

public class P15_SpringAsFactory {
	public static void main(String[] args) throws DaoException {
		//a variable representing spring container
		ClassPathXmlApplicationContext ctx;
		
		//System.out.println("--> 1 <--");
		// an object of spring container
		ctx = new ClassPathXmlApplicationContext("context1.xml");
		//System.out.println("--> 2 <--");
		
		ProductDao dao1 = ctx.getBean("jdbc", ProductDao.class);
		int pc = dao1.count();
		
		System.out.println("There are " + pc + " products.");
		
		//ProductDao dao2 = ctx.getBean("jdbc", ProductDao.class);
		
		//System.out.println(dao1==dao2);
		
		ctx.close();
	}

}
