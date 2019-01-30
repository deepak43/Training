package com.hpe.training.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public final class HibernateUtil {
	
	private HibernateUtil()
	{
		
	}
	
	
	private static SessionFactory factory;
	public static SessionFactory getsessionFactory()
	{
		if(factory == null)
		{
			//empty configuration object
			Configuration cfg = new Configuration();
			
			//load information from hibernate.cfg.xml
			cfg.configure();
			
			ServiceRegistry registry = new StandardServiceRegistryBuilder().
					applySettings(cfg.getProperties()).build();
			
			factory = cfg.buildSessionFactory(registry);
		}
		
		return factory;
	}

}
