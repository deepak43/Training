package com.hpe.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.training.entity.Category;
import com.hpe.training.entity.Product;
import com.hpe.training.entity.Supplier;
import com.hpe.training.utils.HibernateUtil;

public class P05_GetProductAndCategory {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getsessionFactory();
		try {
			Session session = factory.openSession();
			
			Integer id = 1;
			Product p1 = (Product) session.get(Product.class, id);
			session.close();
			
			Category c1 = p1.getCategory();
			
			System.out.println("Product name: "+ p1.getProductName());
			System.out.println("Price " + p1.getUnitPrice());
			System.out.println("Category " + c1.getCategoryName());
			System.out.println("Description " + c1.getDescription());
			
			Supplier s1 = p1.getSupplier();
			
			//supplier info
			System.out.println("Supplier name = " + s1.getCompanyName());
			System.out.println("Contact person = " + s1.getContactPerson());
			System.out.println("Citu/Region  = " + s1.getContactDetails().getCity() + 
					"/" + s1.getContactDetails().getRegion());
		
		}
		finally
		{
			factory.close();
		}

	}

}
