package com.ecommerce.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.OrderItemDto;
import com.ecommerce.dto.OrderRequest;
import com.ecommerce.dto.Response;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.enums.OrderStatus;
import com.ecommerce.exception.NotFoundException;
import com.ecommerce.mapper.EntityDtoMapper;
import com.ecommerce.repository.OrderItemRepo;
import com.ecommerce.repository.OrderRepo;
import com.ecommerce.repository.ProductRepo;
import com.ecommerce.service.interf.OrderItemService;
import com.ecommerce.service.interf.UserService;
import com.ecommerce.specification.OrderItemSpecification;

import lombok.extern.slf4j.Slf4j;

@Service

@Slf4j
public class OrderItemServiceImpl implements OrderItemService {


    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;
    private final ProductRepo productRepo;
    private final UserService userService;
    private final EntityDtoMapper entityDtoMapper;


    public OrderItemServiceImpl(OrderRepo orderRepo, OrderItemRepo orderItemRepo, ProductRepo productRepo,
			UserService userService, EntityDtoMapper entityDtoMapper) {
		super();
		this.orderRepo = orderRepo;
		this.orderItemRepo = orderItemRepo;
		this.productRepo = productRepo;
		this.userService = userService;
		this.entityDtoMapper = entityDtoMapper;
	}

	@Override
    public Response placeOrder(OrderRequest orderRequest) {

        User user = userService.getLoginUser();
        //map order request items to order entities

        List<OrderItem> orderItems = orderRequest.getItems().stream().map(orderItemRequest -> {
            Product product = productRepo.findById(orderItemRequest.getProductId())
                    .orElseThrow(()-> new NotFoundException("Product Not Found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.getQuantity()))); //set price according to the quantity
            orderItem.setStatus(OrderStatus.PENDING);
            orderItem.setUser(user);
            return orderItem;

        }).collect(Collectors.toList());

        //calculate the total price
        BigDecimal totalPrice = orderRequest.getTotalPrice() != null && orderRequest.getTotalPrice().compareTo(BigDecimal.ZERO) > 0
                ? orderRequest.getTotalPrice()
                : orderItems.stream().map(OrderItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        //create order entity
        Order order = new Order();
        order.setOrderItemList(orderItems);
        order.setTotalPrice(totalPrice);

        //set the order reference in each orderitem
        orderItems.forEach(orderItem -> orderItem.setOrder(order));

        orderRepo.save(order);

        return Response.builder()
                .status(200)
                .message("Order was successfully placed")
                .build();

    }
	
//    @Override
//    public Response placeOrder(OrderRequest orderRequest) {
//
//        User user = userService.getLoginUser();
//
////         ✅ Check if Payment ID exists
//        if (orderRequest.getPaymentId() == null || orderRequest.getPaymentId().isEmpty()) {
//            throw new IllegalArgumentException("Payment ID is missing or invalid");
//        }
//
//        // ✅ Verify Payment (Mock Verification)
////        boolean isPaymentValid = paymentService.verifyPayment(orderRequest.getPaymentId());
////        if (!isPaymentValid) {
////            return Response.builder()
////                    .status(400)
////                    .message("Payment verification failed")
////                    .build();
////        }
//
//        // ✅ Map order request items to order entities
//        List<OrderItem> orderItems = orderRequest.getItems().stream().map(orderItemRequest -> {
//            Product product = productRepo.findById(orderItemRequest.getProductId())
//                    .orElseThrow(() -> new NotFoundException("Product Not Found"));
//
//            OrderItem orderItem = new OrderItem();
//            orderItem.setProduct(product);
//            orderItem.setQuantity(orderItemRequest.getQuantity());
//            orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.getQuantity())));
//            orderItem.setStatus(OrderStatus.PENDING);
//            orderItem.setUser(user);
//            return orderItem;
//        }).collect(Collectors.toList());
//
//       
//        BigDecimal totalPrice = orderRequest.getTotalPrice() != null && orderRequest.getTotalPrice().compareTo(BigDecimal.ZERO) > 0
//                ? orderRequest.getTotalPrice()
//                : orderItems.stream().map(OrderItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        
//        Order order = new Order();
//        order.setOrderItemList(orderItems);
//        order.setTotalPrice(totalPrice);
//
//        // Set the order reference in each order item
//        orderItems.forEach(orderItem -> orderItem.setOrder(order));
//        if (orderRequest.getPaymentInfo() != null) {
//            Payment payment = new Payment();
//            payment.setAmount(totalPrice);  // Assuming full amount is paid
//            payment.setMethod(orderRequest.getPaymentInfo().getMethod());
//            payment.setStatus("PENDING"); // Default status
//            payment.setOrder(order);  // Link payment to order
//
//            // Set payment in order
////            order.setPayment(payment);
//        }
//
//        // ✅ Save the order
//        orderRepo.save(order);
//
//        return Response.builder()
//                .status(200)
//                .message("Order was successfully placed with Payment ID: " + orderRequest.getPaymentId())
//                .build();
//    }

    

    @Override
    public Response updateOrderItemStatus(Long orderItemId, String status) {
        OrderItem orderItem = orderItemRepo.findById(orderItemId)
                .orElseThrow(()-> new NotFoundException("Order Item not found"));

        orderItem.setStatus(OrderStatus.valueOf(status.toUpperCase()));
        orderItemRepo.save(orderItem);
        return Response.builder()
                .status(200)
                .message("Order status updated successfully")
                .build();
    }

    @Override
    public Response filterOrderItems(OrderStatus status, LocalDateTime startDate, LocalDateTime endDate, Long itemId, Pageable pageable) {
        Specification<OrderItem> spec = Specification.where(OrderItemSpecification.hasStatus(status))
                .and(OrderItemSpecification.createdBetween(startDate, endDate))
                .and(OrderItemSpecification.hasItemId(itemId));

        Page<OrderItem> orderItemPage = orderItemRepo.findAll(spec, pageable);

        if (orderItemPage.isEmpty()){
            throw new NotFoundException("No Order Found");
        }
        List<OrderItemDto> orderItemDtos = orderItemPage.getContent().stream()
                .map(entityDtoMapper::mapOrderItemToDtoPlusProductAndUser)
                .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .orderItemList(orderItemDtos)
                .totalPage(orderItemPage.getTotalPages())
                .totalElement(orderItemPage.getTotalElements())
                .build();
    }

}
