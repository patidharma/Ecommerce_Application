package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.Address;
@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
}
