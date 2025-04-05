package com.ecommerce.service.interf;

import com.ecommerce.dto.LoginRequest;
import com.ecommerce.dto.Response;
import com.ecommerce.dto.UserDto;
import com.ecommerce.entity.User;

public interface UserService {
    Response registerUser(UserDto registrationRequest);
    Response loginUser(LoginRequest loginRequest);
    Response getAllUsers();
    User getLoginUser();
    Response getUserInfoAndOrderHistory();
}
