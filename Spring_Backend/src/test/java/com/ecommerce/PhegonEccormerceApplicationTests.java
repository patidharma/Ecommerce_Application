package com.ecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.EcommerceApplication;

@SpringBootTest
class PhegonEccormerceApplicationTests {

    @Test
    void contextLoads() {
        // Test to ensure that the Spring context loads correctly
    }

    @Test
    public void main() {
        // Test the main method to ensure it runs without exceptions
        String[] args = {};
        EcommerceApplication.main(args);
    }
}
