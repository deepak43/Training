package com.hpe.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.training.entity.Product;
import com.hpe.training.entity.Supplier;
import com.hpe.training.utils.HibernateUtil;

public class P06_GetSupplierAndProducts {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getsessionFactory();
		try {
			Session session = factory.openSession();
			Supplier s1 = (Supplier) session.get(Supplier.class, 1);
			
			System.out.println("Supplier name: " + s1.getCompanyName());
			System.out.println("Contact name: " + s1.getContactPerson());
			System.out.println("Products supplied by: ");
			
			for(Product p : s1.getProducts())
			{
				System.out.printf("%d - %s ($%.2f)\n",
						p.getProductId(), p.getProductName(), p.getUnitPrice());
			}
			session.close();
		}
		finally
		{
			factory.close();
		}
	}
}
