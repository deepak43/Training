package com.hpe.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hpe.training.entity.Category;
import com.hpe.training.utils.HibernateUtil;

public class P03_ChangeCategoryName {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SessionFactory factory = HibernateUtil.getsessionFactory();
		try {
		
			Session session = factory.openSession();
			
			//c1 is persistent object
			Category c1 = (Category)session.get(Category.class, 9);
			
			//chanegs to persistent objects make hibernate mark them as dirty
			//during the "commit", dirty obj result in SQL UPDATEexecution
			
			c1.setCategoryName("Electronics items");
			session.beginTransaction().commit();
			
			session.close();
			
			//c1 now is a detached obj
		}
		finally
		{
			factory.close();
		}

	}

}
