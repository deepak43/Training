package com.hpe.training.programs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hpe.training.entity.Category;
import com.hpe.training.entity.Product;
import com.hpe.training.utils.HibernateUtil;

public class P13_TestingCriteria {

	private static Session session;
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getsessionFactory();
		try {
			session = factory.openSession();
			
			//printAllCategories();
			
			//printProductsByPriceRange(50.00, 500.00);
			
			//printProductsByCategory("Beverages");
			
			//printCategoriesBySupplier("Exotic Liquids");
			
			//printProductNamesAndPrices();
			
			printCategorywiseProductSummary();
			
			session.close();
		}
		finally
		{
			factory.close();
		}
		

	}
	
	static void printCategorywiseProductSummary() {
		Criteria cr = session.createCriteria(Product.class);
		cr.createAlias("category", "cat");
		
		ProjectionList projectionList = Projections.projectionList()		
				.add(Projections.groupProperty("cat.categoryName"))
				.add(Projections.rowCount())
				.add(Projections.avg("unitPrice"));
		
		cr.setProjection(projectionList);
		
		List<Object[]> list = cr.list();
		
		for (Object[] p : list)
		{
			System.out.printf("%s -> %d, $%.2f\n", p[0], p[1], p[2]);
		}
		
	}

	static void printProductNamesAndPrices() {
		Criteria cr = session.createCriteria(Product.class);
		ProjectionList projectionList = Projections.projectionList()		
				.add(Projections.property("productName"))
				.add(Projections.property("unitPrice"));
		
		cr.setProjection(projectionList);
		cr.setMaxResults(10);
		
		List<Object[]> list = cr.list();
		
		for (Object[] p : list)
		{
			System.out.printf("%-35s $%10.2f\n", p[0], p[1]);
		}
		
	}

	static void printCategoriesBySupplier(String supplierName) {
		Criteria cr = session.createCriteria(Category.class);
		cr.createAlias("products", "pr");
		cr.createAlias("pr.supplier", "sup");
		cr.add(Restrictions.eqOrIsNull("sup.companyName", supplierName));
		
		Set<Category> categories = new HashSet<Category>(); 
		categories.addAll(cr.list());
		
		for (Category c : categories)
		{
			System.out.printf("%d - %s (%s)\n", c.getCategoryId(), c.getCategoryName(), c.getDescription());
		}
		
	}

	static void printProductsByCategory(String catName) {
		Criteria cr = session.createCriteria(Product.class);
		cr.createAlias("category", "cat");
		cr.add(Restrictions.eq("cat.categoryName", catName));
		
		List<Product> list = cr.list();
		
		for (Product p : list)
		{
			System.out.printf("%-35s $%10.2f\n", p.getProductName(), p.getUnitPrice());
		}
		
	}

	static void printProductsByPriceRange(double min, double max) {
		Criteria cr = session.createCriteria(Product.class);
		cr.add(Restrictions.ge("unitPrice", min));
		cr.add(Restrictions.le("unitPrice", max));
		List<Product> list = cr.list();
		
		for (Product p : list)
		{
			System.out.printf("%-35s $%10.2f\n", p.getProductName(), p.getUnitPrice());
		}
		
	}
	static void printAllCategories() {
		Criteria cr = session.createCriteria(Category.class);
		List<Category> list = cr.list();
		
		for (Category c : list)
		{
			System.out.printf("%d - %s (%s)\n", c.getCategoryId(), c.getCategoryName(), c.getDescription());
		}
		
	}

}
