//package com.phegondev.Phegon.Eccormerce.dto;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.phegondev.Phegon.Eccormerce.enums.UserRole;
//import lombok.Builder;
//import lombok.Data;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.springframework.http.ResponseEntity;
//
////@Data
//@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class Response {
//
//    private int status;
//    private String message;
//    private final LocalDateTime timestamp = LocalDateTime.now();
//
//    private String token;
//    private String role;
//    private String  expirationTime;
//
//    private int totalPage;
//    private long totalElement;
//
//    private AddressDto address;
//
//    private UserDto user;
//    private List<UserDto> userList;
//
//    private CategoryDto category;
//    private List<CategoryDto> categoryList;
//
//    private ProductDto product;
//    private List<ProductDto> productList;
//
//    private OrderItemDto orderItem;
//    private List<OrderItemDto> orderItemList;
//
//    private OrderDto order;
//    private List<OrderDto> orderList;
//    
//    
//	public Response(int status, String message, String token, String role, String expirationTime, int totalPage,
//			long totalElement, AddressDto address, UserDto user, List<UserDto> userList, CategoryDto category,
//			List<CategoryDto> categoryList, ProductDto product, List<ProductDto> productList, OrderItemDto orderItem,
//			List<OrderItemDto> orderItemList, OrderDto order, List<OrderDto> orderList) {
//		super();
//		this.status = status;
//		this.message = message;
//		this.token = token;
//		this.role = role;
//		this.expirationTime = expirationTime;
//		this.totalPage = totalPage;
//		this.totalElement = totalElement;
//		this.address = address;
//		this.user = user;
//		this.userList = userList;
//		this.category = category;
//		this.categoryList = categoryList;
//		this.product = product;
//		this.productList = productList;
//		this.orderItem = orderItem;
//		this.orderItemList = orderItemList;
//		this.order = order;
//		this.orderList = orderList;
//	}
//	public int getStatus() {
//		return status;
//	}
//	public void setStatus(int status) {
//		this.status = status;
//	}
//	public String getMessage() {
//		return message;
//	}
//	public void setMessage(String message) {
//		this.message = message;
//	}
//	public String getToken() {
//		return token;
//	}
//	public void setToken(String token) {
//		this.token = token;
//	}
//	public String getRole() {
//		return role;
//	}
//	public void setRole(String role) {
//		this.role = role;
//	}
//	public String getExpirationTime() {
//		return expirationTime;
//	}
//	public void setExpirationTime(String expirationTime) {
//		this.expirationTime = expirationTime;
//	}
//	public int getTotalPage() {
//		return totalPage;
//	}
//	public void setTotalPage(int totalPage) {
//		this.totalPage = totalPage;
//	}
//	public long getTotalElement() {
//		return totalElement;
//	}
//	public void setTotalElement(long totalElement) {
//		this.totalElement = totalElement;
//	}
//	public AddressDto getAddress() {
//		return address;
//	}
//	public void setAddress(AddressDto address) {
//		this.address = address;
//	}
//	public UserDto getUser() {
//		return user;
//	}
//	public void setUser(UserDto user) {
//		this.user = user;
//	}
//	public List<UserDto> getUserList() {
//		return userList;
//	}
//	public void setUserList(List<UserDto> userList) {
//		this.userList = userList;
//	}
//	public CategoryDto getCategory() {
//		return category;
//	}
//	public void setCategory(CategoryDto category) {
//		this.category = category;
//	}
//	public List<CategoryDto> getCategoryList() {
//		return categoryList;
//	}
//	public void setCategoryList(List<CategoryDto> categoryList) {
//		this.categoryList = categoryList;
//	}
//	public ProductDto getProduct() {
//		return product;
//	}
//	public void setProduct(ProductDto product) {
//		this.product = product;
//	}
//	public List<ProductDto> getProductList() {
//		return productList;
//	}
//	public void setProductList(List<ProductDto> productList) {
//		this.productList = productList;
//	}
//	public OrderItemDto getOrderItem() {
//		return orderItem;
//	}
//	public void setOrderItem(OrderItemDto orderItem) {
//		this.orderItem = orderItem;
//	}
//	public List<OrderItemDto> getOrderItemList() {
//		return orderItemList;
//	}
//	public void setOrderItemList(List<OrderItemDto> orderItemList) {
//		this.orderItemList = orderItemList;
//	}
//	public OrderDto getOrder() {
//		return order;
//	}
//	public void setOrder(OrderDto order) {
//		this.order = order;
//	}
//	public List<OrderDto> getOrderList() {
//		return orderList;
//	}
//	public void setOrderList(List<OrderDto> orderList) {
//		this.orderList = orderList;
//	}
//	public LocalDateTime getTimestamp() {
//		return timestamp;
//	}
//	


package com.ecommerce.dto;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private int status;
    private String message;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private String token;
    private String role;
    private String expirationTime;
    private int totalPage;
    private long totalElement;
    private AddressDto address;
    private UserDto user;
    private List<UserDto> userList;
    private CategoryDto category;
    private List<CategoryDto> categoryList;
    private ProductDto product;
    private List<ProductDto> productList;
    private OrderItemDto orderItem;
    private List<OrderItemDto> orderItemList;
    private OrderDto order;
    private List<OrderDto> orderList;

    private Response(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.token = builder.token;
        this.role = builder.role;
        this.expirationTime = builder.expirationTime;
        this.totalPage = builder.totalPage;
        this.totalElement = builder.totalElement;
        this.address = builder.address;
        this.user = builder.user;
        this.userList = builder.userList;
        this.category = builder.category;
        this.categoryList = builder.categoryList;
        this.product = builder.product;
        this.productList = builder.productList;
        this.orderItem = builder.orderItem;
        this.orderItemList = builder.orderItemList;
        this.order = builder.order;
        this.orderList = builder.orderList;
    }

    public static class Builder {
        private int status;
        private String message;
        private String token;
        private String role;
        private String expirationTime;
        private int totalPage;
        private long totalElement;
        private AddressDto address;
        private UserDto user;
        private List<UserDto> userList;
        private CategoryDto category;
        private List<CategoryDto> categoryList;
        private ProductDto product;
        private List<ProductDto> productList;
        private OrderItemDto orderItem;
        private List<OrderItemDto> orderItemList;
        private OrderDto order;
        private List<OrderDto> orderList;

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder expirationTime(String expirationTime) {
            this.expirationTime = expirationTime;
            return this;
        }

        public Builder totalPage(int totalPage) {
            this.totalPage = totalPage;
            return this;
        }

        public Builder totalElement(long totalElement) {
            this.totalElement = totalElement;
            return this;
        }

        public Builder address(AddressDto address) {
            this.address = address;
            return this;
        }

        public Builder user(UserDto user) {
            this.user = user;
            return this;
        }

        public Builder userList(List<UserDto> userList) {
            this.userList = userList;
            return this;
        }

        public Builder category(CategoryDto category) {
            this.category = category;
            return this;
        }

        public Builder categoryList(List<CategoryDto> categoryList) {
            this.categoryList = categoryList;
            return this;
        }

        public Builder product(ProductDto product) {
            this.product = product;
            return this;
        }

        public Builder productList(List<ProductDto> productList) {
            this.productList = productList;
            return this;
        }

        public Builder orderItem(OrderItemDto orderItem) {
            this.orderItem = orderItem;
            return this;
        }

        public Builder orderItemList(List<OrderItemDto> orderItemList) {
            this.orderItemList = orderItemList;
            return this;
        }

        public Builder order(OrderDto order) {
            this.order = order;
            return this;
        }

        public Builder orderList(List<OrderDto> orderList) {
            this.orderList = orderList;
            return this;
        }

        // ✅ Build method to return Response object
        public Response build() {
            return new Response(this);
        }
    }

   
    public static Builder builder() {
        return new Builder();
    }

   
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public long getTotalElement() {
        return totalElement;
    }

    public AddressDto getAddress() {
        return address;
    }

    public UserDto getUser() {
        return user;
    }

    public List<UserDto> getUserList() {
        return userList;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public List<CategoryDto> getCategoryList() {
        return categoryList;
    }

    public ProductDto getProduct() {
        return product;
    }

    public List<ProductDto> getProductList() {
        return productList;
    }

    public OrderItemDto getOrderItem() {
        return orderItem;
    }

    public List<OrderItemDto> getOrderItemList() {
        return orderItemList;
    }

    public OrderDto getOrder() {
        return order;
    }

    public List<OrderDto> getOrderList() {
        return orderList;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}


