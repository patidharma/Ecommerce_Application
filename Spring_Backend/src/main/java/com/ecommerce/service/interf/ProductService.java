package com.ecommerce.service.interf;

import com.ecommerce.dto.Response;
import com.ecommerce.entity.TransactionDetails;
import com.razorpay.Order;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public interface ProductService {

    Response createProduct(Long categoryId, MultipartFile image, String name, String description, BigDecimal price);
    Response updateProduct(Long productId, Long categoryId, MultipartFile image, String name, String description, BigDecimal price);
    Response deleteProduct(Long productId);
    Response getProductById(Long productId);
    Response getAllProducts();
    Response getProductsByCategory(Long categoryId);
    Response searchProduct(String searchValue);
    TransactionDetails createTransaction(Integer amount);
    TransactionDetails prepareTransactionDetails(Order order);
	
}
