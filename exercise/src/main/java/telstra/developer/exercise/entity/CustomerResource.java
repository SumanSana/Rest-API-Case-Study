package telstra.developer.exercise.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="customer_resource")
@JsonInclude(Include.NON_NULL)
public class CustomerResource {
	

	private String uuid;
	
	private String name;
	
	private String address;
	
	private List<Discount> eligibleDiscounts;
		
	@JsonIgnore
	private List<Discount> customerDiscounts;
	
	private List<PurchasedProduct> products;
	
	public CustomerResource() {
		
	}
	
	
	public CustomerResource(String uuid, String name, String address) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.address = address;
	}

	@Id
	@Column(name="uuid")
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Transient
	public List<Discount> getEligibleDiscounts() {
		return eligibleDiscounts;
	}


	public void setEligibleDiscounts(List<Discount> eligibleDiscounts) {
		this.eligibleDiscounts = eligibleDiscounts;
	}


	@ManyToMany
	@JoinTable(name="customer_discount",
	joinColumns=@JoinColumn(name="cd_uuid"),
	inverseJoinColumns=@JoinColumn(name="cd_discount_id"))
	public List<Discount> getCustomerDiscounts() {
		return customerDiscounts;
	}


	public void setCustomerDiscounts(List<Discount> customerDiscounts) {
		this.customerDiscounts = customerDiscounts;
	}

	@ManyToMany
	@JoinTable(name="customer_product",
	joinColumns=@JoinColumn(name="cp_uuid"),
	inverseJoinColumns=@JoinColumn(name="cp_product_id"))
	public List<PurchasedProduct> getProducts() {
		return products;
	}


	public void setProducts(List<PurchasedProduct> products) {
		this.products = products;
		
	}

	
}
