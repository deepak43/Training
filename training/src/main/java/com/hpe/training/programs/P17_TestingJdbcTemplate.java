package com.hpe.training.programs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.hpe.training.cfg.AppConfig6;
import com.hpe.training.entity.Shipper;

public class P17_TestingJdbcTemplate {

	//this is a class confined by spring's contract
	//that knows how to convert a ResultSet into a Shipper
	static class ShipperRowMapper implements RowMapper<Shipper> {
		
		@Override
		public Shipper mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Shipper shipper = new Shipper();
			shipper.setShipperId(rs.getInt("shipper_id"));
			shipper.setCompanyName(rs.getString("company_name"));
			shipper.setPhone(rs.getString("phone"));
			
			return shipper;
			
		}
	}
	
	private static JdbcTemplate template;
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig6.class);
		template = ctx.getBean(JdbcTemplate.class);
		
		//addNewShipper();
		
		//printProductCount();
		
		//printEmployeeNameForId(4);
		
		//printShipperData(2);
		
		//printShipperData2();
		
		//printShipperDataForId(2);
		
		printAllShippers();
		
		ctx.close();

	}

	static void printAllShippers() {
		String sql = "select * from shippers";
		
		RowMapper<Shipper> srm = (rs, rowNum) -> {
			Shipper shipper = new Shipper();
			shipper.setShipperId(rs.getInt("shipper_id"));
			shipper.setCompanyName(rs.getString("company_name"));
			shipper.setPhone(rs.getString("phone"));
			
			return shipper;
		};
		
		List<Shipper> list = template.query(sql, srm);
		
		for(Shipper sr : list)
		{
			System.out.println(sr.getCompanyName() + " -> " + sr.getPhone() );
		}
		
	}

	static void printShipperDataForId(int shipperId) {
		String sql = "select * from shippers where shipper_id = ?";
		//SQL results in 1 row multiple columns
		Shipper sh = template.queryForObject(sql, new ShipperRowMapper(), shipperId);
		System.out.println("Company Name - " + sh.getCompanyName());
		System.out.println("Phone - " + sh.getPhone());
		
	}

	static void printShipperData2() {
		String sql = "select * from shippers";
		//use queryForList() function when SQL results in multiple rows
		List<Map<String, Object>> list = template.queryForList(sql);
		for(Map<String, Object> rec : list)
		{
			System.out.println("Company - " + rec.get("company_name"));
			System.out.println("Phone - " + rec.get("phone"));
		}
		
	}

	static void printShipperData(int shipperId) {
		String sql = "select * from shippers where shipper_id = ?";
		//use queryForMap() function when the SQL results in 1 row and multiple columns
		Map<String, Object> rec = template.queryForMap(sql, shipperId);
		System.out.println(rec);
		
	}

	static void printEmployeeNameForId(int empId) {
		String sql = "select first_name || ' ' || last_name from employees where employee_id = ?";
		String name = template.queryForObject(sql, String.class, empId);
		System.out.println("Hello, " + name);
		
	}

	static void printProductCount() {
		String sql = "select count(*) from products";
		//use queryForObject() when the SQL results in 1 row and 1 column
		Integer pc = template.queryForObject(sql, Integer.class);
		System.out.println("There are " + pc + " products.");
		
	}

	static void addNewShipper() {
		String sql = "insert into shippers values(?, ?, ?)";
		//update() function for DML
		template.update(sql, 4, "Vinod Transports", "9090909090");
		System.out.println("New shipper added..!!");
	}

}
