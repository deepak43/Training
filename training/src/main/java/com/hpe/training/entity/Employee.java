package com.hpe.training.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@Column(name = "employee_id")
	private Integer employeeId;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "first_name")
	private String firstName;
	private String title;
	@Column(name = "title_of_courtesy")
	private String titleOfCourtesy;
	@Column(name = "birth_date")
	private Date birthDate;
	@Column(name = "hire_date")
	private Date hireDate;
	@Embedded
	private ContactDetails contactDetails;
	@Column(name = "home_phone")
	private String homePhone;
	private String extension;
	private byte[] photo;
	private String notes;

	@ManyToOne
	@JoinColumn(name = "reports_to")
	private Employee manager;
	
	@OneToMany(mappedBy = "manager")
	private List<Employee> subordinates;
	
	//all the customers that this employee has done business with
	@ManyToMany
	@JoinTable(name = "orders", 
				joinColumns = {@JoinColumn(name = "employee_id")},  // EMPLOYEES.EMPLOYEE.ID => ORDERS.EMPLOYEE_ID
				inverseJoinColumns = {@JoinColumn(name = "customer_id")})	// ORDERS.CUSTOMER_ID => CUSTOMERS.ID				
	private Set<Customer> customers;
	
	//this employee may have zero or one laptop
	@OneToOne(mappedBy = "ownedBy", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Laptop laptop;

	public Employee() {
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleOfCourtesy() {
		return titleOfCourtesy;
	}

	public void setTitleOfCourtesy(String titleOfCourtesy) {
		this.titleOfCourtesy = titleOfCourtesy;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public List<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public Laptop getLaptop() {
		return laptop;
	}

	public void setLaptop(Laptop laptop) {
		this.laptop = laptop;
	}
	
	//helper function to do bidirectinoal association with laptop
	public void assignLaptop(Laptop laptop)
	{
		this.laptop = laptop;
		laptop.setOwnedBy(this);
	}

}
