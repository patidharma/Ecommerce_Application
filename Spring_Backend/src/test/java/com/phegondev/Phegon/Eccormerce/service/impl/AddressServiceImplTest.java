package com.phegondev.Phegon.Eccormerce.service.impl;

import com.ecommerce.dto.AddressDto;
import com.ecommerce.dto.Response;
import com.ecommerce.entity.Address;
import com.ecommerce.entity.User;
import com.ecommerce.repository.AddressRepo;
import com.ecommerce.service.impl.AddressServiceImpl;
import com.ecommerce.service.interf.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceImplTest {

    @Mock
    private AddressRepo addressRepo;

    @Mock
    private UserService userService;

    @InjectMocks
    private AddressServiceImpl addressService;

    private User mockUser;
    private Address mockAddress;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock User and Address setup
        mockUser = new User();
        mockUser.setId(1L);

        mockAddress = new Address();
        mockAddress.setStreet("Old Street");
        mockAddress.setCity("Old City");
        mockAddress.setUser(mockUser);
        mockUser.setAddress(mockAddress);
    }

    @Test
    void testSaveAddress_NewAddress() {
        // User has no address
        mockUser.setAddress(null);
        when(userService.getLoginUser()).thenReturn(mockUser);
        when(addressRepo.save(any(Address.class))).thenReturn(mockAddress);

        AddressDto addressDto = new AddressDto();
        addressDto.setStreet("New Street");
        addressDto.setCity("New City");

        Response response = addressService.saveAndUpdateAddress(addressDto);

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals("Address successfully created", response.getMessage());

        verify(addressRepo, times(1)).save(any(Address.class));
    }

    @Test
    void testUpdateAddress_ExistingAddress() {
        when(userService.getLoginUser()).thenReturn(mockUser);
        when(addressRepo.save(any(Address.class))).thenReturn(mockAddress);

        AddressDto addressDto = new AddressDto();
        addressDto.setStreet("Updated Street");
        addressDto.setCity("Updated City");

        Response response = addressService.saveAndUpdateAddress(addressDto);

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals("Address successfully updated", response.getMessage());

        // Verify updated fields
        assertEquals("Updated Street", mockAddress.getStreet());
        assertEquals("Updated City", mockAddress.getCity());

        verify(addressRepo, times(1)).save(any(Address.class));
    }

    @Test
    void testSaveAddress_ExceptionThrownByUserService() {
        when(userService.getLoginUser()).thenThrow(new RuntimeException("User not found"));

        AddressDto addressDto = new AddressDto();
        addressDto.setStreet("New Street");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            addressService.saveAndUpdateAddress(addressDto);
        });

        assertEquals("User not found", exception.getMessage());
        verify(addressRepo, times(0)).save(any(Address.class));
    }

    @Test
    void testSaveAddress_NullDtoFields() {
        when(userService.getLoginUser()).thenReturn(mockUser);
        when(addressRepo.save(any(Address.class))).thenReturn(mockAddress);

        AddressDto addressDto = new AddressDto(); // All fields null
        Response response = addressService.saveAndUpdateAddress(addressDto);

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals("Address successfully updated", response.getMessage());

        // Ensure no changes to address fields
        assertEquals("Old Street", mockAddress.getStreet());
        assertEquals("Old City", mockAddress.getCity());

        verify(addressRepo, times(1)).save(any(Address.class));
    }
}
