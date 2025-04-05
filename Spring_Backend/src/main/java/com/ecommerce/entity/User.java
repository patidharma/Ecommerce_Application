
//
//import com.phegondev.Phegon.Eccormerce.enums.UserRole;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Objects;
//
//@Data
//@Entity
//@Table(name = "users")
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank(message = "Name is required")
//    private String name;
//
//    @Column(unique = true)
//    @NotBlank(message = "Email is required")
//    private String email;
//
//    @NotBlank(message = "Password number is required")
//    private String password;
//
//    @Column(name = "phone_number")
//    @NotBlank(message = "Phone number is required")
//    private String phoneNumber;
//
//    private UserRole role;
//    
//    
//
//    public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getPhoneNumber() {
//		return phoneNumber;
//	}
//
//	public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//
//	public UserRole getRole() {
//		return role;
//	}
//
//	public void setRole(UserRole role) {
//		this.role = role;
//	}
//
//	public List<OrderItem> getOrderItemList() {
//		return orderItemList;
//	}
//
//	public void setOrderItemList(List<OrderItem> orderItemList) {
//		this.orderItemList = orderItemList;
//	}
//
//	public Address getAddress() {
//		return address;
//	}
//
//	public void setAddress(Address address) {
//		this.address = address;
//	}
//
//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<OrderItem> orderItemList;
//
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
//    private Address address;
//
//    @Column(name = "created_at")
//    private final LocalDateTime createdAt = LocalDateTime.now();  // This will be excluded from hashCode and equals
//
//    // equals method
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;  // Using instanceof for subclass comparison
//        User user = (User) o;
//        return Objects.equals(id, user.id) &&
//                Objects.equals(name, user.name) &&
//                Objects.equals(email, user.email) &&
//                Objects.equals(password, user.password) &&
//                Objects.equals(phoneNumber, user.phoneNumber) &&
//                role == user.role &&
//                Objects.equals(orderItemList, user.orderItemList) &&
//                Objects.equals(address, user.address);
//    }
//
//    // hashCode method
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, email, password, phoneNumber, role, orderItemList, address);  // Excluding createdAt from hashCode
//    }
//
//    // canEqual method
//    public boolean canEqual(Object other) {
//        return other instanceof User;  // Allow comparison only between User instances or its subclasses
//    }
//	
//}

package com.ecommerce.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.ecommerce.enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

	public User() {
		super();
	}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @Column(name = "phone_number")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private UserRole role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Address address;

    @Column(name = "created_at")
    private final LocalDateTime createdAt = LocalDateTime.now();

    
    private User(UserBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.phoneNumber = builder.phoneNumber;
        this.role = builder.role;
        this.orderItemList = builder.orderItemList;
        this.address = builder.address;
    }

   
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhoneNumber() { return phoneNumber; }
    public UserRole getRole() { return role; }
    public List<OrderItem> getOrderItemList() { return orderItemList; }
    public Address getAddress() { return address; }
    public LocalDateTime getCreatedAt() { return createdAt; }

   
    public static class UserBuilder {
        private Long id;
        private String name;
        private String email;
        private String password;
        private String phoneNumber;
        private UserRole role;
        private List<OrderItem> orderItemList;
        private Address address;

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder role(UserRole role) {
            this.role = role;
            return this;
        }

        public UserBuilder orderItemList(List<OrderItem> orderItemList) {
            this.orderItemList = orderItemList;
            return this;
        }

        public UserBuilder address(Address address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

   
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                role == user.role &&
                Objects.equals(orderItemList, user.orderItemList) &&
                Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, phoneNumber, role, orderItemList, address);
    }
    
}
