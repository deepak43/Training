package com.hpe.training.programs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hpe.training.entity.Category;
import com.hpe.training.entity.Order;
import com.hpe.training.entity.Product;
import com.hpe.training.utils.HibernateUtil;

public class P12_TestingHQL {

	private static Session session;
	
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getsessionFactory();
		try {
			session = factory.openSession();
			
			//printAllCategories();
			
			//printProductsByPriceRange(50.00, 500.00); //minprice=50 maxprice=500
			
			//printProductsByCategory("Beverages");
			
			//printProductsByCity("London");
			
			//printCategoriesBySupplier("Exotic Liquids"); //Exotic Liquids - supplier's company name
			
			//printProductsByPage(5); //4 is pageno assuming page size is 10
			
			//printProductNamesAndPrices(); //example for PROJECTION
			
			//printCategorywiseProductSummary(); //example of GROUP BY
			
			//incrementProductPriceBy(1.0); //inc price is $1
			
			//printCustomersFromCity("London");
			
			printOrdersHavingOrderTotalMoreThan(10000);
			
			
			session.close();
		}
		finally
		{
			factory.close();
		}
	}

	private static void printOrdersHavingOrderTotalMoreThan(double orderTotal) {
		String sql = "select * from orders where order_id in \r\n" + 
				"(select order_id from order_details\r\n" + 
				"group by order_id\r\n" + 
				"having sum(unit_price*quantity*(1-discount))>=:ORDER_TOTAL)";
		
		SQLQuery qry = session.createSQLQuery(sql);
		qry.addEntity(Order.class); //without this qry.list() will return List<Object[]>
		qry.setDouble("ORDER_TOTAL", orderTotal);
		List<Order> list = qry.list();
		
		for(Order ord : list)
		{
			System.out.println("Order id: " + ord.getOrderId());
			System.out.println("Customer: " + ord.getCustomer().getCompanyName());
			System.out.println("Employee: " + ord.getEmployee().getFirstName());
			System.out.println();
		}
		
	}

	static void printCustomersFromCity(String city) {
		// use sql queries instead of hql in situations
		//1. when the table under question is not mapped to any entity
		//2. when migrating your jdbc code into hibernate code, the query may be too complex
		//3. when leveraging the db features such as stored functions, procedures, built in functions
		String sql = "SELECT * FROM CUSTOMERS where city= :CITY";
		SQLQuery qry = session.createSQLQuery(sql);
		qry.setString("CITY", city);
		
		List<Object[]> list = qry.list();
		System.out.println("Customers from " + city + " are: ");
		
		for(Object[] data : list)
		{
			System.out.printf("%s [%s]\n", data[1], data[2]);
		}
		
	}

	private static void incrementProductPriceBy(double incPrice) {
		Transaction tx = session.beginTransaction();
		String hql = "update Product set unitPrice = unitPrice + :INC_PRICE";
		Query qry = session.createQuery(hql);
		qry.setDouble("INC_PRICE", incPrice);
		try
		{
			qry.executeUpdate();
			tx.commit();
			System.out.printf("Product price increased by $%.2f for each product\n", incPrice);
		}
		catch(Exception ex)
		{
			tx.rollback();
			System.out.println("Something went wrong..!!  -> " + ex.getMessage());
		}
		
	}

	private static void printCategorywiseProductSummary() {
		String hql = "select p.category.categoryName, count(p), avg(p.unitPrice), "
				+ "sum(p.unitPrice * p.unitsInStock) from Product p "
				+ "group by p.category.categoryName "
				+ "having count(p) > 10 "
				+ "order by p.category.categoryName";
		Query qry = session.createQuery(hql);
		
		List<Object[]> list = qry.list();
		
		for(Object[] data : list)
		{
			String name = (String) data[0];
			Long count = (Long)data[1];
			Double avgPrice = (Double) data[2];
			Double stockValue = (Double) data[3];
			System.out.printf("%s (count=%d,  avg=%.2f,  stock_val=%.2f)\n", name, count, avgPrice, stockValue);
		}
		
	}

	static void printProductNamesAndPrices() {
		String hql = "select productName, unitPrice from Product";
		Query qry = session.createQuery(hql);
		qry.setMaxResults(10);
		List<Object[]> list = qry.list();
		
		for(Object[] data : list)
		{
			String name = (String) data[0];
			Double price = (Double)data[1];
			System.out.println(name + " -> " + price);
		}
		
	}

	static void printProductsByPage(int pageNum) {
		int pageSize = 10;
		int offset = (pageNum - 1) * pageSize;
		String hql = "from Product";
		Query qry = session.createQuery(hql);
		qry.setFirstResult(offset);
		qry.setMaxResults(pageSize);
		
		List<Product> list = qry.list();
		
		for(Product p : list)
		{
			System.out.printf("%2d %-40s $%10.2f\n",
					p.getProductId(),
					p.getProductName(),
					p.getUnitPrice());
		}
	}

	//example for joining a ManyToMany or OneToMany member
	static void printCategoriesBySupplier(String companyName) {

		String hql = "select c from Category c join c.products p where p.supplier.companyName = ?";  //using of ? is deprecated. here instead of ? we can use :<String>. as used below
		
		Query qry = session.createQuery(hql);
		qry.setString(0, companyName);
		
		Set<Category> categories = new HashSet<Category>(); 
		categories.addAll(qry.list());
		System.out.printf("Categories supplied by %s:\n", companyName);
		
		for(Category c : categories)
		{
			System.out.println(c.getCategoryName());
		}
		
	}

	//example for joining a ManyToOne or OneToOne member
	static void printProductsByCategory(String categoryName) {
		String hql = "from Product where category.categoryName = :CAT_NAME";
		Query qry = session.createQuery(hql);
		qry.setString("CAT_NAME", categoryName);
		
		List<Product> list = qry.list();
		System.out.printf("Products in %s:\n", categoryName);
		
		for(Product p : list)
		{
			System.out.println(p.getProductName() + " -> " + p.getUnitPrice());
		}
	}
	
	static void printProductsByCity(String cityName) {
		//from Product where supplier.contactDetails.city = ?
		String hql = "from Product where supplier.contactDetails.city = :CITY_NAME";
		Query qry = session.createQuery(hql);
		qry.setString("CITY_NAME", cityName);
		
		List<Product> list = qry.list();
		System.out.printf("Products in %s:\n", cityName);
		
		for(Product p : list)
		{
			System.out.println(p.getProductName() + " -> " + p.getUnitPrice());
		}
	}

	static void printProductsByPriceRange(double minPrice, double maxPrice) {

		//String hql = "from Product where unitPrice between ? and ?";
		String hql = "from Product where unitPrice between :MIN and :MAX";
		Query qry = session.createQuery(hql);
		qry.setDouble("MIN", minPrice);
		qry.setDouble("MAX", maxPrice);
		List<Product> list = qry.list();
		System.out.printf("Products between $%.2f and $%.2f are:\n", minPrice, maxPrice);
		
		for(Product p : list)
		{
			System.out.println(p.getProductName() + " -> " + p.getUnitPrice());
		}
		
	}

	static void printAllCategories() {
		//String hql = "SELECT c FROM Category c";
		String hql = "from Category";
		Query qry = session.createQuery(hql);
		List<Category> list = qry.list(); //HQL is converted to SQL and is executed here
		
		for(Category c : list)
		{
			System.out.println(c.getCategoryName());
		}
		
	}
}
