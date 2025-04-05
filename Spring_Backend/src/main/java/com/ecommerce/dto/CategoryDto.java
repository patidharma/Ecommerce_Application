package com.ecommerce.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class CategoryDto {

    private Long id;
    private String  name;
    private List<ProductDto> productList;
    
    public CategoryDto(Long id, String name, List<ProductDto> productList) {
		super();
		this.id = id;
		this.name = name;
		this.productList = productList;
	}
	public CategoryDto() {
		super();
	}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ProductDto> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductDto> productList) {
		this.productList = productList;
	}
	
    
}
