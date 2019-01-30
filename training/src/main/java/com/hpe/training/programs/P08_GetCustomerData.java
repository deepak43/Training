package com.hpe.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.training.entity.Customer;
import com.hpe.training.entity.Employee;
import com.hpe.training.utils.HibernateUtil;

public class P08_GetCustomerData {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getsessionFactory();
		try {
			Session session = factory.openSession();
			Customer c1 = (Customer) session.get(Customer.class, "ALFKI");
			
			System.out.println("Name: " + c1.getCompanyName());
			System.out.println("Contact Person: " + c1.getContactName());
			System.out.println("City: " + c1.getContactDetails().getCity());
			
			System.out.println("Employees who have processed orders: ");
			
			for(Employee e : c1.getEmployees())
			{
				System.out.println(e.getFirstName() + " " + e.getLastName());
			}
			
			session.close();
		}
		finally
		{
			factory.close();
		}

	}

}
