package com.hpe.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hpe.training.entity.Employee;
import com.hpe.training.entity.Laptop;
import com.hpe.training.utils.HibernateUtil;

public class P14_AssignLaptopToEmployee {

	public static void main(String[] args) {


		SessionFactory factory = HibernateUtil.getsessionFactory();
		try {
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			
			try {
				//persistent objects
				Employee e1 = (Employee) session.get(Employee.class, 4);
				Employee e2 = (Employee) session.get(Employee.class, 6);
				Employee e3 = (Employee) session.get(Employee.class, 7);
				
				//transient objects
				Laptop lt1 = new Laptop("ASDF1234", "ACER", "TravelMate 345");
				Laptop lt2 = new Laptop("LKJH6789", "LENOVO", "Super 999");
				Laptop lt3 = new Laptop("DDDD4444", "APPLE", "Macbook Pro");
				
				//e1.setLaptop(lt2); lt2.setOwnedBy(e1); // java way of bidirectional association
				//e2.setLaptop(lt3); lt3.setOwnedBy(e2);
				
				e1.assignLaptop(lt2);
				e2.assignLaptop(lt3);
				lt1.allocateTo(e3);
				
				session.merge(e1); //updates employees + laptops table
				session.merge(e2); //updates employees + laptops table
				session.merge(lt1); //updates only laptops table
				tx.commit();
				System.out.println("Employee/laptop details saved..!");
			}
			catch(Exception ex)
			{
				tx.rollback();
			}
			session.close();
		}
		finally
		{
			factory.close();
		}

	}

}
