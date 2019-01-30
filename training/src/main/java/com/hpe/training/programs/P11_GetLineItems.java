package com.hpe.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.training.entity.LineItem;
import com.hpe.training.utils.HibernateUtil;

public class P11_GetLineItems {

	public static void main(String[] args) {
SessionFactory factory = HibernateUtil.getsessionFactory();
		
		try {
			Session session = factory.openSession();
			
			//an instance of lineitem is used for representing a primary key value
			
			LineItem id = new LineItem();
			
			id.setOrder(10248);
			id.setProduct(42);
			
			LineItem item = (LineItem) session.get(LineItem.class, id);
			
			
			System.out.println("Unit price: $" + item.getUnitPrice());
			System.out.println("Discount: " + (item.getDiscount() *100) + "%");
			System.out.println("Quantity: " + item.getQuantity());
			
			session.close();
		} finally {
			factory.close();
		}

	}

}
