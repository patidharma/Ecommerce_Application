package com.ecommerce.dto;

public class OrderItemRequest {
	

    private Long productId;
    private int quantity;
    
	public OrderItemRequest(Long productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public OrderItemRequest() {
		super();
	}

	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
    
}
