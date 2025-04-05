package com.phegondev.Phegon.Eccormerce.security;

import com.ecommerce.entity.User;
import com.ecommerce.enums.UserRole;
import com.ecommerce.exception.NotFoundException;
import com.ecommerce.repository.UserRepo;
import com.ecommerce.security.CustomUserDetailsService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDetailsServiceTest {

    private final UserRepo userRepo = Mockito.mock(UserRepo.class);
    private final CustomUserDetailsService userDetailsService = new CustomUserDetailsService(userRepo);

    @Test
    void testLoadUserByUsername_UserExists() {
        // Arrange
        User user = new User();
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
        user.setRole(UserRole.USER);

        when(userRepo.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername("john.doe@example.com");

        // Assert
        assertNotNull(userDetails);
        assertEquals("john.doe@example.com", userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("USER")));

        verify(userRepo, times(1)).findByEmail("john.doe@example.com");
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Arrange
        when(userRepo.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () ->
                userDetailsService.loadUserByUsername("notfound@example.com"));

        assertEquals("User/ Email Not found", exception.getMessage());
        verify(userRepo, times(1)).findByEmail("notfound@example.com");
    }
}
