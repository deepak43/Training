package com.hpe.training.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;
import com.hpe.training.entity.Product;

// @Controller helps in auto detection and loading by @ComponentScan
// as well as helps HandleMapping to map URL paths to handler functions
@Controller
public class ProductController {

	@Autowired
	@Qualifier("htDao")
	private ProductDao dao;
	
	//handler function
	@RequestMapping("/get-all-products")
	public ModelAndView getAllProducts() throws DaoException
	{
		System.out.println("Getting all products");
		List<Product> list = dao.getAll();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/show-products.jsp");
		mav.addObject("products", list);
		
		return mav;
	}
}
