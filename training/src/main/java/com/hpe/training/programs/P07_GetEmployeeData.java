package com.hpe.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.training.entity.Customer;
import com.hpe.training.entity.Employee;
import com.hpe.training.utils.HibernateUtil;

public class P07_GetEmployeeData {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getsessionFactory();
		try {
			Session session = factory.openSession();
			Employee e1 = (Employee) session.get(Employee.class, 5);
			
			System.out.println("Name: " + e1.getFirstName() + " " + e1.getLastName());
			System.out.println("City: " + e1.getContactDetails().getCity());
			System.out.println("Manager: " + e1.getManager().getFirstName() + " " + e1.getManager().getLastName() );
			System.out.println();
			
			System.out.println("Subordinates: ");
			for(Employee sub : e1.getSubordinates())
			{
				System.out.println(sub.getFirstName() + " "+ sub.getLastName());
			}
			
			System.out.println();
			
			System.out.println("Customers whos orders have been processed by: ");
			for(Customer c : e1.getCustomers())
			{
				System.out.println(c.getCompanyName());
			}
			
			session.close();
		}
		finally
		{
			factory.close();
		}

	}

}
