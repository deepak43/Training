package com.hpe.training.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="suppliers")
public class Supplier {

	@Id
	@Column(name="supplier_id")
	private Integer supplierId;
	@Column(name="company_name")
	private String companyName;
	@Column(name="contact_name")
	private String contactPerson;
	@Column(name="contact_title")
	private String contactTitle;
	
	//composition (HAS-A)
	@Embedded
	private ContactDetails contactDetails;
	
	private String phone;
	private String fax;
	@Column(name="home_page")
	private String homePage;
	
	//one supplier might have supplied 0 or more products
	//can be represented by an array or a List<?> or a Set<?>
	@XmlTransient
	@JsonIgnore
	@OneToMany //by default fetchType is LAZY for collections
	@JoinColumn(name="supplier_id")
	private List<Product> products;
	
	//Instead of @JoinColumn(name="supplier_id"), we can also specify
	// @OneToMany(mappedBy="supplier"), where "supplier" is the name of 
	//the member in Product.java containing the JOIN information
	
	public Supplier()
	{
		
	}
	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactTitle() {
		return contactTitle;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
