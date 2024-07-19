package models;

import java.util.Date;

public class RatingModel {
	private String customerId;
	private String orderId;
	private String productId;
	private int rating;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public RatingModel(String customerId, String orderId, String productId, int rating) {
		super();
		this.customerId = customerId;
		this.orderId = orderId;
		this.productId = productId;
		this.rating = rating;
	}
	public RatingModel() {
		super();
	}

	
}
