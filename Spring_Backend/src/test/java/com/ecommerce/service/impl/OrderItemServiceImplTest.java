package com.phegondev.Phegon.Eccormerce.service.impl;

import com.ecommerce.dto.OrderItemDto;
import com.ecommerce.dto.OrderItemRequest;
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
import com.ecommerce.service.impl.OrderItemServiceImpl;
import com.ecommerce.service.interf.UserService;
import com.ecommerce.specification.OrderItemSpecification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderItemServiceImplTest {

    @InjectMocks
    private OrderItemServiceImpl orderItemService;

    @Mock
    private OrderRepo orderRepo;

    @Mock
    private OrderItemRepo orderItemRepo;

    @Mock
    private ProductRepo productRepo;

    @Mock
    private UserService userService;

    @Mock
    private EntityDtoMapper entityDtoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceOrderSuccess() {
        // Arrange
        User user = new User();
        when(userService.getLoginUser()).thenReturn(user);

        Product product = new Product();
        product.setId(1L);
        product.setPrice(new BigDecimal("100"));

        // Creating OrderItemRequest manually
        OrderItemRequest orderItemRequest = new OrderItemRequest();
        orderItemRequest.setProductId(1L);  // Assuming these fields exist
        orderItemRequest.setQuantity(2);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setItems(Collections.singletonList(orderItemRequest)); // Adding OrderItemRequest to OrderRequest

        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        // Act
        Response response = orderItemService.placeOrder(orderRequest);

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals("Order was successfully placed", response.getMessage());
        verify(orderRepo, times(1)).save(any(Order.class));
    }

    @Test
    void testPlaceOrderProductNotFound() {
        // Arrange
        when(userService.getLoginUser()).thenReturn(new User());

        OrderItemRequest orderItemRequest = new OrderItemRequest();
        orderItemRequest.setProductId(1L); // Assuming these fields exist

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setItems(Collections.singletonList(orderItemRequest));

        when(productRepo.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> orderItemService.placeOrder(orderRequest));
    }


    @Test
    void testUpdateOrderItemStatusSuccess() {
        // Arrange
        Long orderItemId = 1L;
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemId);

        when(orderItemRepo.findById(orderItemId)).thenReturn(Optional.of(orderItem));

        // Act
        Response response = orderItemService.updateOrderItemStatus(orderItemId, "SHIPPED");

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals("Order status updated successfully", response.getMessage());
        assertEquals(OrderStatus.SHIPPED, orderItem.getStatus());
        verify(orderItemRepo, times(1)).save(orderItem);
    }

    @Test
    void testUpdateOrderItemStatusNotFound() {
        // Arrange
        Long orderItemId = 1L;
        when(orderItemRepo.findById(orderItemId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> orderItemService.updateOrderItemStatus(orderItemId, "SHIPPED"));
    }

    @Test
    void testFilterOrderItemsSuccess() {
        // Arrange
        Pageable pageable = Pageable.unpaged();
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);

        List<OrderItem> orderItemList = Collections.singletonList(orderItem);
        Page<OrderItem> orderItemPage = new PageImpl<>(orderItemList);

        when(orderItemRepo.findAll(any(Specification.class), eq(pageable))).thenReturn(orderItemPage);
        when(entityDtoMapper.mapOrderItemToDtoPlusProductAndUser(orderItem)).thenReturn(new OrderItemDto());

        // Act
        Response response = orderItemService.filterOrderItems(OrderStatus.PENDING, null, null, null, pageable);

        // Assert
        assertEquals(200, response.getStatus());
        assertNotNull(response.getOrderItemList());
        assertEquals(1, response.getOrderItemList().size());
    }

    @Test
    void testFilterOrderItemsNoResults() {
        // Arrange
        Pageable pageable = Pageable.unpaged();
        Page<OrderItem> emptyPage = new PageImpl<>(Collections.emptyList());
        when(orderItemRepo.findAll(any(Specification.class), eq(pageable))).thenReturn(emptyPage);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> orderItemService.filterOrderItems(OrderStatus.PENDING, null, null, null, pageable));
    }
}
