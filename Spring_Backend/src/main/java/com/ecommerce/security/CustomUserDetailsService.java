package com.ecommerce.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.User;
import com.ecommerce.exception.NotFoundException;
import com.ecommerce.repository.UserRepo;

@Service

public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

	public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username)
                .orElseThrow(()-> new NotFoundException("User/ Email Not found"));
//
//        return AuthUser.builder()
//                .user(user)
//                .build();
        
        return new AuthUser(user);

    }
}
