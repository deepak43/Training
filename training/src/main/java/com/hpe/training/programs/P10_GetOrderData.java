package com.hpe.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.training.entity.ContactDetails;
import com.hpe.training.entity.Customer;
import com.hpe.training.entity.Employee;
import com.hpe.training.entity.Order;
import com.hpe.training.utils.HibernateUtil;

public class P10_GetOrderData {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getsessionFactory();
		
		try {
			Session session = factory.openSession();
			
			Integer orderId = 10255;
			Order ord = (Order) session.get(Order.class, orderId);
			
			System.out.println("Order placed by: ");
			Customer c1 = ord.getCustomer();
			System.out.println("Customer company: " + c1.getCompanyName());
			System.out.println("Address: " + c1.getContactDetails().getAddress());
			System.out.println(c1.getContactDetails().getCity());
			System.out.println(c1.getContactDetails().getCountry());
			System.out.println();
			
			System.out.println("Order processed by: ");
			Employee e1 = ord.getEmployee();
			System.out.println("Name : " + e1.getFirstName() + " " + e1.getLastName());
			System.out.println("Title: " + e1.getTitle());
			System.out.println("City : " + e1.getContactDetails().getCity());
			System.out.println();
			
			System.out.println("Order date: " + ord.getOrderDate());
			System.out.println("Reqd. date: " + ord.getRequiredDate());
			System.out.println("Shppd date: " + ord.getShippedDate());
			System.out.println();
			
			System.out.println("Order delivered by: " + ord.getShippedBy().getCompanyName());
			System.out.println();
			
			ContactDetails cd = ord.getShipAddress();
			System.out.println("Shipped to: ");
			System.out.println(cd.getAddress());
			System.out.println(cd.getCity());
			System.out.println(cd.getCountry() + " - " + cd.getPostalCode());


			session.close();
		} finally {
			factory.close();
		}
	}
}









