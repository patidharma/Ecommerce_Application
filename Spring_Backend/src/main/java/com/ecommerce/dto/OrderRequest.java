package com.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;
import com.ecommerce.entity.Payment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {

    private BigDecimal totalPrice;
    private List<OrderItemRequest> items;
    private Payment paymentInfo;
    private String paymentId; 
    
	public OrderRequest() {
		super();
	}
	public OrderRequest(BigDecimal totalPrice, List<OrderItemRequest> items, Payment paymentInfo, String paymentId) {
		super();
		this.totalPrice = totalPrice;
		this.items = items;
		this.paymentInfo = paymentInfo;
		this.paymentId = paymentId;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<OrderItemRequest> getItems() {
		return items;
	}
	public void setItems(List<OrderItemRequest> items) {
		this.items = items;
	}
	public Payment getPaymentInfo() {
		return paymentInfo;
	}
	public void setPaymentInfo(Payment paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	
    
}
