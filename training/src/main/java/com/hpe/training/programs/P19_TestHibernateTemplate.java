package com.hpe.training.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.hpe.training.cfg.AppConfig7;
import com.hpe.training.entity.Product;

public class P19_TestHibernateTemplate {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig7.class);
		
		//HibernateTemplate implements all the methods and more from
		//Session, Query and Criteria API of Hibernate
		HibernateTemplate ht = ctx.getBean(HibernateTemplate.class);
		
		Product p = ht.get(Product.class, 1);
		
		System.out.println("Product name: " + p.getProductName());
		System.out.println("Price: $" + p.getUnitPrice());
		System.out.println("Category: "+ p.getCategory().getCategoryName());
		System.out.println("Supplier: " + p.getSupplier().getCompanyName());
		System.out.println("Supplied from: " + p.getSupplier().getContactDetails().getCountry());
		
		ctx.close();
	}

}
