package com.hpe.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.training.entity.Category;
import com.hpe.training.utils.HibernateUtil;

public class P04_DiffernceBetweenUpdateAndMerge {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getsessionFactory();
		try {
			Session session = factory.openSession();
			
			//c1 is persistent object
			Category c1 = (Category)session.get(Category.class, 9);
			c1.setCategoryName("Electronics products");
			session.close();
			
			// new session; empty
			session = factory.openSession();
			// to make "c1" part of new session
			//we can use update, saveOrUpdate or merge
			session.saveOrUpdate(c1);
			session.beginTransaction().commit();
		
		}
		finally
		{
			factory.close();
		}
	}
}
