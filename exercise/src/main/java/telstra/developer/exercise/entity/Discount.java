package telstra.developer.exercise.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="discount")
@JsonInclude(Include.NON_NULL)
public class Discount {
	
	
	private String discountId;
	
	private String type;
	
	private String description;
	
	private BigDecimal amount;
	
	private String productId;

	@JsonIgnore
	private PurchasedProduct product;
	
	
	
	public Discount() {
		
	}
	
	public Discount(String discountId, String type, String description, BigDecimal amount) {
		super();
		this.discountId = discountId;
		this.type = type;
		this.description = description;
		this.amount = amount;
	}

	
	@Id
	@Column(name="discount_id")
	public String getDiscountId() {
		return discountId;
	}

	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}

	@Column(name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="amount")
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@OneToOne()
	@JoinColumn(name="product_id")
	public PurchasedProduct getProduct() {
		return product;
	}



	public void setProduct(PurchasedProduct product) {
		this.product = product;
		if(product!=null)
			setProductId(product.getProductId());
	}



	@Transient
	public String getProductId() {
		return productId;
	}



	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
	

}
