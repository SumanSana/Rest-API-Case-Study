package telstra.developer.exercise.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
@Table(name="purchased_product")
@JsonInclude(Include.NON_NULL)
public class PurchasedProduct {
	
	
	private String productId;
	
	private String description;
	
	private BigDecimal originalPrice;
	
	private BigDecimal finalPrice;
	
	private Discount discountInformation;
	
	
	
	public PurchasedProduct() {
	
	}
	
	public PurchasedProduct(String productId, String description, BigDecimal originalPrice, BigDecimal finalPrice) {
		super();
		this.productId = productId;
		this.description = description;
		this.originalPrice = originalPrice;
		this.finalPrice = finalPrice;
	}

	@Id
	@Column(name="product_id")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="original_price")
	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	@Column(name="final_price")
	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}

	@OneToOne(mappedBy="product")
	public Discount getDiscountInformation() {
		return discountInformation;
	}

	public void setDiscountInformation(Discount discountInformation) {
		this.discountInformation = discountInformation;
	}

}
