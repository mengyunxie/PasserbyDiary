package com.passerby.authservice.service;

import com.passerby.authservice.client.UserServiceClient;
import com.passerby.authservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private UserServiceClient userServiceClient;

    @Autowired
    public CustomAuthenticationManager(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Implement your custom authentication logic here
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDTO userDTO = userServiceClient.getUser(username);
        // For demonstration purposes, let's assume a simple check
        if (userDTO != null && userDTO.getUsername().equals(username) && userDTO.getPassword().equals(password)) {
            Authentication auth = new UsernamePasswordAuthenticationToken(username, password);

            return auth;
        } else {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
