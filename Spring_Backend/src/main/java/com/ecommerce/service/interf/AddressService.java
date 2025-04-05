package com.ecommerce.service.interf;

import com.ecommerce.dto.AddressDto;
import com.ecommerce.dto.Response;

public interface AddressService {
    Response saveAndUpdateAddress(AddressDto addressDto);
}
